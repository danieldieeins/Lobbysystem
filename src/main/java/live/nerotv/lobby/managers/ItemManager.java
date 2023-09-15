package live.nerotv.lobby.managers;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

public class ItemManager {

    public static void giveItems(Player player) {
        player.getInventory().clear();
        player.getInventory().setItem(1,navigator());
        player.getInventory().setItem(3,profile(player));
        player.getInventory().setItem(4,maps());
        player.getInventory().setItem(5,hider());
        player.getInventory().setItem(7,extras());
    }

    public static ItemStack navigator() {
        ItemStack item = new ItemStack(Material.COMPASS);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName("NAVIGATOR");
        item.setItemMeta(itemMeta);
        return item;
    }

    public static ItemStack profile(Player player) {
        ItemStack item = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta itemMeta = (SkullMeta)item.getItemMeta();
        itemMeta.setDisplayName("PROFILE");
        itemMeta.setOwningPlayer(player);
        item.setItemMeta(itemMeta);
        return item;
    }

    public static ItemStack maps() {
        ItemStack item = new ItemStack(Material.CHEST);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName("MAPS");
        item.setItemMeta(itemMeta);
        return item;
    }

    public static ItemStack hider() {
        ItemStack item = new ItemStack(Material.BLAZE_ROD);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName("PLAYER_HIDER");
        item.setItemMeta(itemMeta);
        return item;
    }

    public static ItemStack extras() {
        ItemStack item = new ItemStack(Material.TURTLE_HELMET);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName("EXTRAS");
        item.setItemMeta(itemMeta);
        return item;
    }
}