package org.devathon.contest2016.utils;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.devathon.contest2016.DevathonPlugin;

/**
 * Created by AthenaDev on 11/5/16.
 */
public class Utils {

    public static DevathonPlugin plugin;

    public Utils(DevathonPlugin instance) {
        plugin = instance;
    }

    public static void setUsername(Player player, String name) {
        ConfigurationSection cs = plugin.dataConfig.getConfigurationSection("users" + "." + player.getUniqueId().toString());
        cs.set("username", name);
        plugin.saveData();
    }

    public static void setPassword(Player player, String password) {
        ConfigurationSection cs = plugin.dataConfig.getConfigurationSection("users" + "." + player.getUniqueId().toString());
        cs.set("password", password);
        plugin.saveData();
    }

    public static String getUsername(Player player) {
        return plugin.dataConfig.getConfigurationSection("users" + "." + player.getUniqueId().toString()).getString("username");
    }

    public static String getPassword(Player player) {
        return plugin.dataConfig.getConfigurationSection("users" + "." + player.getUniqueId().toString()).getString("password");
    }
}