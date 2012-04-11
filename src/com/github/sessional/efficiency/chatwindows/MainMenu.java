/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.sessional.efficiency.chatwindows;

import com.github.sessional.efficiency.EfficiencyPlugin;
import com.github.sessional.efficiency.chatwindows.options.ExitLink;
import com.github.sessional.efficiency.chatwindows.options.LinkedMenu;

/**
 *
 * @author Andrew
 */
public class MainMenu extends ChatMenu
{
    public MainMenu(EfficiencyPlugin plugin, ChatMenu parent)
    {
        super(plugin, parent);
        setName("Main Menu");
    }
    
    public void init()
    {
        setNumOptions(4);
        
        LinkedMenu expertiseLink = new LinkedMenu(getPlugin(), "View Expertise", 1, getPlugin().getChatMenu("ExpertiseMenu"));
        LinkedMenu browseLink = new LinkedMenu(getPlugin(), "Browse Techniques", 2, getPlugin().getChatMenu("BrowseMenu"));
        LinkedMenu techniqueLink = new LinkedMenu(getPlugin(), "Learned Techniques", 3, getPlugin().getChatMenu("TechniqueMenu"));
        ExitLink exitLink = new ExitLink(getPlugin(), "Exit", 0);
        addOption(1, expertiseLink);
        addOption(2, browseLink);
        addOption(3, techniqueLink);
        addOption(0, exitLink);
    }
}
