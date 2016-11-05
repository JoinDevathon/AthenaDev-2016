package org.devathon.contest2016.listeners;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.devathon.contest2016.DevathonPlugin;
import org.devathon.contest2016.commands.TerminalCommand;
import org.devathon.contest2016.utils.Utils;

import java.util.ArrayList;

/**
 * Created by AthenaDev on 11/5/16.
 */
public class PlayerChat implements Listener {

    public static ArrayList<String> terminateSession = new ArrayList<String>();
    public boolean yesOrNo;

    private DevathonPlugin plugin;

    public PlayerChat(DevathonPlugin instance) {
        plugin = instance;
    }

    @EventHandler
    public void onAsyncPlayerChat(AsyncPlayerChatEvent e) {

        Player player = e.getPlayer();

        // add minecraft command (and maybe git, for fun?)

        if(PlayerInteract.enterUsername.contains(player.getUniqueId().toString())) {
            if(e.getMessage().equalsIgnoreCase(Utils.getUsername(player))) {
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
            }
        }

        if(PlayerInteract.enterPassword.contains(player.getUniqueId().toString())) {
            if(e.getMessage().equalsIgnoreCase(Utils.getPassword(player))) {

            }
            else {
                e.setCancelled(true);
                player.sendMessage(ChatColor.GREEN + "> " + ChatColor.DARK_GREEN + "Invalid password!");
            }
        }

        if(TerminalCommand.setUsername.containsKey(player.getUniqueId().toString())) {
            e.setCancelled(true);
            TerminalCommand.setUsername.put(player.getUniqueId().toString(), e.getMessage());
            player.sendMessage(ChatColor.GREEN + "> " + ChatColor.GREEN + "Are you sure you want to change your terminal username to " + e.getMessage() + "? (y or n)");

            if(yesOrNo == true) {
                Utils.setUsername(player, e.getMessage());
                player.sendMessage(ChatColor.GREEN + "> " + ChatColor.GREEN + "Successfully changed username to " + e.getMessage() + ".");
            }
            else {
                player.sendMessage(ChatColor.GREEN + "> " + ChatColor.DARK_GREEN + "Cancelled current operation.");
                TerminalCommand.setUsername.remove(player.getUniqueId().toString(), e.getMessage());
            }
        }

        if(e.getMessage().equalsIgnoreCase("y") || (e.getMessage().equalsIgnoreCase("yes") && (TerminalCommand.setUsername.containsKey(player.getUniqueId().toString())))) {
            yesOrNo = true;
        }

        /*
        if(e.getMessage().equalsIgnoreCase("exit") || (e.getMessage().equalsIgnoreCase("cancel") && (PlayerInteract.enterUsername.contains(player.getUniqueId().toString())) || (PlayerInteract.enterPassword.contains(player.getUniqueId().toString())))) {
            e.setCancelled(true);
            PlayerInteract.enterUsername.remove(player.getUniqueId().toString());
            PlayerInteract.enterPassword.remove(player.getUniqueId().toString());
            player.sendMessage(ChatColor.GREEN + "> " + ChatColor.DARK_GREEN + "Cancelled current operation.");
        }
        */

        if(e.getMessage().equalsIgnoreCase("y") || (e.getMessage().equalsIgnoreCase("yes"))) {
            e.setCancelled(true);
            player.sendMessage(ChatColor.GREEN + "> " + ChatColor.DARK_GREEN + "Completed operation.");
        }

        if(e.getMessage().equalsIgnoreCase("n") || (e.getMessage().equalsIgnoreCase("no"))) {
            e.setCancelled(true);
            player.sendMessage(ChatColor.GREEN + "> " + ChatColor.DARK_GREEN + "Current operation cancelled.");
        }
    }
}