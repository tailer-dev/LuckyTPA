package me.tailerr.luckytpa.commands;

import me.tailerr.luckytpa.misc.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class TPCommand implements CommandExecutor {

    Utils utils;
    public TPCommand(Utils utils) {
        this.utils = utils;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (label.equalsIgnoreCase("tp")) {
            if (sender instanceof ConsoleCommandSender) {
                sender.sendMessage(utils.consoleCmd);
                return true;
            }

            Player player = (Player) sender;

            if (player.hasPermission(utils.tpPermission)) {

                if (args.length < 1) {
                    player.sendMessage(utils.tpUsage);
                    return true;
                }

                Player playerToTeleportTo = Bukkit.getPlayer(args[0]);
                if (playerToTeleportTo == null) {
                    player.sendMessage(utils.playerOffline);
                } else {

                    if (player.getUniqueId().equals(playerToTeleportTo.getUniqueId())) {
                        player.sendMessage(utils.cantTeleportToSelf);
                        return true;
                    }

                    player.sendMessage(utils.tpMessage.replace("%player%", playerToTeleportTo.getName()));
                    player.teleport(playerToTeleportTo.getLocation());
                }

            } else {
                player.sendMessage(utils.noPermissionMessage);
            }

        }
        return true;
    }

}
