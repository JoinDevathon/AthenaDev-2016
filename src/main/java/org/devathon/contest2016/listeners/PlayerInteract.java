package org.devathon.contest2016.listeners;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;

/**
 * Created by AthenaDev on 11/5/16.
 */
public class PlayerInteract implements Listener {

    public static ItemStack skull;
    public static ArrayList<String> enterUsername = new ArrayList<String>();
    public static ArrayList<String> enterPassword = new ArrayList<String>();

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {
        if(e.getPlayer().getGameMode() == GameMode.CREATIVE) {
            e.getPlayer().sendMessage("You can't use the Terminal while in Creative Mode!");
        }

        Player player = (Player) e.getPlayer();

        skull = new ItemStack(Material.SKULL_ITEM, 1, (short)3);
        ItemMeta skullItemMeta = skull.getItemMeta();

        SkullMeta skullMeta = (SkullMeta) skull.getItemMeta();
        skullMeta.setOwner("Hack");
        skull.setItemMeta(skullMeta);

        skullItemMeta.setDisplayName(ChatColor.DARK_GREEN + "Terminal Block");
        ArrayList<String> lore = new ArrayList<String>();
        lore.add(ChatColor.GRAY + "Right-click on a block to place a Terminal.");
        lore.add(ChatColor.GRAY + "Then, right click the head to open the Terminal.");

        skullItemMeta.setLore(lore);
        skull.setItemMeta(skullItemMeta);

        if(e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if(e.getItem().getType().equals(Material.SKULL_ITEM) && (skullMeta.getOwner().equals("Hack"))) {
                // check if the block can't go there?
                player.sendMessage(ChatColor.GREEN + "> " + ChatColor.DARK_GREEN + "Loading terminal...");
                player.sendMessage("");
                player.sendMessage(ChatColor.GREEN + "> " + ChatColor.DARK_GREEN + "Type 'exit' or 'cancel' at any time to end your current session.");
                player.sendMessage(ChatColor.GREEN + "> " + ChatColor.DARK_GREEN + "Enter your username." + ChatColor.DARK_GRAY + " (By default your Minecraft username)");
                enterUsername.add(player.getUniqueId().toString());
            }
        }
    }
}