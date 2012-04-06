/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.com.sessional.efficiency.events;

import com.github.com.sessional.efficiency.EfficiencyPlugin;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;

/**
 *
 * @author Andrew
 */
public class ChatMenuListener implements Listener
{
    private EfficiencyPlugin plugin;
    
    public ChatMenuListener(EfficiencyPlugin plugin)
    {
        this.plugin = plugin;
    }
    
    @EventHandler
    public void onChatEnter(PlayerChatEvent event)
    {
        if (plugin.getPlayerSettings(event.getPlayer()).getChatSettings().isPlayerInWindow())
        {
            event.getPlayer().sendMessage("Player is in a window.");
        }
        else
        {
            
            event.getPlayer().sendMessage("Player is NOT in a window.");
        }
    }
}
