/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.sessional.efficiency.events;

import com.github.sessional.efficiency.EfficiencyPlugin;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

/**
 *
 * @author Andrew
 */
public class WoodcuttingListener implements Listener
{
    private EfficiencyPlugin plugin;
    
    public WoodcuttingListener(EfficiencyPlugin plugin)
    {
        this.plugin = plugin;
    }
    
    @EventHandler
    public void onBlockBreak(BlockBreakEvent event)
    {
        
    }
    
    public boolean isTypeAxe(Material material)
    {
        if (material == Material.WOOD_AXE || material == Material.STONE_AXE
                || material == Material.IRON_AXE || material == Material.GOLD_AXE
                || material == Material.DIAMOND_AXE)
            return true;
        return false;
    }
}
