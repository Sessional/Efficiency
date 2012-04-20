/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.sessional.efficiency.events;

import com.github.sessional.efficiency.EfficiencyPlugin;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 *
 * @author Andrew
 */
public class PlayerSetupListener implements Listener
{
    private EfficiencyPlugin plugin;
    
    public PlayerSetupListener(EfficiencyPlugin plugin)
    {
        this.plugin = plugin;
    }
    
    @EventHandler
    public void onPlayerLogin(PlayerJoinEvent event)
    {
        System.out.println("Player login: " + event.getPlayer().getName());
        plugin.checkAndCreateDirectories(event.getPlayer());
        plugin.createPlayerSettings(event.getPlayer());
    }
    
    @EventHandler
    public void onPlayerLogout(PlayerQuitEvent event)
    {
        System.out.println("Player logout: " + event.getPlayer().getName());
        plugin.savePlayerSettings(event.getPlayer());
        plugin.deletePlayerSettings(event.getPlayer());
    }
}
