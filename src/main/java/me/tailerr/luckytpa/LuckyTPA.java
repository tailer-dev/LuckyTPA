package me.tailerr.luckytpa;

import me.tailerr.luckytpa.commands.*;
import me.tailerr.luckytpa.misc.Utils;
import org.bukkit.plugin.java.JavaPlugin;

public final class LuckyTPA extends JavaPlugin {

    @Override
    public void onEnable() {

        getConfig().options().copyDefaults(true);
        saveDefaultConfig();

        Utils utils = new Utils(getConfig());

        getCommand("tpa").setExecutor(new TPACommand(utils));
        getCommand("tpaccept").setExecutor(new TPAcceptCommand(utils, this));
        getCommand("tp").setExecutor(new TPCommand(utils));
        getCommand("tphere").setExecutor(new TPHereCommand(utils));
        getCommand("tpdeny").setExecutor(new TPDenyCommand(utils));

    }

    @Override
    public void onDisable() {



    }

}