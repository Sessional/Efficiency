/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.sessional.efficiency.chatwindows.options;

import com.github.sessional.efficiency.EfficiencyPlugin;

/**
 *
 * @author Andrew
 */
public abstract class Option
{
    private String name;
    private int hotkey;
    private EfficiencyPlugin plugin;
    
    
    public Option(EfficiencyPlugin plugin, String name, int hotkey)
    {
        this.plugin = plugin;
        this.name = name;
        this.hotkey = hotkey;
    }
    
    public EfficiencyPlugin getPlugin()
    {
        return plugin;
    }
    
    public void setPlugin(EfficiencyPlugin plugin)
    {
        
    }
    
    public String getName()
    {
        return name;
    }
    
    public int getHotkey()
    {
        return hotkey;
    }
    
    public void setName(String name)
    {
        this.name = name;
    }
    
    public void setHotkey(int hotkey)
    {
        this.hotkey = hotkey;
    }
}
