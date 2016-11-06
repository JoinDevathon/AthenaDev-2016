package org.devathon.contest2016.listeners;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.devathon.contest2016.DevathonPlugin;
import org.devathon.contest2016.utils.Utils;

import java.util.ArrayList;

/**
 * Created by AthenaDev on 11/5/16.
 */
public class PlayerChat implements Listener {

    public static ArrayList<String> inCurrentSession = new ArrayList<String>();
    public boolean yesOrNoUsername, yesOrNoPassword;

    private DevathonPlugin plugin;

    public PlayerChat(DevathonPlugin instance) {
        plugin = instance;
    }

    @EventHandler
    public void onAsyncPlayerChat(AsyncPlayerChatEvent e) {

        Player player = e.getPlayer();

        // add minecraft commands

        if(PlayerInteract.enterUsername.contains(player.getUniqueId().toString())) {
            if(e.getMessage().equals(Utils.getUsername(player))) {
                e.setCancelled(true);
                player.sendMessage(ChatColor.GREEN + "> " + ChatColor.DARK_GREEN + "Signing into " + player.getName() + "...");

                if(Utils.getPassword(player).equals("none")) {
                    player.sendMessage(ChatColor.GREEN + "> " + ChatColor.DARK_GREEN + "Couldn't find a password for user " + player.getName() + ", please register one using /terminal setPassword <password>.");
                    player.sendMessage(ChatColor.GREEN + "> " + ChatColor.DARK_GREEN + "Current operation cancelled.");
                    player.sendMessage(ChatColor.GREEN + "> _");
                }
                else {
                    player.sendMessage(ChatColor.GREEN + "> " + ChatColor.DARK_GREEN + "Please enter your terminal password.");
                    player.sendMessage(ChatColor.GREEN + "> _");
                    PlayerInteract.enterUsername.remove(player.getUniqueId().toString());
                    PlayerInteract.enterPassword.add(player.getUniqueId().toString());
                }
            }
            else {
                e.setCancelled(true);
                player.sendMessage(ChatColor.GREEN + "> " + ChatColor.DARK_GREEN + "Invalid username!");
                player.sendMessage(ChatColor.GREEN + "> _");
            }
        }

        if(e.getMessage().equalsIgnoreCase("help") && (inCurrentSession.contains(player.getUniqueId().toString()))) {
            player.sendMessage(ChatColor.GREEN + "> " + ChatColor.DARK_GREEN + "Valid commands:");
            player.sendMessage(ChatColor.GREEN + "> " + ChatColor.DARK_GREEN + "gamemode <0-3> | tp <player> | time set <day, night>");
        }

        if(PlayerInteract.enterPassword.contains(player.getUniqueId().toString())) {
            e.setCancelled(true);
            if(e.getMessage().equalsIgnoreCase(Utils.getPassword(player))) {
                PlayerInteract.enterPassword.remove(player.getUniqueId().toString());
                player.sendMessage(ChatColor.GREEN + "> " + ChatColor.DARK_GREEN + "Signed into " + player.getName() + "!");
                player.sendMessage(ChatColor.GREEN + "> " + ChatColor.DARK_GREEN + "Type 'help' to view all commands. Type 'exit' to exit the terminal.");
                player.sendMessage(ChatColor.GREEN + "> _");
                inCurrentSession.add(player.getUniqueId().toString());
            }
            else {
                player.sendMessage(ChatColor.GREEN + "> " + ChatColor.DARK_GREEN + "Invalid password!");
                player.sendMessage(ChatColor.GREEN + "> _");
            }
        }

        if(e.getMessage().equalsIgnoreCase("exit") || (e.getMessage().equalsIgnoreCase("cancel") && (inCurrentSession.contains(player.getUniqueId().toString())))) {
            e.setCancelled(true);
            PlayerInteract.enterUsername.remove(player.getUniqueId().toString());
            PlayerInteract.enterPassword.remove(player.getUniqueId().toString());
            player.sendMessage(ChatColor.GREEN + "> " + ChatColor.DARK_GREEN + "Cancelled current operation.");
            player.sendMessage(ChatColor.GREEN + "> " + ChatColor.DARK_GREEN + "Signing out and exiting terminal...");
            player.sendMessage(ChatColor.GREEN + "> ");
            player.sendMessage(ChatColor.GREEN + "> " + ChatColor.DARK_GREEN + "[Process Completed]");
        }
    }
}