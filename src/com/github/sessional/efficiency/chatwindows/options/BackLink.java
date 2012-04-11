/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.sessional.efficiency.chatwindows.options;

import com.github.sessional.efficiency.EfficiencyPlugin;
import com.github.sessional.efficiency.settings.ChatSettings;
import org.bukkit.entity.Player;

/**
 *
 * @author Andrew
 */
public class BackLink extends Option
{
    public BackLink(EfficiencyPlugin plugin, String name, int hotkey)
    {
        super(plugin, name, hotkey);
    }
    
    public void goBack(Player player)
    {
        ChatSettings chatSettings = getPlugin().getPlayerSettings(player).getChatSettings();
        chatSettings.setChatMenu(chatSettings.getChatMenu().getParent());
    }
}
