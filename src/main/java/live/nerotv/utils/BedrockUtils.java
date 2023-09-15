package live.nerotv.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class BedrockUtils {

    public static boolean isBedrock(Player player) {
        if(Bukkit.getPluginManager().getPlugin("floodgate")!=null) {
            return org.geysermc.floodgate.api.FloodgateApi.getInstance().isFloodgatePlayer(player.getUniqueId());
        }
        return false;
    }
}