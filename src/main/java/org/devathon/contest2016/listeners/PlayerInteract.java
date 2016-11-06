package org.devathon.contest2016.listeners;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.devathon.contest2016.DevathonPlugin;

import java.util.ArrayList;

/**
 * Created by AthenaDev on 11/5/16.
 */
public class PlayerInteract implements Listener {

    public static ArrayList<String> enterUsername = new ArrayList<String>();
    public static ArrayList<String> enterPassword = new ArrayList<String>();

    private DevathonPlugin plugin;

    public PlayerInteract(DevathonPlugin instance) {
        plugin = instance;
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {
        if((e.getPlayer().getGameMode() == GameMode.CREATIVE) && (e.getItem().getItemMeta().getDisplayName().equals(ChatColor.DARK_GREEN + "Terminal Block"))) {
            e.setCancelled(true);
            e.getPlayer().sendMessage("You can't use the Terminal while in Creative Mode!");
            return;
        }

        // Improvement: Rather than checking for the display name Terminal Block, check if the block's skullMeta hasOwner "Hack"

        Player player = e.getPlayer();

        if(e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if(e.getItem().getType().equals(Material.SKULL_ITEM) && (e.getItem().getItemMeta().getDisplayName().equals(ChatColor.DARK_GREEN + "Terminal Block"))) {
                player.getInventory().getItemInMainHand().setAmount(player.getInventory().getItemInMainHand().getAmount() - 1);
                player.sendMessage(ChatColor.GREEN + "> " + ChatColor.DARK_GREEN + "Loading terminal...");
                player.sendMessage("");
                player.sendMessage(ChatColor.GREEN + "> " + ChatColor.DARK_GREEN + "Type 'exit' or 'cancel' at any time to end your current session.");
                player.sendMessage(ChatColor.GREEN + "> " + ChatColor.DARK_GREEN + "Enter your username." + ChatColor.DARK_GRAY + " (By default your Minecraft username)");
                player.sendMessage(ChatColor.GREEN + "> _");
                enterUsername.add(player.getUniqueId().toString());
            }
        }
        else {
            return;
        }
    }
}