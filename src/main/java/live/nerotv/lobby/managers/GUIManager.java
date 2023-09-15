package live.nerotv.lobby.managers;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class GUIManager {

    public static Inventory compassInventory(Player p) {
        Inventory inventory = Bukkit.createInventory(null,53);
        inventory.addItem(new ItemStack(Material.COMPASS));
        return inventory;
    }
}