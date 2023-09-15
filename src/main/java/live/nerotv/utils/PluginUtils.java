package live.nerotv.utils;

import live.nerotv.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.TabCompleter;
import org.bukkit.event.Listener;

public class PluginUtils {

    public static void initCommand(CommandExecutor commandExecutor) {
        MessageUtils.sendConsoleMessage("§8    §f--> §7Loading command §e" + commandExecutor.getClass().getSimpleName() + "§8...");
        Main.getInstance().getCommand(commandExecutor.getClass().getSimpleName().replace("Command", "")).setExecutor(commandExecutor);
    }

    public static void initCommand(CommandExecutor commandExecutor, TabCompleter tabCompleter) {
        MessageUtils.sendConsoleMessage("§8    §f--> §7Loading command §e" + commandExecutor.getClass().getSimpleName() + "§7 with §fTabCompleter§8...");
        Main.getInstance().getCommand(commandExecutor.getClass().getSimpleName().replace("Command", "")).setTabCompleter(tabCompleter);
        Main.getInstance().getCommand(commandExecutor.getClass().getSimpleName().replace("Command", "")).setExecutor(commandExecutor);
    }

    public static void initListeners(Listener listener) {
        MessageUtils.sendConsoleMessage("§8    §f--> §7Loading listener(s) from §e" + listener.getClass().getSimpleName() + "§8...");
        Bukkit.getPluginManager().registerEvents(listener, Main.getInstance());
    }
}