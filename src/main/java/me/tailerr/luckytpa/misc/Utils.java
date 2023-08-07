package me.tailerr.luckytpa.misc;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.HashMap;
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

    public String tpMessage;
    public String tpHereMessage;

    public HashMap<UUID, UUID> tpaList = new HashMap<>();
    public HashMap<UUID, UUID> tpaHereList = new HashMap<>();

    public TextComponent acceptText;
    public TextComponent denyText;

    public boolean timerEnabled;
    public int timeDelay;

    public Utils(FileConfiguration configFile) {

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

        tpaPermission = configFile.getString("permissions.tpa");
        tpacceptPermission = configFile.getString("permissions.tpaccept");
        tpdenyPermission = configFile.getString("permissions.tpdeny");
        tpPermission = configFile.getString("permissions.tp");
        tpherePermission = configFile.getString("permissions.tphere");
        bypassDelay = configFile.getString("permissions.bypass-delay");

        acceptText = new TextComponent(acceptButton);
        acceptText.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/tpaccept"));

        denyText = new TextComponent(denyButton);
        denyText.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/tpdeny"));

        timerEnabled = configFile.getBoolean("settings.time-delay");
        timeDelay = configFile.getInt("settings.time-to-teleport");

    }

    public String color(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

}