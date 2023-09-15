package live.nerotv.lobby.commands;

import live.nerotv.lobby.Lobby;
import live.nerotv.utils.MessageUtils;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import java.util.ArrayList;
import java.util.List;

public class BuildCommand implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(@NotNull CommandSender s, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {
        if(s instanceof Player p) {
            if(p.hasPermission("nero.team.build")) {
                if(args.length == 0) {
                    if (Lobby.buildList.contains(p.getUniqueId())) {
                        Lobby.buildList.remove(p.getUniqueId());
                        p.setGameMode(GameMode.ADVENTURE);
                    } else {
                        Lobby.buildList.add(p.getUniqueId());
                        p.setGameMode(GameMode.CREATIVE);
                    }
                    MessageUtils.sendMessage(p, "BUILD_MODE_CHANGE", false);
                } else {
                    if(p.hasPermission("nero.admin.build")) {
                        if (Bukkit.getPlayer(args[0]) != null) {
                            Player t = Bukkit.getPlayer(args[0]);
                            if (Lobby.buildList.contains(t.getUniqueId())) {
                                Lobby.buildList.remove(t.getUniqueId());
                                t.setGameMode(GameMode.ADVENTURE);
                            } else {
                                Lobby.buildList.add(t.getUniqueId());
                                t.setGameMode(GameMode.CREATIVE);
                            }
                            MessageUtils.sendMessage(t, "BUILD_MODE_CHANGE", false);
                            MessageUtils.sendMessage(p, "BUILD_MODE_CHANGE_OTHER", false);
                        } else {
                            MessageUtils.sendMessage(s, "PLAYER_NOT_FOUND", true);
                        }
                    } else {
                        MessageUtils.sendMessage(p,"NO_PERMISSIONS",true);
                    }
                }
            } else {
                MessageUtils.sendMessage(p,"NO_PERMISSIONS",true);
            }
        } else {
            if(args.length == 1) {
                if(s.hasPermission("nero.admin.build")) {
                    if(Bukkit.getPlayer(args[0])!=null) {
                        Player p = Bukkit.getPlayer(args[0]);
                        if(Lobby.buildList.contains(p.getUniqueId())) {
                            Lobby.buildList.remove(p.getUniqueId());
                            p.setGameMode(GameMode.ADVENTURE);
                        } else {
                            Lobby.buildList.add(p.getUniqueId());
                            p.setGameMode(GameMode.CREATIVE);
                        }
                        MessageUtils.sendMessage(p,"BUILD_MODE_CHANGE",false);
                        MessageUtils.sendMessage(s,"BUILD_MODE_CHANGE_OTHER",false);
                    } else {
                        MessageUtils.sendMessage(s,"PLAYER_NOT_FOUND",true);
                    }
                } else {
                    MessageUtils.sendMessage(s,"NO_PERMISSIONS",true);
                }
                return false;
            }
            MessageUtils.sendMessage(Bukkit.getConsoleSender(),"NEED_PLAYER",true);
        }
        return false;
    }

    ArrayList<String> players = new ArrayList<>();
    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender s, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {
        if(args.length == 1) {
            if(s.hasPermission("nero.team.build")) {
                 players.clear();
                 for(Player p:Bukkit.getOnlinePlayers()) {
                     players.add(p.getName());
                 }
                 return players;
            }
        }
        return null;
    }
}
