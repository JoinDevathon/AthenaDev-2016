package org.devathon.contest2016.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.devathon.contest2016.DevathonPlugin;
import org.devathon.contest2016.listeners.PlayerInteract;

import java.util.HashMap;

/**
 * Created by AthenaDev on 11/5/16.
 */
public class TerminalCommand implements CommandExecutor {

    public static HashMap<String, String> setUsername = new HashMap<String, String>();

    private DevathonPlugin plugin;

    public TerminalCommand(DevathonPlugin instance) {
        plugin = instance;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage("Console cannot use the Terminal!");
            return true;
        }

        Player player = (Player) sender;

        if(args.length == 0) {
            if (cmd.getName().equalsIgnoreCase("terminal")) {
                player.sendMessage("");
                player.sendMessage(ChatColor.GREEN + "Terminal " + ChatColor.DARK_GREEN + "v1.0.0 by AthenaDev " + ChatColor.GRAY + "- For Devathon");
                player.sendMessage("");
                player.sendMessage(ChatColor.GREEN + "> " + ChatColor.DARK_GREEN + "/terminal give <player> " + ChatColor.GRAY + "Gives the specified player a Terminal Block.");
                player.sendMessage(ChatColor.GREEN + "> " + ChatColor.DARK_GREEN + "/terminal setUsername <username> " + ChatColor.GRAY + "Sets terminal username.");
                player.sendMessage(ChatColor.GREEN + "> " + ChatColor.DARK_GREEN + "/terminal setPassword <password> " + ChatColor.GRAY + "Sets terminal password. (If there is no current password)");
                player.sendMessage(ChatColor.GREEN + "> " + ChatColor.DARK_GREEN + "/terminal resetPassword <oldPassword> <newPassword> <confirmNewPassword> " + ChatColor.GRAY + "Resets current terminal password.");
                player.sendMessage(ChatColor.GREEN + "> " + ChatColor.DARK_GREEN + "/terminal reload " + ChatColor.GRAY + "Reloads the user data file & configuration file.");
                player.sendMessage("");
            }
        }

        if(args.length == 1) {
            Player target = Bukkit.getPlayerExact(args[1]);

            if(target == null) {
                player.sendMessage(ChatColor.GREEN + "> " + ChatColor.DARK_GREEN + "Couldn't find the specified player.");
            }

            if(args[0].equalsIgnoreCase("give") && (args[1].equals(target) && (player.hasPermission("terminal.give")))) {
                player.sendMessage(ChatColor.GREEN + "> " + ChatColor.DARK_GREEN + "Giving a terminal to " + target.getName() + "...");
                player.sendMessage(ChatColor.GREEN + "> " + ChatColor.DARK_GREEN + "Success!");
                target.sendMessage(ChatColor.GREEN + "> " + ChatColor.DARK_GREEN + "You were given a terminal block by " + player.getName());
                target.getInventory().addItem(PlayerInteract.skull);
            }
            else if(!player.hasPermission("terminal.give")) {
                player.sendMessage(plugin.config.getString("noPermission", ChatColor.RED + "You do not have permission to use this command!"));
            }
            else if(args[0].equalsIgnoreCase("reload") && (player.hasPermission("terminal.reload"))) {
                plugin.reloadData();
                plugin.reloadConfig();
                player.sendMessage(ChatColor.GREEN + "> " + ChatColor.DARK_GREEN + "Files successfully reloaded.");
            }
            else if(!player.hasPermission("terminal.reload")) {
                player.sendMessage(plugin.config.getString("noPermission", ChatColor.RED + "You do not have permission to use this command!"));
            }
            else if(args[0].equalsIgnoreCase("setUsername")) {
                player.sendMessage(ChatColor.GREEN + "> " + ChatColor.DARK_GREEN + "Please enter what you would like your new terminal username to be. Type 'cancel' or 'exit' at any time to stop.");
            }
        }
        return true;
    }
}