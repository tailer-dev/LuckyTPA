package me.tailerr.luckytpa.commands;

import me.tailerr.luckytpa.misc.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class TPPosCommand implements CommandExecutor {

    Utils utils;

    public TPPosCommand(Utils utils) {
        this.utils = utils;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (label.equalsIgnoreCase("tppos")) {

            if (sender instanceof ConsoleCommandSender) {
                sender.sendMessage(utils.consoleCmd);
                return true;
            }

            Player player = (Player) sender;

            if (player.hasPermission(utils.tpposPermission)) {

                if (args.length < 3) {
                    player.sendMessage(utils.tpPosUsage);
                    return true;
                }

                double x = Double.parseDouble(args[0]);
                double y = Double.parseDouble(args[1]);
                double z = Double.parseDouble(args[2]);

                Location teleportLocation = new Location(player.getLocation().getWorld(), x, y, z);
                player.teleport(teleportLocation);

                player.sendMessage(utils.tpPos
                        .replace("%x%", String.valueOf(x))
                        .replace("%y%", String.valueOf(y))
                        .replace("%z%", String.valueOf(z)));

            } else {
                player.sendMessage(utils.noPermissionMessage);
            }
        }
        return true;
    }

}