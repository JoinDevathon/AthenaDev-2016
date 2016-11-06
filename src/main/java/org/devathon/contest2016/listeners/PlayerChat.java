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
            e.setCancelled(true);
            player.sendMessage(ChatColor.GREEN + "> " + ChatColor.DARK_GREEN + "Valid commands:");
            player.sendMessage(ChatColor.GREEN + "> " + ChatColor.DARK_GREEN + "gamemode <0-3> | tps | time set <day, night>");
            player.sendMessage(ChatColor.GREEN + "> " + ChatColor.DARK_GREEN + "say <message>  | plugins/pl | stop");
        }

        if(inCurrentSession.contains(player.getUniqueId().toString())) {
            e.setCancelled(true);
            if(e.getMessage().equalsIgnoreCase("gamemode 0")) {
                player.sendMessage(ChatColor.GREEN + "> " + ChatColor.DARK_GREEN + "Attempting to change gamemode to 0...");
                player.performCommand("gamemode 0");
                inCurrentSession.remove(player.getUniqueId().toString());
                plugin.logger.info(player.getName() + " executed the command /gamemode 0 via the terminal.");
            }
            if(e.getMessage().equalsIgnoreCase("gamemode 1")) {
                player.sendMessage(ChatColor.GREEN + "> " + ChatColor.DARK_GREEN + "Attempting to change gamemode to 1...");
                player.performCommand("gamemode 1");
                inCurrentSession.remove(player.getUniqueId().toString());
                plugin.logger.info(player.getName() + " executed the command /gamemode 1 via the terminal.");
            }
            if(e.getMessage().equalsIgnoreCase("gamemode 2")) {
                player.sendMessage(ChatColor.GREEN + "> " + ChatColor.DARK_GREEN + "Attempting to change gamemode to 2...");
                player.performCommand("gamemode 2");
                inCurrentSession.remove(player.getUniqueId().toString());
                plugin.logger.info(player.getName() + " executed the command /gamemode 2 via the terminal.");
            }
            if(e.getMessage().equalsIgnoreCase("gamemode 3")) {
                player.sendMessage(ChatColor.GREEN + "> " + ChatColor.DARK_GREEN + "Attempting to change gamemode to 3...");
                player.performCommand("gamemode 3");
                inCurrentSession.remove(player.getUniqueId().toString());
                plugin.logger.info(player.getName() + " executed the command /gamemode 3 via the terminal.");
            }
            if(e.getMessage().equalsIgnoreCase("tps")) {
                player.sendMessage(ChatColor.GREEN + "> " + ChatColor.DARK_GREEN + "Attempting to load TPS...");
                player.performCommand("tps");
                inCurrentSession.remove(player.getUniqueId().toString());
                plugin.logger.info(player.getName() + " executed the command /tps via the terminal.");
            }
            if(e.getMessage().equalsIgnoreCase("time set day")) {
                player.sendMessage(ChatColor.GREEN + "> " + ChatColor.DARK_GREEN + "Attempting to set time to day...");
                player.performCommand("time set day");
                inCurrentSession.remove(player.getUniqueId().toString());
                plugin.logger.info(player.getName() + " executed the command /time set day via the terminal.");
            }
            if(e.getMessage().equalsIgnoreCase("time set night")) {
                player.sendMessage(ChatColor.GREEN + "> " + ChatColor.DARK_GREEN + "Attempting to set time to night...");
                player.performCommand("time set night");
                inCurrentSession.remove(player.getUniqueId().toString());
                plugin.logger.info(player.getName() + " executed the command /time set night via the terminal.");
            }
            if(e.getMessage().contains("say")) {
                player.sendMessage(ChatColor.GREEN + "> " + ChatColor.DARK_GREEN + "Attempting to execute command 'say' with message " + e.getMessage().replaceAll("say ", ""));
                player.performCommand("say " + e.getMessage().replaceAll("say ", ""));
                inCurrentSession.remove(player.getUniqueId().toString());
                plugin.logger.info(player.getName() + " executed the command /say via the terminal.");
            }
            if(e.getMessage().equalsIgnoreCase("plugins") || (e.getMessage().equalsIgnoreCase("pl"))) {
                player.sendMessage(ChatColor.GREEN + "> " + ChatColor.DARK_GREEN + "Attempting to view plugin list...");
                player.performCommand("plugins");
                inCurrentSession.remove(player.getUniqueId().toString());
                plugin.logger.info(player.getName() + " executed the command /plugins via the terminal.");
            }
            if(e.getMessage().equalsIgnoreCase("stop")) {
                player.sendMessage(ChatColor.GREEN + "> " + ChatColor.DARK_GREEN + "Attempting to stop server...");
                player.performCommand("stop");
                inCurrentSession.remove(player.getUniqueId().toString());
                plugin.logger.info(player.getName() + " executed the command /stop via the terminal.");
            }
        }

        if(PlayerInteract.enterPassword.contains(player.getUniqueId().toString())) {
            e.setCancelled(true);
            if(e.getMessage().equalsIgnoreCase(Utils.getPassword(player))) {
                PlayerInteract.enterPassword.remove(player.getUniqueId().toString());
                player.sendMessage(ChatColor.GREEN + "> " + ChatColor.DARK_GREEN + "Signed into " + Utils.getUsername(player) + "!");
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