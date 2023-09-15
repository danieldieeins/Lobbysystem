package live.nerotv.utils;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MessageUtils {

    public static void sendConsoleMessage(String message) {
        sendMessage(Bukkit.getConsoleSender(),message);
    }

    public static void sendMessage(CommandSender receiver, String message) {
        receiver.sendMessage(StringUtils.formatString(message));
    }

    public static void sendMessage(CommandSender receiver, String message, boolean error) {
        if(error) {
            sendMessage(receiver, "§c"+message, Sound.BLOCK_ANVIL_BREAK, 100, 100);
        } else {
            sendMessage(receiver, "§7"+message, Sound.ENTITY_CHICKEN_EGG, 100, 100);
        }
    }

    public static void sendMessage(CommandSender receiver, String message, Sound sound) {
        sendMessage(receiver,message,sound,1,0);
    }

    public static void sendMessage(CommandSender receiver, String message, Sound sound, float volume, float pitch) {
        sendMessage(receiver,message);
        if(receiver instanceof Player p) {
            p.playSound(p,sound,volume,pitch);
        }
    }
}