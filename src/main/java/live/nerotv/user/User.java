package live.nerotv.user;

import live.nerotv.Main;
import live.nerotv.utils.storage.Storage;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

import java.util.UUID;

public class User {

    private static Storage sqlstorage;

    private UUID uuid;
    private OfflinePlayer offlinePlayer;
    private Storage yamlstorage;
    public boolean isLaby;

    public User(UUID uuid) {
        this.uuid = uuid;
        this.offlinePlayer = Bukkit.getOfflinePlayer(uuid);
        this.isLaby = false;
    }

    public UUID getUUID() {
        return uuid;
    }

    public OfflinePlayer getOfflinePlayer() {
        return offlinePlayer;
    }

    public Storage getStorage() {
        if(Main.getAPIConfig().getCFG().getString("Settings.Backend.user").equalsIgnoreCase("sqlite")||Main.getAPIConfig().getCFG().getString("Settings.Backend.user").equalsIgnoreCase("sql")||Main.getAPIConfig().getCFG().getString("Settings.Backend.user").equalsIgnoreCase("MySQL")||Main.getAPIConfig().getCFG().getString("Settings.Backend.user").equalsIgnoreCase("maria")||Main.getAPIConfig().getCFG().getString("Settings.Backend.user").equalsIgnoreCase("mariadb")) {
            if(sqlstorage==null) {
                if (Main.getAPIConfig().getCFG().getString("Settings.Backend.user").equalsIgnoreCase("MySQL") || Main.getAPIConfig().getCFG().getString("Settings.Backend.user").equalsIgnoreCase("maria") || Main.getAPIConfig().getCFG().getString("Settings.Backend.user").equalsIgnoreCase("mariadb")) {
                    sqlstorage = new Storage((String) Main.getAPIConfig().get("Settings.MariaDB.host"), (String) Main.getAPIConfig().get("Settings.MariaDB.port"), (String) Main.getAPIConfig().get("Settings.MariaDB.database"), (String) Main.getAPIConfig().get("Settings.MariaDB.user"), (String) Main.getAPIConfig().get("Settings.MariaDB.password"));
                } else {
                    sqlstorage = new Storage("Nero/SQL/users.sql");
                }
            }
            return sqlstorage;
        } else {
            if(yamlstorage==null) {
                yamlstorage = new Storage("Nero/Users/"+uuid+".yml");
            }
            return yamlstorage;
        }
    }

    public void setSetting(String setting, String content) {
        getStorage().set(uuid.toString()+".settings."+setting.toUpperCase().replace(" ","_"),content);
    }

    public String getSetting(String setting) {
        return getStorage().getString(uuid.toString()+".settings."+setting.toUpperCase().replace(" ","_"));
    }

    public void delete() {
        Main.users.remove(uuid);
        if(Bukkit.getPlayer(uuid)!=null) {
            Bukkit.getPlayer(uuid).kickPlayer("Disconnected");
        }
        sqlstorage = null;
        yamlstorage = null;
        offlinePlayer = null;
        uuid = null;
        System.gc();
    }
}