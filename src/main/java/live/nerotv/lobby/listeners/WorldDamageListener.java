package live.nerotv.lobby.listeners;

import live.nerotv.lobby.Lobby;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.*;
import org.bukkit.event.hanging.HangingBreakByEntityEvent;
import org.bukkit.event.hanging.HangingBreakEvent;
import org.bukkit.event.hanging.HangingPlaceEvent;

public class WorldDamageListener implements Listener {

    @EventHandler
    public void onWorldDamage(HangingBreakByEntityEvent e) {
        if(e.getRemover() instanceof Player p) {
            if(!Lobby.buildList.contains(p.getUniqueId())) {
                e.setCancelled(true);
            }
        } else {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onWorldDamage(EntityDamageByEntityEvent e) {
        if(e.getDamager() instanceof Player p) {
            if(!Lobby.buildList.contains(p.getUniqueId())) {
                e.setCancelled(true);
            }
        } else {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onWorldDamage(HangingBreakEvent e) {
        if(!e.getCause().equals(HangingBreakEvent.RemoveCause.ENTITY)) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onWorldDamage(HangingPlaceEvent e) {
        if(e.getPlayer()!=null) {
            if (!Lobby.buildList.contains(e.getPlayer().getUniqueId())) {
                e.setCancelled(true);
            }
        } else {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onDamage(EntityDamageEvent e) {
        if(!e.getCause().equals(EntityDamageEvent.DamageCause.ENTITY_ATTACK)) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onWorldDamage(BlockBreakEvent e) {
        if(!Lobby.buildList.contains(e.getPlayer().getUniqueId())) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onWorldDamage(BlockPlaceEvent e) {
        if(!Lobby.buildList.contains(e.getPlayer().getUniqueId())) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onWorldDamage(EntityDamageByBlockEvent e) {
        e.setCancelled(true);
    }

    @EventHandler
    public void onWorldDamage(EntityExplodeEvent e) {
        e.setCancelled(true);
        e.getEntity().remove();
    }

    @EventHandler
    public void onWorldDamage(ExplosionPrimeEvent e) {
        e.setCancelled(true);
        e.setFire(false);
        e.setRadius(0);
    }
}