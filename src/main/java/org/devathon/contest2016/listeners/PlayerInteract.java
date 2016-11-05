package org.devathon.contest2016.listeners;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

/**
 * Created by AthenaDev on 11/5/16.
 */
public class PlayerInteract implements Listener {

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {
        if(e.getPlayer().getGameMode() == GameMode.CREATIVE) {
            e.getPlayer().sendMessage("You can't use the Terminal while in Creative Mode!");
        }

        Player player = (Player) e.getPlayer();

        if(e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if(e.getItem().getType().equals(Material.SKULL_ITEM)) {

            }
        }
    }
}