package me.tailerr.luckytpa.commands;

import me.tailerr.luckytpa.misc.Utils;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class TPACommand implements CommandExecutor {

    Utils utils;
    public TPACommand(Utils utils) {
        this.utils = utils;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (label.equalsIgnoreCase("tpa")) {
            if (sender instanceof ConsoleCommandSender) {
                sender.sendMessage(utils.consoleCmd);
                return true;
            }

            Player player = (Player) sender;

            if (player.hasPermission(utils.tpaPermission)) {

                Player secondPlayer = Bukkit.getPlayerExact(args[0]);

                if (secondPlayer == null) {
                    player.sendMessage(utils.playerOffline);
                } else {

                    utils.tpaList.remove(secondPlayer.getUniqueId());
                    utils.tpaHereList.remove(player.getUniqueId());

                    utils.tpaList.put(secondPlayer.getUniqueId(), player.getUniqueId());

                    player.sendMessage(utils.teleportSent.replace("%player%", secondPlayer.getName()));

                    ComponentBuilder componentBuilder = new ComponentBuilder();
                    componentBuilder.append(utils.teleportRecieved.replace("%player%", player.getName()));
                    componentBuilder.append("\n");
                    componentBuilder.append(utils.acceptText + " §7| " + utils.denyText);

                    secondPlayer.spigot().sendMessage(componentBuilder.create());

                }

            } else {
                player.sendMessage(utils.noPermissionMessage);
            }

        }

        if (label.equalsIgnoreCase("tpahere")) {
            if (sender instanceof ConsoleCommandSender) {
                sender.sendMessage(utils.consoleCmd);
                return true;
            }

            Player player = (Player) sender;

            if (player.hasPermission(utils.tpaPermission)) {

                Player secondPlayer = Bukkit.getPlayerExact(args[0]);

                if (secondPlayer == null) {
                    player.sendMessage(utils.playerOffline);
                } else {

                    utils.tpaList.remove(secondPlayer.getUniqueId());
                    utils.tpaHereList.remove(player.getUniqueId());

                    utils.tpaHereList.put(secondPlayer.getUniqueId(), player.getUniqueId());

                    player.sendMessage(utils.teleportHereSent.replace("%player%", secondPlayer.getName()));

                    ComponentBuilder componentBuilder = new ComponentBuilder();
                    componentBuilder.append(utils.teleportHereRecieved.replace("%player%", player.getName()));
                    componentBuilder.append("\n");
                    componentBuilder.append(utils.acceptText + " §7| " + utils.denyText);

                    secondPlayer.spigot().sendMessage(componentBuilder.create());

                }

            } else {
                player.sendMessage(utils.noPermissionMessage);
            }

        }

        return true;
    }

}
