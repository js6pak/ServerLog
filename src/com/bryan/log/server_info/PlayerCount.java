package com.bryan.log.server_info;

import com.bryan.log.ServerLog;
import com.bryan.log.utils.Methods;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.InvalidConfigurationException;

import java.io.IOException;

public class PlayerCount {

    private ServerLog serverLog;
    private Methods methods;
    public PlayerCount(ServerLog serverLog) {
        this.serverLog = serverLog;
        this.methods = new Methods(serverLog);
    }

    public void appendPlayerCount() throws IOException {
        if (methods.dateChanged("/Server Information/Player Count/")) {
            try {
                methods.moveToHistory();
            } catch (InvalidConfigurationException ex) {
                Bukkit.getConsoleSender().sendMessage(ChatColor.DARK_RED + "There was a fatal error moving the files to the History...");
            }
        }

        Integer playerCount = Bukkit.getServer().getOnlinePlayers().size();
        methods.appendString("/Server Information/Player Count/", methods.getConfigFile().getString("player-count").replace("[count]", playerCount.toString()));
    }

}