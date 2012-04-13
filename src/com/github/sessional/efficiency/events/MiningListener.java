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
public class MiningListener implements Listener
{
    private EfficiencyPlugin plugin;
    
    public MiningListener(EfficiencyPlugin plugin)
    {
        this.plugin = plugin;
    }
    
    @EventHandler
    public void onBlockBreak(BlockBreakEvent event)
    {
        
    }
    
    public boolean isTypePickaxe(Material material)
    {
        if (material == Material.WOOD_PICKAXE || material == Material.STONE_PICKAXE
                || material == Material.IRON_PICKAXE || material == Material.GOLD_PICKAXE
                || material == Material.DIAMOND_PICKAXE)
            return true;
        return false;
    }
}
