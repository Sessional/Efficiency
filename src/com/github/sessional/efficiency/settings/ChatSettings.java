/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.sessional.efficiency.settings;

import com.github.sessional.efficiency.EfficiencyPlugin;
import com.github.sessional.efficiency.chatwindows.ChatMenu;
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
    private int curPage;

    public ChatSettings(EfficiencyPlugin plugin, Player player)
    {
        curPage = 0;
        this.plugin = plugin;
        currentMenu = null;
        this.player = player;
    }

    public void goForwardPage()
    {
        curPage++;
    }

    public int getPageNum()
    {
        return curPage;
    }

    public void goBackPage()
    {
        curPage--;
    }

    public boolean isPlayerInWindow()
    {
        return currentMenu != null;
    }

    public ChatMenu getChatMenu()
    {
        return currentMenu;
    }

    public void setChatMenu(ChatMenu chatMenu)
    {
        currentMenu = chatMenu;
        if (currentMenu == null)
        {
            player.sendMessage("You have exited the menu. You can now talk again.");
        }
        if (currentMenu != null)
        {
            currentMenu.displayMenu(player);
        }
    }
}
