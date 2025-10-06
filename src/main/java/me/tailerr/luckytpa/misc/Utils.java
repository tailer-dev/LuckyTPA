package me.tailerr.luckytpa.misc;

import me.tailerr.luckytpa.LuckyTPA;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class Utils {

    public String noPermissionMessage;
    public String teleportSent;
    public String teleportRecieved;
    public String consoleCmd;
    public String playerOffline;

    public String tpaPermission;
    public String tpacceptPermission;
    public String tpdenyPermission;
    public String tpPermission;
    public String tpherePermission;
    public String tpposPermission;
    public String bypassDelay;

    public String acceptButton;
    public String denyButton;
    public String noRequests;
    public String youAccepted;
    public String playerAccepted;
    public String youDenied;
    public String playerDenied;
    public String playerAcceptedTimer;
    public String teleportHereSent;
    public String teleportHereRecieved;

    public String tpaUsage;
    public String tpaHereUsage;
    public String tpUsage;
    public String tpHereUsage;

    public String tpMessage;
    public String tpHereMessage;

    public String tpPos;
    public String tpPosUsage;

    public String cantTeleportToSelf;
    public HashMap<UUID, UUID> tpaList = new HashMap<>();
    public HashMap<UUID, UUID> tpaHereList = new HashMap<>();
    public List<UUID> cooldownUUIDs = new ArrayList<>();

    public TextComponent acceptText;
    public TextComponent denyText;

    public boolean timerEnabled;
    public int timeDelay;
    public int cooldownDelay;
    public String cooldownMsg;

    LuckyTPA luckyTPA;

    public Utils(FileConfiguration configFile, LuckyTPA luckyTPA) {

        this.luckyTPA = luckyTPA;

        noPermissionMessage = color(configFile.getString("messages.no-permission"));
        teleportSent = color(configFile.getString("messages.sent-tpa"));
        teleportRecieved = color(configFile.getString("messages.received-tpa"));
        consoleCmd = color(configFile.getString("messages.console-cmd"));
        playerOffline = color(configFile.getString("messages.player-offline"));
        acceptButton = color(configFile.getString("messages.accept"));
        denyButton = color(configFile.getString("messages.deny"));
        noRequests = color(configFile.getString("messages.no-requests"));
        youAccepted = color(configFile.getString("messages.you-accepted"));
        youDenied = color(configFile.getString("messages.you-denied"));
        playerAccepted = color(configFile.getString("messages.player-accepted"));
        playerDenied = color(configFile.getString("messages.player-denied"));
        playerAcceptedTimer = color(configFile.getString("messages.player-accepted-timer"));
        teleportHereSent = color(configFile.getString("messages.sent-tpahere"));
        teleportHereRecieved = color(configFile.getString("messages.received-tpahere"));
        tpMessage = color(configFile.getString("messages.tp-msg"));
        tpHereMessage = color(configFile.getString("messages.tphere-msg"));
        cantTeleportToSelf = color(configFile.getString("messages.cant-tp-to-self"));

        tpaUsage = color(configFile.getString("usage-messages.tpa"));
        tpaHereUsage = color(configFile.getString("usage-messages.tpahere"));
        tpUsage = color(configFile.getString("usage-messages.tp"));
        tpHereUsage = color(configFile.getString("usage-messages.tphere"));

        tpPos = color(configFile.getString("messages.tppos"));
        tpPosUsage = color(configFile.getString("usage-messages.tppos"));

        tpaPermission = configFile.getString("permissions.tpa");
        tpacceptPermission = configFile.getString("permissions.tpaccept");
        tpdenyPermission = configFile.getString("permissions.tpdeny");
        tpPermission = configFile.getString("permissions.tp");
        tpherePermission = configFile.getString("permissions.tphere");
        bypassDelay = configFile.getString("permissions.bypass-delay");
        tpposPermission = configFile.getString("permissions.tppos");

        acceptText = new TextComponent(acceptButton);
        acceptText.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/tpaccept"));

        denyText = new TextComponent(denyButton);
        denyText.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/tpdeny"));

        timerEnabled = configFile.getBoolean("settings.time-delay");
        timeDelay = configFile.getInt("settings.time-to-teleport");

        cooldownDelay = configFile.getInt("settings.cooldown");
        cooldownMsg = color(configFile.getString("settings.cooldown-msg"));

    }

    public void removeCooldown(Player player) {

        new BukkitRunnable() {
            @Override
            public void run() {
                cooldownUUIDs.remove(player.getUniqueId());
            }
        }.runTaskLater(luckyTPA, 20L * cooldownDelay);

    }

    public String color(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

}