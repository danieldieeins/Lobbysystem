package live.nerotv.lobby;

import live.nerotv.Main;
import live.nerotv.lobby.commands.BedrockCommand;
import live.nerotv.lobby.commands.BuildCommand;
import live.nerotv.lobby.commands.LabyModCommand;
import live.nerotv.lobby.listeners.*;
import live.nerotv.utils.LabyModUtils;
import live.nerotv.utils.PluginUtils;
import java.util.ArrayList;
import java.util.UUID;

public class Lobby {

    private static Main instance;
    public static ArrayList<UUID> buildList;

    public Lobby(Main main) {
        instance = main;
    }

    public void onLoad() {
        buildList = new ArrayList<>();
    }

    public void onEnable() {
        loadCommands();
        loadListeners();
        instance.getServer().getMessenger().registerIncomingPluginChannel(instance, "labymod3:main", new LabyModUtils());
    }

    public void onDisable() {
        buildList = null;
        instance = null;
    }

    private void loadCommands() {
        BuildCommand buildCommand = new BuildCommand();
        LabyModCommand labyModCommand = new LabyModCommand();

        PluginUtils.initCommand(new BedrockCommand());
        PluginUtils.initCommand(buildCommand,buildCommand);
        PluginUtils.initCommand(labyModCommand,labyModCommand);
    }

    private void loadListeners() {
        PluginUtils.initListeners(new PlayerHurtListener());
        PluginUtils.initListeners(new PlayerInteractListener());
        PluginUtils.initListeners(new PlayerInventoryListener());
        PluginUtils.initListeners(new PlayerJoinListener());
        PluginUtils.initListeners(new PlayerQuitListener());
        PluginUtils.initListeners(new WorldDamageListener());
    }

    public static Main getInstance() {
        return instance;
    }
}