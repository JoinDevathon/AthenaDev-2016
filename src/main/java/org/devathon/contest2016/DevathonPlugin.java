package org.devathon.contest2016;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.devathon.contest2016.commands.TerminalCommand;
import org.devathon.contest2016.listeners.PlayerChat;
import org.devathon.contest2016.listeners.PlayerInteract;
import org.devathon.contest2016.listeners.PlayerJoin;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

public class DevathonPlugin extends JavaPlugin {

    public Logger logger = getLogger();
    public FileConfiguration config = getConfig();
    public FileConfiguration dataConfig;
    public File data = new File(getDataFolder(), "userData.yml");

    @Override
    public void onEnable() {
        saveDefaultConfig();
        setupDataConfig();
        saveData();
        registerCommands();
        registerListeners();
    }

    @Override
    public void onDisable() {}

    public void setupDataConfig() {

        if(!data.exists()) {
            try {
                data.createNewFile();
                saveResource("userData.yml", true);
            }
            catch (IOException e) {
                logger.severe("Couldn't create userData.yml! Something went wrong!");
            }
        }
        dataConfig = YamlConfiguration.loadConfiguration(data);
    }

    public void saveData() {
        try {
            dataConfig.save(data);
        }
        catch (IOException e) {
            logger.severe("Couldn't save userData.yml! Something went wrong!");
        }
    }

    public void reloadData() {
        dataConfig = YamlConfiguration.loadConfiguration(data);
    }

    public void registerCommands() {
        getCommand("terminal").setExecutor(new TerminalCommand(this));
    }

    public void registerListeners() {
        getServer().getPluginManager().registerEvents(new PlayerChat(), this);
        getServer().getPluginManager().registerEvents(new PlayerInteract(), this);
        getServer().getPluginManager().registerEvents(new PlayerJoin(this), this);
    }
}