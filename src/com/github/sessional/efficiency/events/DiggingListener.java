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
import org.bukkit.inventory.ItemStack;

/**
 *
 * @author Andrew
 */
public class DiggingListener implements Listener
{

    private EfficiencyPlugin plugin;

    public DiggingListener(EfficiencyPlugin plugin)
    {
        this.plugin = plugin;
    }

    @EventHandler
    public void onBlockDamage(BlockBreakEvent event)
    {
        if (isTypeSpade(event.getPlayer().getItemInHand().getType()))
        {
            ItemStack is = event.getPlayer().getItemInHand();
            is.setDurability((short) Math.max(is.getDurability() - 1, 0));
            event.getPlayer().updateInventory();
        }
    }
    
    public boolean isTypeSpade(Material material)
    {
        if (material == Material.WOOD_SPADE || material == Material.STONE_SPADE
                || material == Material.IRON_SPADE || material == Material.GOLD_SPADE
                || material == Material.DIAMOND_SPADE)
            return true;
        return false;
    }
}
