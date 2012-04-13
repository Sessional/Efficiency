/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.sessional.efficiency.chatwindows;

import com.github.sessional.efficiency.EfficiencyPlugin;
import com.github.sessional.efficiency.chatwindows.options.BackLink;
import com.github.sessional.efficiency.chatwindows.options.LinkedMenu;

/**
 *
 * @author Andrew
 */
public class DiggingDisplayMenu extends ChatMenu
{
    
    public DiggingDisplayMenu(EfficiencyPlugin plugin, ChatMenu parent)
    {
        super(plugin, parent);
        setName("Digging Tree Browse Menu");
    }
    
    public void init()
    {
        setNumOptions(1);
        LinkedMenu digging1Menu = new LinkedMenu(getPlugin(), "Digging Talent 1", 1, getPlugin().getChatMenu("Digging1TalentMenu"));
        BackLink backLink = new BackLink(getPlugin(), "Back", 0);
        addOption(0, backLink);
        addOption(1, digging1Menu);
    }
}
