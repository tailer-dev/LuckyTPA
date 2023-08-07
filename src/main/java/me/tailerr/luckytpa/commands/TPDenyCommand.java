package me.tailerr.luckytpa.commands;

import me.tailerr.luckytpa.misc.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class TPDenyCommand implements CommandExecutor {

    Utils utils;
    public TPDenyCommand(Utils utils) {
        this.utils = utils;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (label.equalsIgnoreCase("tpdeny")) {
            if (sender instanceof ConsoleCommandSender) {
                sender.sendMessage(utils.consoleCmd);
                return true;
            }

            Player player = (Player) sender;

            if (player.hasPermission(utils.tpdenyPermission)) {

                if (utils.tpaList.containsKey(player.getUniqueId())) {
                    Player playerToTeleport = Bukkit.getPlayer(utils.tpaList.get(player.getUniqueId()));

                    player.sendMessage(utils.youDenied);
                    playerToTeleport.sendMessage(utils.playerDenied.replace("%player%", player.getName()));

                    utils.tpaList.remove(player.getUniqueId());
                } else if (utils.tpaHereList.containsKey(player.getUniqueId())) {

                    Player playerToTeleportToo = Bukkit.getPlayer(utils.tpaHereList.get(player.getUniqueId()));

                    player.sendMessage(utils.youDenied);
                    playerToTeleportToo.sendMessage(utils.playerDenied.replace("%player%", player.getName()));

                    utils.tpaHereList.remove(player.getUniqueId());
                } else {
                    player.sendMessage(utils.noRequests);
                }

            } else {
                player.sendMessage(utils.noPermissionMessage);
            }

        }

        return true;
    }


}
