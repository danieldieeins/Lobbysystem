package live.nerotv;

import live.nerotv.jumper.Jumper;
import live.nerotv.lobby.Lobby;
import live.nerotv.pixels.Pixels;
import live.nerotv.stickfight.Stickfight;
import live.nerotv.user.User;
import live.nerotv.utils.storage.types.Config;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.UUID;

public final class Main extends JavaPlugin {

    public static HashMap<UUID, User> users;
    private static Jumper jumper;
    private static Pixels pixels;
    private static Stickfight stickfight;
    private static Lobby lobby;
    private static Main instance;
    private static Config config;

    private void initConfig() {
        config = new Config("Nero/API/config.yml");
        config.checkEntry("Settings.Backend.user","SQLite");
        config.checkEntry("Settings.Backend.memory","SQLite");
        config.checkEntry("Settings.MariaDB.host","127.0.0.1");
        config.checkEntry("Settings.MariaDB.port",3306);
        config.checkEntry("Settings.MariaDB.database","database");
        config.checkEntry("Settings.MariaDB.user","user");
        config.checkEntry("Settings.MariaDB.password","password");
    }

    @Override
    public void onLoad() {
        initConfig();
        instance = this;
        users = new HashMap<>();
        jumper = new Jumper(instance);
        pixels = new Pixels(instance);
        stickfight = new Stickfight(instance);
        lobby = new Lobby(instance);
        jumper.onLoad();
        pixels.onLoad();
        stickfight.onLoad();
        lobby.onLoad();
    }

    @Override
    public void onEnable() {
        jumper.onEnable();
        pixels.onEnable();
        stickfight.onEnable();
        lobby.onEnable();
    }

    @Override
    public void onDisable() {
        lobby.onDisable();
        lobby = null;
        stickfight.onDisable();
        stickfight = null;
        pixels.onDisable();
        pixels = null;
        jumper.onDisable();
        jumper = null;
        instance = null;
        config = null;
        users.clear();
        users = null;
        System.gc();
    }

    public static Jumper getJumper() {
        return jumper;
    }

    public static Pixels getPixels() {
        return pixels;
    }

    public static Stickfight getStickfight() {
        return stickfight;
    }

    public static Lobby getLobby() {
        return lobby;
    }

    public static Main getInstance() {
        return instance;
    }

    public static Config getAPIConfig() {
        return config;
    }

    public static User getUser(UUID uuid) {
        if(users.containsKey(uuid)) {
            return users.get(uuid);
        }
        users.put(uuid,new User(uuid));
        return getUser(uuid);
    }
}