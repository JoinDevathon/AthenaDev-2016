package org.devathon.contest2016.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

/**
 * Created by AthenaDev on 11/5/16.
 */
public class PlayerChat implements Listener {

    @EventHandler
    public void onAsyncPlayerChat(AsyncPlayerChatEvent e) {

        Player player = (Player) e.getPlayer();



    }
}