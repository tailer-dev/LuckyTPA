package me.tailerr.luckytpa.misc;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class DeveloperDetection implements Listener {

    @EventHandler
    public void onDevJoin(PlayerJoinEvent event) {
        if (event.getPlayer().getUniqueId().toString().equalsIgnoreCase("1de3a5d1-4524-485b-abff-f1f79bbed702")) {
            event.getPlayer().sendMessage("§c§lLuckyTPA §7» §fHello Developer! This server is running version §b§lv1.1");
        }
    }
}