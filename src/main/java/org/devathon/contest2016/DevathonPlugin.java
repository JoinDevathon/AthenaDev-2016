package org.devathon.contest2016;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.devathon.contest2016.commands.TerminalCommand;
import org.devathon.contest2016.listeners.PlayerChat;
import org.devathon.contest2016.listeners.PlayerInteract;
import org.devathon.contest2016.listeners.PlayerJoin;
import org.devathon.contest2016.utils.Utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Logger;

public class DevathonPlugin extends JavaPlugin {

    public Logger logger = getLogger();
    public FileConfiguration config = getConfig();
    public FileConfiguration dataConfig;
    public File data = new File(getDataFolder(), "userData.yml");
    public ItemStack skull;

    @Override
    public void onEnable() {
        new Utils(this);
        saveDefaultConfig();
        setupDataConfig();
        saveData();
        setupSkullItemStack();
        registerCommands();
        registerListeners();
    }

    public void setupSkullItemStack() {
        skull = new ItemStack(Material.SKULL_ITEM, 1, (short)3);
        SkullMeta skullMeta = (SkullMeta) skull.getItemMeta(); // make in main class instead
        skullMeta.setOwner("Hack");
        skull.setItemMeta(skullMeta);
        skullMeta.setDisplayName(ChatColor.DARK_GREEN + "Terminal Block");
        ArrayList<String> lore = new ArrayList<String>();
        lore.add(ChatColor.GRAY + "Right-click on a block to place a Terminal.");
        lore.add(ChatColor.GRAY + "Then, right click the head to open the Terminal.");
        skullMeta.setLore(lore);
        skull.setItemMeta(skullMeta);
    }

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
        getServer().getPluginManager().registerEvents(new PlayerChat(this), this);
        getServer().getPluginManager().registerEvents(new PlayerInteract(this), this);
        getServer().getPluginManager().registerEvents(new PlayerJoin(this), this);
    }
}