/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.sessional.efficiency.settings;

import com.github.sessional.efficiency.EfficiencyPlugin;
import com.github.sessional.efficiency.Tree.Talent.DiggingTalent;
import com.github.sessional.efficiency.Tree.Talent.Talent;
import org.bukkit.entity.Player;

/**
 *
 * @author Andrew
 */
public class PlayerSettings
{
    private ChatSettings chatSettings;
    private WoodcuttingSettings woodcuttingSettings;
    private DiggingSettings diggingSettings;
    private Player player;
    private EfficiencyPlugin plugin;
    private int freeLevels;
    
    public PlayerSettings(EfficiencyPlugin plugin, Player player)
    {
        this.player = player;
        this.plugin = plugin;
        chatSettings = new ChatSettings(plugin, player);
        woodcuttingSettings = new WoodcuttingSettings(player);
        diggingSettings = new DiggingSettings(player);
        freeLevels = 0;
    }
    
    public int getFreeLevels()
    {
        return freeLevels;
    }
    
    public void setFreeLevels(int levels)
    {
        freeLevels = levels;
    }
    
    public void incrementFreeLevels()
    {
        setFreeLevels(getFreeLevels() + 1);
    }
    
    public ChatSettings getChatSettings()
    {
        return chatSettings;
    }
    
    public WoodcuttingSettings getWoodcuttingSettings()
    {
        return woodcuttingSettings;
    }
    
    public DiggingSettings getDiggingSettings()
    {
        return diggingSettings;
    }
    
    public boolean learnTalent(Talent talent)
    {
        if (talent instanceof DiggingTalent)
        {
            if (getDiggingSettings().hasTalent(talent))
            {
                return getDiggingSettings().increaseRankForTalent(talent);
            }
            else
            {
                return getDiggingSettings().setRankForTalent(talent, 1);
            }
        }
        return false;
    }
    
    public ProfessionSettings getProfessionSettings(Talent talent)
    {
        if (talent instanceof DiggingTalent)
        {
            return getDiggingSettings();
        }
        return null;
    }
    
    public boolean hasTalent(Talent talent)
    {
        if (talent instanceof DiggingTalent)
        {
            if (getDiggingSettings().hasTalent(talent))
                return true;
        }
        return false;
    }
}
