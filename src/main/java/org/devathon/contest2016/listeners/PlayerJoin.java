package org.devathon.contest2016.listeners;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.devathon.contest2016.DevathonPlugin;

/**
 * Created by AthenaDev on 11/5/16.
 */
public class PlayerJoin implements Listener {

    DevathonPlugin plugin;

    public PlayerJoin(DevathonPlugin instance) {
        plugin = instance;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {

        Player player = (Player) e.getPlayer();

        plugin.dataConfig.createSection("users" + "." + player.getUniqueId().toString());
        ConfigurationSection cs = plugin.dataConfig.getConfigurationSection("users" + "." + player.getUniqueId().toString());
        cs.set("username", player.getName());
        cs.set("password", "none");



    }
}