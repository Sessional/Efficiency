/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.sessional.efficiency.chatwindows;

import com.github.sessional.efficiency.EfficiencyPlugin;
import com.github.sessional.efficiency.chatwindows.options.LinkedMenu;

/**
 *
 * @author Andrew
 */
public class ExpertiseMenu extends ChatMenu
{
    public ExpertiseMenu(EfficiencyPlugin plugin, ChatMenu parent)
    {
        super(plugin, parent);
        setName("Expertise Menu");
    }
    
    public void init()
    {
        setNumOptions(0);
        
        LinkedMenu backLink = new LinkedMenu(getPlugin(), "Back", 0, getPlugin().getChatMenu("MainMenu"));
        addOption(0, backLink);
    }
}
