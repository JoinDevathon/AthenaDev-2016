package org.devathon.contest2016.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.devathon.contest2016.DevathonPlugin;
import org.devathon.contest2016.utils.Utils;

/**
 * Created by AthenaDev on 11/5/16.
 */
public class TerminalCommand implements CommandExecutor {

    public DevathonPlugin plugin;

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
                player.sendMessage(ChatColor.GREEN + "> " + ChatColor.DARK_GREEN + "/terminal reload " + ChatColor.GRAY + "Reloads the user data file & configuration file.");
                player.sendMessage("");
            }
        }

        if(args.length == 2) {
            Player target = Bukkit.getPlayerExact(args[1]);

            if((target == null) && (args[0].equalsIgnoreCase("give"))) {
                player.sendMessage(ChatColor.GREEN + "> " + ChatColor.DARK_GREEN + "Couldn't find the specified player.");
            }

            if(args[0].equalsIgnoreCase("give") && (args[1].equals(target.getName()) && (player.hasPermission("terminal.give")))) {
                player.sendMessage(ChatColor.GREEN + "> " + ChatColor.DARK_GREEN + "Giving a terminal to " + target.getName() + "...");
                player.sendMessage(ChatColor.GREEN + "> " + ChatColor.DARK_GREEN + "Success!");
                target.sendMessage(ChatColor.GREEN + "> " + ChatColor.DARK_GREEN + "You were given a terminal block by " + player.getName());
                target.getInventory().addItem(plugin.skull);
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
                StringBuilder username = new StringBuilder();
                for (int i = 1; i < args.length; i++) username.append(args[i] + "");
                String newUsername = username.toString();

                player.sendMessage(ChatColor.GREEN + "> " + ChatColor.DARK_GREEN + "Successfully set terminal login username to " + newUsername + "!");
                player.sendMessage(ChatColor.GREEN + "> _");
                Utils.setUsername(player, newUsername);
            }
            else if(args[0].equalsIgnoreCase("setPassword")) {
                StringBuilder password = new StringBuilder();
                for (int i = 1; i < args.length; i++) password.append(args[i] + "");
                String newPassword = password.toString();

                player.sendMessage(ChatColor.GREEN + "> " + ChatColor.DARK_GREEN + "Successfully set password to " + newPassword + "!");
                player.sendMessage(ChatColor.GREEN + "> _");
                Utils.setPassword(player, newPassword);
            }
            else {
                player.sendMessage(ChatColor.GREEN + "> " + ChatColor.DARK_GREEN + "Something went wrong! Check your arguments.");
            }
        }
        return true;
    }
}