package live.nerotv.lobby.commands;

import live.nerotv.Main;
import live.nerotv.lobby.managers.FormManager;
import live.nerotv.utils.BedrockUtils;
import live.nerotv.utils.MessageUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class BedrockCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender s, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {
        if(s instanceof Player p) {
            if(BedrockUtils.isBedrock(p)) {
                FormManager.openBedrockJoinForm(Main.getUser(p.getUniqueId()));
            } else {
                MessageUtils.sendMessage(Bukkit.getConsoleSender(),"NEED_BEDROCK_PLAYER",true);
            }
        } else {
            MessageUtils.sendMessage(Bukkit.getConsoleSender(),"NEED_PLAYER",true);
        }
        return false;
    }
}
