/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.sessional.efficiency.chatwindows.options;

import com.github.sessional.efficiency.EfficiencyPlugin;
import org.bukkit.entity.Player;

/**
 *
 * @author Andrew
 */
public class ExitLink extends Option
{
    public ExitLink(EfficiencyPlugin plugin, String name, int hotkey)
    {
        super(plugin, name, hotkey);
    }
    
    public void exitMenu(Player player)
    {
        getPlugin().getPlayerSettings(player).getChatSettings().setChatMenu(null);
    }
}
