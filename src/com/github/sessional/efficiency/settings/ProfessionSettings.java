/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.sessional.efficiency.settings;

import com.github.sessional.efficiency.EfficiencyPlugin;

/**
 *
 * @author Andrew
 */
public class ProfessionSettings
{
    
    private int level;
    private EfficiencyPlugin plugin;
    
    public ProfessionSettings(EfficiencyPlugin plugin)
    {
        this.plugin = plugin;
    }
    
    public EfficiencyPlugin getPlugin()
    {
        return plugin;
    }
    
    public int getExpertiseLevel()
    {
        return level;
    }
    
    public void setExpertiseLevel(int level)
    {
        this.level = level;
    }
    
    public void incrementExpertiseLevel()
    {
        setExpertiseLevel(getExpertiseLevel() + 1);
    }
}
