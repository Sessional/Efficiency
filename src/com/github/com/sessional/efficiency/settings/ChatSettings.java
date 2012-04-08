/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.com.sessional.efficiency.settings;

import com.github.com.sessional.efficiency.EfficiencyPlugin;
import com.github.com.sessional.efficiency.chatwindows.ChatWindow;
import com.github.com.sessional.efficiency.chatwindows.Option;
import java.util.List;
import org.bukkit.entity.Player;

/**
 *
 * @author Andrew
 */
public class ChatSettings
{
    private ChatMenu currentMenu;
    private Player player;
    private EfficiencyPlugin plugin;
    
    public ChatSettings(EfficiencyPlugin plugin, Player player)
    {
        this.plugin = plugin; 
        currentMenu = ChatMenu.None;
        this.player = player;
    }
    
    public enum ChatMenu
    {
        None,
        Root,
        Expertise,
        LevelUp,
        Proficiency
    }
    
    public boolean isPlayerInWindow()
    {
        return currentMenu != ChatMenu.None;
    }
    
    public ChatMenu getChatMenu()
    {
        return currentMenu;
    }
    
    public void setChatMenu(ChatMenu chatMenu)
    {
        currentMenu = chatMenu;
        showChatMenu();
    }
    
    public void showChatMenu()
    {
        ChatWindow chatWin = plugin.getChatWindow(getNameFromType(currentMenu));
        for (int i = 1; i <= chatWin.getNumOptions(); i++)
        {
            player.sendMessage(chatWin.getOption(i).getHotkey() + ". " + chatWin.getOption(i).getName());
        }
        player.sendMessage(chatWin.getOption(0).getHotkey() + ". " + chatWin.getOption(0).getName());
    }
    
    public String getNameFromType(ChatMenu menuType)
    {
        switch (menuType)
        {
            case Root:
                return "MainMenu";
            default:
                return "None";
        }
    }
}
