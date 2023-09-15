package live.nerotv.lobby.listeners;

import live.nerotv.Main;
import live.nerotv.user.User;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuitListener implements Listener {

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        Player p = e.getPlayer(); User u = Main.getUser(p.getUniqueId());
        e.setQuitMessage(null);
        u.delete();
    }
}