package live.nerotv.lobby.listeners;

import com.destroystokyo.paper.event.player.PlayerPickupExperienceEvent;
import live.nerotv.Main;
import live.nerotv.lobby.Lobby;
import live.nerotv.lobby.managers.GUIManager;
import live.nerotv.lobby.managers.ItemManager;
import live.nerotv.user.User;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class PlayerInteractListener implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        if(!Lobby.buildList.contains(e.getPlayer().getUniqueId())) {
            e.setCancelled(true);
            if(e.getItem()!=null) {
                if(e.getItem().getItemMeta()!=null) {
                    Player p = e.getPlayer();
                    User u = Main.getUser(p.getUniqueId());
                    ItemStack item = e.getItem();
                    ItemMeta itemMeta = item.getItemMeta();
                    String itemName = itemMeta.getDisplayName();
                    if(itemName.equalsIgnoreCase(ItemManager.navigator().getItemMeta().getDisplayName())) {
                        p.openInventory(GUIManager.compassInventory(p));
                    }
                }
            }
        }
    }

    @EventHandler
    public void onInteract(PlayerInteractAtEntityEvent e) {
        if(!Lobby.buildList.contains(e.getPlayer().getUniqueId())) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onInteract(PlayerInteractEntityEvent e) {
        if(!Lobby.buildList.contains(e.getPlayer().getUniqueId())) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onInteract(PlayerDropItemEvent e) {
        if(!Lobby.buildList.contains(e.getPlayer().getUniqueId())) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onInteract(PlayerPickupArrowEvent e) {
        if(!Lobby.buildList.contains(e.getPlayer().getUniqueId())) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onInteract(PlayerPickupExperienceEvent e) {
        if(!Lobby.buildList.contains(e.getPlayer().getUniqueId())) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onInteract(PlayerAttemptPickupItemEvent e) {
        if(!Lobby.buildList.contains(e.getPlayer().getUniqueId())) {
            e.setCancelled(true);
        }
    }
}