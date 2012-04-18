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
        
        LinkedMenu expertiseLink = new LinkedMenu(getPlugin(),
                ChatMenu.getNameFromType(MenuTypes.EXPERTISE_MENU),
                1, getPlugin().getChatMenu(ChatMenu.getNameFromType(MenuTypes.EXPERTISE_MENU)));
        LinkedMenu browseLink = new LinkedMenu(getPlugin(),
                ChatMenu.getNameFromType(MenuTypes.BROWSE_MENU),
                2, getPlugin().getChatMenu(ChatMenu.getNameFromType(MenuTypes.BROWSE_MENU)));
        LinkedMenu techniqueLink = new LinkedMenu(getPlugin(),
                ChatMenu.getNameFromType(MenuTypes.TECHNIQUE_MENU),
                3, getPlugin().getChatMenu(ChatMenu.getNameFromType(MenuTypes.TECHNIQUE_MENU)));
        ExitLink exitLink = new ExitLink(getPlugin(), "Exit", 0);
        addOption(1, expertiseLink);
        addOption(2, browseLink);
        addOption(3, techniqueLink);
        addOption(0, exitLink);
    }
}
