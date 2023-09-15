package live.nerotv.lobby.listeners;

import live.nerotv.lobby.Lobby;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class PlayerHurtListener implements Listener {

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent e) {
        if(e.getEntity() instanceof Player t&&e.getDamager() instanceof Player p) {
            if(!Lobby.buildList.contains(p.getUniqueId())) {
                e.setCancelled(true);
            }
        }
    }
}