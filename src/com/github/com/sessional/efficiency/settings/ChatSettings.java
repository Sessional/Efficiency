/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.com.sessional.efficiency.settings;

import org.bukkit.entity.Player;

/**
 *
 * @author Andrew
 */
public class ChatSettings
{
    private ChatWindow currentWindow;
    private Player player;
    
    public ChatSettings(Player player)
    {
        currentWindow = ChatWindow.None;
        this.player = player;
    }
    
    public enum ChatWindow
    {
        None,
        Root,
        Expertise,
        LevelUp,
        Proficiency
    }
    
    public boolean isPlayerInWindow()
    {
        return currentWindow != ChatWindow.None;
    }
    
    public ChatWindow getChatWindow()
    {
        return currentWindow;
    }
    
    public void setChatWindow(ChatWindow chatWindow)
    {
        currentWindow = chatWindow;
    }
}
