package me.tailerr.luckytpa;

import me.tailerr.luckytpa.commands.TPACommand;
import me.tailerr.luckytpa.commands.TPAcceptCommand;
import me.tailerr.luckytpa.commands.TPCommand;
import me.tailerr.luckytpa.commands.TPHereCommand;
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

    }

    @Override
    public void onDisable() {



    }

}