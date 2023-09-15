package live.nerotv.lobby.listeners;

import live.nerotv.lobby.Lobby;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class PlayerInventoryListener implements Listener {

    @EventHandler
    public void onInventory(InventoryClickEvent e) {
        Player p = (Player)e.getWhoClicked();
        if(!Lobby.buildList.contains(p.getUniqueId())) {
            e.setCancelled(true);
        }
    }
}