package live.nerotv.utils;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.handler.codec.DecoderException;
import io.netty.handler.codec.EncoderException;
import live.nerotv.Main;
import net.minecraft.network.PacketDataSerializer;
import net.minecraft.network.protocol.game.PacketPlayOutCustomPayload;
import net.minecraft.resources.MinecraftKey;
import net.minecraft.server.network.PlayerConnection;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;
import org.jetbrains.annotations.NotNull;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.nio.charset.Charset;
import java.util.List;
import java.util.UUID;

public class LabyModUtils implements PluginMessageListener {

    public static void playCinematic(Player player, List<Location> points, long duration) {
        JsonObject cinematic = new JsonObject();
        JsonArray pointss = new JsonArray();
        for(Location location : points) {
            JsonObject point = new JsonObject();
            point.addProperty( "x", location.getX() );
            point.addProperty( "y", location.getY() );
            point.addProperty( "z", location.getZ() );
            point.addProperty( "yaw", location.getYaw() );
            point.addProperty( "pitch", location.getPitch() );
            point.addProperty( "tilt", 0 );
            pointss.add( point );
        }
        cinematic.add( "points", pointss );
        cinematic.addProperty( "duration", duration );
        player.teleport(points.get(0));
        player.setAllowFlight( true );
        sendLabyModMessage( player, "cinematic", cinematic );
    }

    public static void cancelCinematic(Player player) {
        sendLabyModMessage( player, "cinematic", new JsonObject() ); // Empty object
    }

    public static void sendCineScope( Player player, int coveragePercent, long duration ) {
        JsonObject object = new JsonObject();
        object.addProperty( "coverage", coveragePercent );
        object.addProperty( "duration", duration );
        sendLabyModMessage( player, "cinescopes", object );
    }

    public static void disableVoiceChat(Player player) {
        JsonObject object = new JsonObject();
        object.addProperty( "allowed", false );
        sendLabyModMessage( player, "voicechat", object );
    }

    public static void sendSettings( Player player, boolean keepSettings, boolean required ) {
        JsonObject voicechatObject = new JsonObject();
        voicechatObject.addProperty( "keep_settings_on_server_switch", keepSettings );
        JsonObject requestSettingsObject = new JsonObject();
        requestSettingsObject.addProperty("required", required );
        JsonObject settingsObject = new JsonObject();
        settingsObject.addProperty("enabled", true);
        settingsObject.addProperty("microphoneVolume", 10);
        settingsObject.addProperty("surroundRange", 10);
        settingsObject.addProperty("surroundVolume", 10);
        settingsObject.addProperty("continuousTransmission", false);
        requestSettingsObject.add("settings", settingsObject );
        voicechatObject.add("request_settings", requestSettingsObject);
        sendLabyModMessage( player, "voicechat", voicechatObject );
    }

    public static void sendClientToServer( Player player, String title, String address, boolean preview ) {
        JsonObject object = new JsonObject();
        object.addProperty( "title", title );
        object.addProperty( "address", address );
        object.addProperty( "preview", preview );
        sendLabyModMessage( player, "server_switch", object );
    }

    public static void sendCurrentPlayingGamemode( Player player, boolean visible, String gamemodeName ) {
        JsonObject object = new JsonObject();
        object.addProperty( "show_gamemode", visible );
        object.addProperty( "gamemode_name", gamemodeName );
        sendLabyModMessage( player, "server_gamemode", object );
    }

    public void forceSticker( Player receiver, UUID npcUUID, short stickerId ) {
        JsonArray array = new JsonArray();
        JsonObject forcedSticker = new JsonObject();
        forcedSticker.addProperty( "uuid", npcUUID.toString() );
        forcedSticker.addProperty( "sticker_id", stickerId );
        array.add(forcedSticker);
        sendLabyModMessage( receiver, "sticker_api", array );
    }

    public static void forceEmote( Player receiver, UUID npcUUID, int emoteId ) {
        JsonArray array = new JsonArray();
        JsonObject forcedEmote = new JsonObject();
        forcedEmote.addProperty( "uuid", npcUUID.toString() );
        forcedEmote.addProperty( "emote_id", emoteId );
        array.add(forcedEmote);
        sendLabyModMessage( receiver, "emote_api", array );
    }

    public static void updateBalanceDisplay(Player player, EnumBalanceType type, boolean visible, int balance) {
        JsonObject economyObject = new JsonObject();
        JsonObject cashObject = new JsonObject();
        cashObject.addProperty( "visible", visible );
        cashObject.addProperty( "balance", balance );
        economyObject.add(type.getKey(), cashObject);
        sendLabyModMessage( player, "economy", economyObject );
    }

    public static void sendWatermark(Player player, boolean visible) {
        JsonObject object = new JsonObject();
        object.addProperty("visible", visible);
        sendLabyModMessage(player, "watermark", object);
    }

