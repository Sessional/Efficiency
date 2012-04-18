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
public class BrowseMenu extends ChatMenu
{
    public BrowseMenu(EfficiencyPlugin plugin, ChatMenu parent)
    {
        super(plugin, parent);
        setName("Browse Menu");
    }
    
    public void init()
    {
        setNumOptions(1);
        LinkedMenu diggingMenu = new LinkedMenu(getPlugin(),
                ChatMenu.getNameFromType(MenuTypes.DIGGING_DISPLAY_MENU), 1,
                getPlugin().getChatMenu(ChatMenu.getNameFromType(MenuTypes.DIGGING_DISPLAY_MENU)));
        BackLink backLink = new BackLink(getPlugin(), "Back", 0);
        addOption(1, diggingMenu);
        addOption(0, backLink);
    }
}
