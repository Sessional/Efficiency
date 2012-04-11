/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.sessional.efficiency.chatwindows.options;

import com.github.sessional.efficiency.EfficiencyPlugin;
import com.github.sessional.efficiency.chatwindows.ChatMenu;

/**
 *
 * @author Andrew
 */
public class LinkedMenu extends Option
{
    
    private ChatMenu linkedMenu;
    
    public LinkedMenu(EfficiencyPlugin plugin, String name, int hotkey, ChatMenu linkedMenu)
    {
        super(plugin, name, hotkey);
        this.linkedMenu = linkedMenu;
    }
    
    public ChatMenu getLinkedMenu()
    {
        return linkedMenu;
    }
    
    public void setLinkedMenu(ChatMenu linkedMenu)
    {
        this.linkedMenu = linkedMenu;
    }
}
