/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.sessional.efficiency.chatwindows;

import com.github.sessional.efficiency.EfficiencyPlugin;
import com.github.sessional.efficiency.chatwindows.options.Option;
import com.github.sessional.efficiency.settings.ChatSettings;
import java.util.HashMap;
import org.bukkit.entity.Player;

/**
 *
 * @author Andrew
 */
public abstract class ChatMenu
{
    private String name;
    private int numOptions;
    private EfficiencyPlugin plugin;
    private ChatMenu parent;
    private int numPages;
    private int itemsPerPage = 9;
    private HashMap<String, Option> options;
    
    public ChatMenu(EfficiencyPlugin plugin, ChatMenu parent)
    {
        this.plugin = plugin;
        this.parent = parent;
        options = new HashMap<String, Option>();
    }
    
    public void setName(String name)
    {
        this.name = name;
    }
    
    public Option getOption(Integer hotkey)
    {
        if (!options.containsKey(hotkey.toString()))
        {
            return null;
        }
        return options.get(hotkey.toString());
    }
    
    public void addOption(Integer hotkey, Option option)
    {
        if (options.containsKey(hotkey.toString()))
        {
            options.remove(hotkey.toString());
        }
        options.put(hotkey.toString(), option);
    }
    
    public int getNumPages()
    {
        return numPages;
    }
    
    public void setNumPages(int num)
    {
        this.numPages = num;
    }
    
    public int getItemsPerPage()
    {
        return itemsPerPage;
    }
    
    public void setItemsPerPage(int itemsPerPage)
    {
        this.itemsPerPage = itemsPerPage;
    }
    
    public void setNumOptions(int numOptions)
    {
        this.numOptions = numOptions;
    }
    
    public String getName()
    {
        return name;
    }
    
    public int getNumOptions()
    {
        return numOptions;
    }
    
    public EfficiencyPlugin getPlugin()
    {
        return plugin;
    }
    
    public ChatMenu getParent()
    {
        return parent;
    }
    
    public void displayMenu(Player player)
    {
        int itemsToList = itemsPerPage;
        ChatSettings chatSettings = getPlugin().getPlayerSettings(player).getChatSettings();
        System.out.println("Before last page check.");
        if (chatSettings.getPageNum() != 0)
        {
            itemsToList--;
        }
        System.out.println("Before next page check.");
        if (getOption((chatSettings.getPageNum()+1)*(itemsToList-1)) != null)
        {
            itemsToList--;
        }
        System.out.println("Before name.");
        player.sendMessage("§6Menu: " + getName() + " - Page: " + (chatSettings.getPageNum() + 1));
        System.out.println("Before loop.");
        for (int i = (chatSettings.getPageNum()*itemsToList) + 1; i <= (chatSettings.getPageNum()+1)*itemsToList; i++)
        {
            if (getOption(i) != null)
            {
                player.sendMessage("§6" + getOption(i).getHotkey() + ": §f" + getOption(i).getName());
            }
        }
        System.out.println("Before option 8.");
        if (getOption((chatSettings.getPageNum()+1)*itemsPerPage) != null)
        {
            player.sendMessage("§6" + getOption(8).getHotkey() + ": §f" + getOption(8).getName());
        }
        System.out.println("Before option 9.");
        if (chatSettings.getPageNum() != 0)
        {
            player.sendMessage("§6" + getOption(9).getHotkey() + ": §f" + getOption(9).getName());
        }
        System.out.println("Before option 0.");
        player.sendMessage("§6" + getOption(0).getHotkey() + ": §f" + getOption(0).getName());
        System.out.println("After option 0.");
    }
}