    public static void sendFlag(Player receiver, UUID uuid, String countryCode) {
        JsonObject flagPacket = new JsonObject();
        JsonArray users = new JsonArray();
        JsonObject userObject = new JsonObject();
        userObject.addProperty("uuid", uuid.toString());
        userObject.addProperty("code", countryCode);
        users.add(userObject);
        flagPacket.add("users", users);
        sendLabyModMessage(receiver, "language_flag", flagPacket);
    }

    public static void setSubtitle(Player receiver, UUID subtitlePlayer, String value) {
        JsonArray array = new JsonArray();
        JsonObject subtitle = new JsonObject();
        subtitle.addProperty("uuid", subtitlePlayer.toString());
        subtitle.addProperty("size", 0.8d); //0.8 - 1.6
        if (value != null)
            subtitle.addProperty("value", value);
        array.add(subtitle);
        sendLabyModMessage(receiver, "account_subtitle", array);
    }

    public static void sendServerBanner(Player player, String imageUrl) {
        JsonObject object = new JsonObject();
        object.addProperty("url", imageUrl);
        sendLabyModMessage(player, "server_banner", object);
    }

    @Override
    public void onPluginMessageReceived(@NotNull String channel, @NotNull Player player, @NotNull byte[] message) {
        if (!channel.equals("labymod3:main")) {
            return;
        }
        DataInputStream in = new DataInputStream(new ByteArrayInputStream(message));
        ByteBuf buf = Unpooled.wrappedBuffer(message);
        String key = readString(buf, Short.MAX_VALUE);
        String json = readString(buf, Short.MAX_VALUE);
        if (key.equals("INFO")) {
            Main.getUser(player.getUniqueId()).isLaby = true;
            JsonObject obj = new JsonObject();
            String domain = "a.nerotv.live";
            sendLabyModMessage( player, "discord_rpc", obj );
        }
    }

    private JsonObject addSecret( JsonObject jsonObject, String hasKey, String key, UUID secret, String domain) {
        jsonObject.addProperty( hasKey, true );
        jsonObject.addProperty( key, secret.toString() + ":" + domain );
        return jsonObject;
    }

    private static void sendLabyModMessage(Player player, String key, JsonElement messageContent) {
        byte[] bytes = getBytesToSend(key, messageContent.toString());
        PacketDataSerializer pds = new PacketDataSerializer(Unpooled.wrappedBuffer(bytes));
        PacketPlayOutCustomPayload payloadPacket = new PacketPlayOutCustomPayload(new MinecraftKey("labymod3:main"), pds);
        CraftPlayer p = (CraftPlayer) player;
        PlayerConnection c = p.getHandle().c;
        c.a(payloadPacket);
    }

    private static byte[] getBytesToSend(String messageKey, String messageContents) {
        ByteBuf byteBuf = Unpooled.buffer();
        writeString(byteBuf, messageKey);
        writeString(byteBuf, messageContents);
        byte[] bytes = new byte[byteBuf.readableBytes()];
        byteBuf.readBytes(bytes);
        byteBuf.release();
        return bytes;
    }

    private static void writeVarIntToBuffer(ByteBuf buf, int input) {
        while ((input & -128) != 0) {
            buf.writeByte(input & 127 | 128);
            input >>>= 7;
        }
        buf.writeByte(input);
    }

    private static void writeString(ByteBuf buf, String string) {
        byte[] abyte = string.getBytes(Charset.forName("UTF-8"));
        if (abyte.length > Short.MAX_VALUE) {
            throw new EncoderException("String too big (was " + string.length() + " bytes encoded, max " + Short.MAX_VALUE + ")");
        } else {
            writeVarIntToBuffer(buf, abyte.length);
            buf.writeBytes(abyte);
        }
    }

    private static int readVarIntFromBuffer(ByteBuf buf) {
        int i = 0;
        int j = 0;
        byte b0;
        do {
            b0 = buf.readByte();
            i |= (b0 & 127) << j++ * 7;
            if (j > 5) {
                throw new RuntimeException("VarInt too big");
            }
        } while ((b0 & 128) == 128);
        return i;
    }

    private static String readString(ByteBuf buf, int maxLength) {
        int i = readVarIntFromBuffer(buf);
        if (i > maxLength * 4) {
            throw new DecoderException("The received encoded string buffer length is longer than maximum allowed (" + i + " > " + maxLength * 4 + ")");
        } else if (i < 0) {
            throw new DecoderException("The received encoded string buffer length is less than zero! Weird string!");
        } else {
            byte[] bytes = new byte[i];
            buf.readBytes(bytes);

            String s = new String(bytes, Charset.forName("UTF-8"));
            if (s.length() > maxLength) {
                throw new DecoderException("The received string length is longer than maximum allowed (" + i + " > " + maxLength + ")");
            } else {
                return s;
            }
        }
    }

    public enum EnumBalanceType {
        CASH("cash"),
        BANK("bank");
        private final String key;
        EnumBalanceType(String key) {
            this.key = key;
        }
        public String getKey() {
            return this.key;
        }
    }
}