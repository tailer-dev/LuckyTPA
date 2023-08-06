package me.tailerr.luckytpa.commands;

import me.tailerr.luckytpa.LuckyTPA;
import me.tailerr.luckytpa.misc.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class TPAcceptCommand implements CommandExecutor {

    LuckyTPA luckyTPA;

    Utils utils;
    public TPAcceptCommand(Utils utils, LuckyTPA luckyTPA) {
        this.utils = utils;
        this.luckyTPA = luckyTPA;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (label.equalsIgnoreCase("tpaccept")) {
            if (sender instanceof ConsoleCommandSender) {
                sender.sendMessage(utils.consoleCmd);
                return true;
            }

            Player player = (Player) sender;

            if (player.hasPermission(utils.tpacceptPermission)) {

                if (utils.tpaList.containsKey(player.getUniqueId())) {

                    Player playerToTeleport = Bukkit.getPlayer(utils.tpaList.get(player.getUniqueId()));
                    player.sendMessage(utils.youAccepted);

                    if (utils.timerEnabled) {
                        playerToTeleport.sendMessage(utils.playerAcceptedTimer.replace("%player%", player.getName()).replace("%time%", String.valueOf(utils.timeDelay)));

                        new BukkitRunnable() {
                            @Override
                            public void run() {
                                playerToTeleport.teleport(player.getLocation());
                            }
                        }.runTaskLater(luckyTPA, 20 * utils.timeDelay);

                    } else {
                        playerToTeleport.sendMessage(utils.playerAccepted.replace("%player%", player.getName()));
                        playerToTeleport.teleport(player.getLocation());
                    }

                } else if (utils.tpaHereList.containsKey(player.getUniqueId())) {



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