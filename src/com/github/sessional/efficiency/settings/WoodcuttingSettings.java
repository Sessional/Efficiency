/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.sessional.efficiency.settings;

import com.github.sessional.efficiency.EfficiencyPlugin;
import org.bukkit.entity.Player;

/**
 *
 * @author Andrew
 */
public class WoodcuttingSettings extends ProfessionSettings
{
    private Player player;
    
    public WoodcuttingSettings(EfficiencyPlugin plugin, Player player)
    {
        super(plugin);
        this.player = player;
    }
}
