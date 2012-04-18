/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.sessional.efficiency.Tree.Talent;

import com.github.sessional.efficiency.EfficiencyPlugin;
import org.bukkit.entity.Player;

/**
 *
 * @author Andrew
 */
public abstract class Talent
{

    private EfficiencyPlugin plugin;
    private String name;
    private String description;
    private Talent requirement;
    private int requiredExpertise;
    private int ranks;
    private int[] chances;

    public Talent(EfficiencyPlugin plugin, String name, String description, int ranks)
    {
        this.plugin = plugin;
        this.name = name;
        this.description = description;
        this.ranks = ranks;
        chances = new int[ranks + 1];
    }

    public String getName()
    {
        return name;
    }

    public void setRankChance(int rank, int chance)
    {
        chances[rank] = chance;
    }

    public String getDescription()
    {
        return description;
    }

    public int getRanks()
    {
        return ranks;
    }

    public int getChanceAtRank(int rank)
    {
        return getChances()[rank];
    }

    public int[] getChances()
    {
        return chances;
    }

    public Talent getRequirement()
    {
        return requirement;
    }

    public void setRequirement(Talent requirement)
    {
        this.requirement = requirement;
    }

    public void setRequiredLevel(int level)
    {
        this.requiredExpertise = level;
    }

    public int getRequiredLevel()
    {
        return requiredExpertise;
    }

    public boolean canLearn(Player player)
    {
        if (this.getRequirement() != null)
        {
            if (plugin.getPlayerSettings(player).hasTalent(getRequirement()))
            {
                if (plugin.getPlayerSettings(player).getProfessionSettings(this).getExpertiseLevel() >= this.getRequiredLevel())
                {
                    return true;
                }
            }
        } else
        {
            if (plugin.getPlayerSettings(player).getProfessionSettings(this).getExpertiseLevel() >= this.getRequiredLevel())
            {
                return true;
            }
        }
        return false;
    }

    public boolean hasTalent(Player player)
    {
        if (plugin.getPlayerSettings(player).hasTalent(this))
        {
            return true;
        }
        return false;
    }
}
