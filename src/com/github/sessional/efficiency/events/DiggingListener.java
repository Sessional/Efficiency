/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.sessional.efficiency.events;

import com.github.sessional.efficiency.EfficiencyPlugin;
import com.github.sessional.efficiency.Tree.DiggingTree;
import com.github.sessional.efficiency.Tree.DiggingTree.DiggingTechniques;
import com.github.sessional.efficiency.Tree.Talent.Talent;
import com.github.sessional.efficiency.settings.DiggingSettings;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
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
        if (event.getBlock().getType() != Material.DIRT
                && event.getBlock().getType() != Material.SAND
                && event.getBlock().getType() != Material.GRAVEL
                && event.getBlock().getType() != Material.CLAY
                && event.getBlock().getType() != Material.SNOW
                && event.getBlock().getType() != Material.SOUL_SAND)
        {
            return;
        }
        if (isTypeSpade(event.getPlayer().getItemInHand().getType()))
        {
            Player p = event.getPlayer();
            DiggingSettings digSettings = plugin.getPlayerSettings(p).getDiggingSettings();
            if (digSettings.hasTalent(plugin.getTalent(DiggingTree.getNameFromTechnique(DiggingTechniques.MASTER_DIGGING))))
            {
                if (activates(p, plugin.getTalent(DiggingTree.getNameFromTechnique(DiggingTechniques.MASTER_DIGGING))))
                {
                    stopDurability(p);
                    p.sendMessage("Apprentice digging has prevented durability damage to your spade!");
                    plugin.getPlayerSettings(p).getDiggingSettings().incrementExpertiseLevel();
                    p.sendMessage("Your expertise level in digging is now: " + plugin.getPlayerSettings(p).getDiggingSettings().getExpertiseLevel());
                }
            } else if (digSettings.hasTalent(plugin.getTalent(DiggingTree.getNameFromTechnique(DiggingTechniques.EXPERT_DIGGING))))
            {
                if (activates(p, plugin.getTalent(DiggingTree.getNameFromTechnique(DiggingTechniques.EXPERT_DIGGING))))
                {
                    stopDurability(p);
                    p.sendMessage("Apprentice digging has prevented durability damage to your spade!");
                    plugin.getPlayerSettings(p).getDiggingSettings().incrementExpertiseLevel();
                    p.sendMessage("Your expertise level in digging is now: " + plugin.getPlayerSettings(p).getDiggingSettings().getExpertiseLevel());
                }
            } else if (digSettings.hasTalent(plugin.getTalent(DiggingTree.getNameFromTechnique(DiggingTechniques.JOURNEYMAN_DIGGING))))
            {
                if (activates(p, plugin.getTalent(DiggingTree.getNameFromTechnique(DiggingTechniques.JOURNEYMAN_DIGGING))))
                {
                    stopDurability(p);
                    p.sendMessage("Apprentice digging has prevented durability damage to your spade!");
                    plugin.getPlayerSettings(p).getDiggingSettings().incrementExpertiseLevel();
                    p.sendMessage("Your expertise level in digging is now: " + plugin.getPlayerSettings(p).getDiggingSettings().getExpertiseLevel());
                }
            } else if (digSettings.hasTalent(plugin.getTalent(DiggingTree.getNameFromTechnique(DiggingTechniques.APPRENTICE_DIGGING))))
            {
                if (activates(p, plugin.getTalent(DiggingTree.getNameFromTechnique(DiggingTechniques.APPRENTICE_DIGGING))))
                {
                    stopDurability(p);
                    p.sendMessage("Apprentice digging has prevented durability damage to your spade!");
                    plugin.getPlayerSettings(p).getDiggingSettings().incrementExpertiseLevel();
                    p.sendMessage("Your expertise level in digging is now: " + plugin.getPlayerSettings(p).getDiggingSettings().getExpertiseLevel());
                }
            }

            if (event.getBlock().getType() == Material.GRAVEL)
            {
                if (digSettings.hasTalent(plugin.getTalent(DiggingTree.getNameFromTechnique(DiggingTechniques.IMPROVED_FLINT))))
                {
                    if (activates(p, plugin.getTalent(DiggingTree.getNameFromTechnique(DiggingTechniques.IMPROVED_FLINT))))
                    {
                        Location loc = event.getBlock().getLocation();
                        ItemStack flint = new ItemStack(Material.FLINT, 1);
                        p.sendMessage("Improved flint finding has activated and you have found additional flint!");
                        event.getBlock().getWorld().dropItemNaturally(loc, flint);
                    }
                }
            }
        }
    }

    public boolean activates(Player player, Talent talent)
    {
        Talent t2 = plugin.getTalent(talent.getName());
        int rank = plugin.getPlayerSettings(player).getDiggingSettings().getRankForTalent(talent);
        int ranNum = getRandom();
        if (ranNum < t2.getChanceAtRank(rank))
        {
            return true;
        }
        return false;
    }

    public int getRandom()
    {
        double rand = Math.random();
        int randVal = (int) (rand * 100) + 1;
        return randVal;
    }

    public boolean isTypeSpade(Material material)
    {
        if (material == Material.WOOD_SPADE || material == Material.STONE_SPADE
                || material == Material.IRON_SPADE || material == Material.GOLD_SPADE
                || material == Material.DIAMOND_SPADE)
        {
            return true;
        }
        return false;
    }

    public void stopDurability(Player player)
    {
        ItemStack item = player.getItemInHand();
        item.setDurability((short) Math.max(item.getDurability() - 1, 0));
        player.updateInventory();
    }
}
