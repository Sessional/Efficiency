/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.com.sessional.efficiency.settings;

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
    
    public PlayerSettings(Player player)
    {
        this.player = player;
        chatSettings = new ChatSettings(player);
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
