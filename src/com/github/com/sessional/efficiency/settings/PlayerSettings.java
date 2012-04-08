/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.com.sessional.efficiency.settings;

import com.github.com.sessional.efficiency.EfficiencyPlugin;
import org.bukkit.entity.Player;

/**
 *
 * @author Andrew
 */
public class PlayerSettings
{
    private ChatSettings chatSettings;
    private WoodcuttingSettings woodcuttingSettings;
    private Player player;
    private EfficiencyPlugin plugin;
    
    public PlayerSettings(EfficiencyPlugin plugin, Player player)
    {
        this.player = player;
        this.plugin = plugin;
        chatSettings = new ChatSettings(plugin, player);
        woodcuttingSettings = new WoodcuttingSettings(player);
    }
    
    public ChatSettings getChatSettings()
    {
        return chatSettings;
    }
    
    public WoodcuttingSettings getWoodcuttingSettings()
    {
        return woodcuttingSettings;
    }
}
