/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.sessional.efficiency.settings;

import com.github.sessional.efficiency.Tree.Talent.Talent;
import java.util.HashMap;
import org.bukkit.entity.Player;

/**
 *
 * @author Andrew
 */
public class DiggingSettings extends ProfessionSettings
{
    private Player player;
    private HashMap<String, Integer> talents;
    
    public DiggingSettings(Player player)
    {
        this.player = player;
        talents = new HashMap<String, Integer>();
    }
    
    public int getRankForTalent(Talent talent)
    {
        if (talents.containsKey(talent.getName()))
        {
            return talents.get(talent.getName());
        }
        else
        {
            return 0;
        }
    }
    
    public boolean setRankForTalent(Talent talent, int rank)
    {
        if (rank <= talent.getRanks())
        {
            talents.put(talent.getName(), rank);
            return true;
        }
        return false;
    }
    
    public boolean hasTalent(Talent talent)
    {
        if (talents.containsKey(talent.getName()))
        {
            if (talents.get(talent.getName()) > 0)
            {
                return true;
            }
        }
        return false;
    }

    public boolean increaseRankForTalent(Talent talent)
    {
        return setRankForTalent(talent, getRankForTalent(talent) + 1);
    }
}
