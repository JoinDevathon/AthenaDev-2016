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

        Player player = (Player) e.getPlayer();

        // add minecraft command (and maybe git, for fun?)

        if(PlayerInteract.enterUsername.contains(player.getUniqueId().toString())) {
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

        if(e.getMessage().equalsIgnoreCase("exit") || (e.getMessage().equalsIgnoreCase("cancel") && (PlayerInteract.enterUsername.contains(player.getUniqueId().toString())) || (PlayerInteract.enterPassword.contains(player.getUniqueId().toString())))) {
            PlayerInteract.enterUsername.remove(player.getUniqueId().toString());
            PlayerInteract.enterPassword.remove(player.getUniqueId().toString());
            player.sendMessage(ChatColor.GREEN + "> " + ChatColor.DARK_GREEN + "Cancelled current operation.");
        }

        if(e.getMessage().equalsIgnoreCase("y") || (e.getMessage().equalsIgnoreCase("yes"))) {
            player.sendMessage(ChatColor.GREEN + "> " + ChatColor.DARK_GREEN + "Completed operation.");
        }

        if(e.getMessage().equalsIgnoreCase("n") || (e.getMessage().equalsIgnoreCase("no"))) {
            player.sendMessage(ChatColor.GREEN + "> " + ChatColor.DARK_GREEN + "Current operation cancelled.");
        }
    }
}