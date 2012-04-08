/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.com.sessional.efficiency.events;

import com.github.com.sessional.efficiency.EfficiencyPlugin;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

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
        plugin.createPlayerSettings(event.getPlayer());
    }
}
