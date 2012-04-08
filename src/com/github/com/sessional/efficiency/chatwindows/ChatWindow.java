/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.com.sessional.efficiency.chatwindows;

import com.github.com.sessional.efficiency.EfficiencyPlugin;
import com.github.com.sessional.efficiency.settings.ChatSettings.ChatMenu;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.bukkit.entity.Player;

/**
 *
 * @author Andrew
 */
public abstract class ChatWindow
{

    private HashMap<Integer, Option> options;
    private EfficiencyPlugin plugin;
    private int numOptions = 0;

    public ChatWindow(EfficiencyPlugin plugin)
    {
        options = new HashMap<Integer, Option>();
        this.plugin = plugin;
    }
    
    public int getNumOptions()
    {
        return numOptions;
    }
    
    public void setNumOptions(int options)
    {
        numOptions = options;
    }
    
    public List<Option> getOptions()
    {
        List<Option> retList = new ArrayList<Option>();
        for (Option o : options.values())
        {
            retList.add(o);
        }
        return retList;
    }
    
    public void exitWindow(Player player)
    {
        plugin.getPlayerSettings(player).getChatSettings().setChatMenu(ChatMenu.None);
    }

    public void addOption(Integer optionNumber, Option option)
    {
        options.put(optionNumber, option);
    }
    
    public void removeOption(Integer optionNumber)
    {
        options.remove(optionNumber);
    }

    public Option getOption(Integer optionNumber)
    {
        return options.get(optionNumber);
    }

    public abstract void executeOption(String option);

    public abstract void executeOption(int index);
}
