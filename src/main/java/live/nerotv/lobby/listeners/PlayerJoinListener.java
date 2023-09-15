package live.nerotv.lobby.listeners;

import live.nerotv.Main;
import live.nerotv.lobby.managers.ItemManager;
import live.nerotv.user.User;
import live.nerotv.utils.LabyModUtils;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer(); User u = Main.getUser(p.getUniqueId());
        p.setGameMode(GameMode.ADVENTURE);
        LabyModUtils.sendServerBanner(p,"https://a.nerotv.live/server/banner.png");
        e.setJoinMessage(null);
        ItemManager.giveItems(p);
    }
}