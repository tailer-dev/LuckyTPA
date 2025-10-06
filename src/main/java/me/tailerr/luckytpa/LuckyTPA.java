package me.tailerr.luckytpa;

import me.tailerr.luckytpa.commands.*;
import me.tailerr.luckytpa.misc.DeveloperDetection;
import me.tailerr.luckytpa.misc.Utils;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class LuckyTPA extends JavaPlugin {

    Metrics metrics;

    @Override
    public void onEnable() {
        metrics = new Metrics(this, 19635);
        getConfig().options().copyDefaults(true);
        saveDefaultConfig();
        Utils utils = new Utils(getConfig(), this);

        TPACommand tpaCommand = new TPACommand(utils);

        getCommand("tpa").setExecutor(tpaCommand);
        getCommand("tpahere").setExecutor(tpaCommand);
        getCommand("tpaccept").setExecutor(new TPAcceptCommand(utils, this));
        getCommand("tp").setExecutor(new TPCommand(utils));
        getCommand("tphere").setExecutor(new TPHereCommand(utils));
        getCommand("tpdeny").setExecutor(new TPDenyCommand(utils));
        getCommand("tppos").setExecutor(new TPPosCommand(utils));

        Bukkit.getPluginManager().registerEvents(new DeveloperDetection(), this);

    }

    @Override
    public void onDisable() {

        metrics.shutdown();

    }

}