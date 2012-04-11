/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.sessional.efficiency.chatwindows;

import com.github.sessional.efficiency.EfficiencyPlugin;
import com.github.sessional.efficiency.chatwindows.options.BackLink;

/**
 *
 * @author Andrew
 */
public class BrowseMenu extends ChatMenu
{
    public BrowseMenu(EfficiencyPlugin plugin, ChatMenu parent)
    {
        super(plugin, parent);
        setName("Browse Menu");
    }
    
    public void init()
    {
        setNumOptions(0);
        
        BackLink backLink = new BackLink(getPlugin(), "Back", 0);
        addOption(0, backLink);
    }
}
