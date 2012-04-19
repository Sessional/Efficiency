/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.sessional.efficiency.chatwindows;

import com.github.sessional.efficiency.EfficiencyPlugin;
import com.github.sessional.efficiency.chatwindows.options.Option;
import com.github.sessional.efficiency.settings.ChatSettings;
import com.github.sessional.efficiency.settings.PlayerSettings;
import java.util.HashMap;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

/**
 *
 * @author Andrew
 */
public abstract class ChatMenu
{
    public enum MenuTypes
    {
        MAIN_MENU, BROWSE_MENU, DIGGING_DISPLAY_MENU, EXPERTISE_MENU, TALENT_DISPLAY_MENU, TECHNIQUE_MENU
    }
    
    public static String getNameFromType(MenuTypes menu)
    {
        switch (menu)
        {
            case MAIN_MENU:
                return "Main Menu";
            case BROWSE_MENU:
                return "Browse Menu";
            case DIGGING_DISPLAY_MENU:
                return "Digging Display Menu";
            case EXPERTISE_MENU:
                return "Expertise Menu";
            case TALENT_DISPLAY_MENU:
                return "Talent Display Menu";
            case TECHNIQUE_MENU:
                return "Technique Menu";
            default:
                return "Not a valid menu!";
        }
    }
    
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
        PlayerSettings playerSettings = getPlugin().getPlayerSettings(player);
        if (chatSettings.getPageNum() != 0)
        {
            itemsToList--;
        }
        if (getOption((chatSettings.getPageNum() + 1) * (itemsToList - 1)) != null)
        {
            itemsToList--;
        }
        player.sendMessage("§6Menu: " + getName() + " - Page: " + (chatSettings.getPageNum() + 1));
        if (this instanceof TalentDisplayMenu)
        {
            TalentDisplayMenu tDM = (TalentDisplayMenu) this;
            player.sendMessage("§6Talent: §f" + tDM.getLinkedTalent().getName());
            player.sendMessage("§f" + tDM.getLinkedTalent().getDescription());
            StringBuilder sb = new StringBuilder();
            sb.append(ChatColor.GOLD);
            sb.append("Requires level: ");
            sb.append(ChatColor.WHITE);
            sb.append(tDM.getLinkedTalent().getRequiredLevel());
            if (tDM.getLinkedTalent().getRequirement() != null)
            {
                sb.append(ChatColor.GOLD);
                sb.append(" - Requires talent: ");
                sb.append(ChatColor.WHITE);
                sb.append(tDM.getLinkedTalent().getRequirement().getName());
            }
            else
            {
                sb = new StringBuilder();
                sb.append("No requirements.");
            }
            player.sendMessage(sb.toString());
            sb = new StringBuilder();
            for (int i = 0; i < tDM.getLinkedTalent().getChances().length; i++)
            {
                if (playerSettings.getDiggingSettings().getRankForTalent(tDM.getLinkedTalent()) == i)
                {
                    sb.append(ChatColor.GREEN);
                }
                sb.append(tDM.getLinkedTalent().getChanceAtRank(i)); if (playerSettings.getDiggingSettings().getRankForTalent(tDM.getLinkedTalent()) == i)
                {
                    sb.append(ChatColor.WHITE);
                }
                sb.append("/");
            }
            sb.deleteCharAt(sb.lastIndexOf("/"));
            player.sendMessage("§6Chances: " + "§f" + sb.toString());
            
            if (playerSettings.getDiggingSettings().hasTalent(tDM.getLinkedTalent()))
            {
                if (playerSettings.getDiggingSettings().getRankForTalent(tDM.getLinkedTalent()) < tDM.getLinkedTalent().getRanks())
                {
                    player.sendMessage("§6" + getOption(9).getHotkey() + ": §f" + getOption(9).getName());
                }
            }
            else
            {
                player.sendMessage("§6" + getOption(9).getHotkey() + ": §f" + getOption(9).getName());
            }
        } else
        {
            for (int i = (chatSettings.getPageNum() * itemsToList) + 1; i <= (chatSettings.getPageNum() + 1) * itemsToList; i++)
            {
                if (getOption(i) != null)
                {
                    player.sendMessage("§6" + getOption(i).getHotkey() + ": §f" + getOption(i).getName());
                }
            }
            if (getOption((chatSettings.getPageNum() + 1) * itemsPerPage) != null)
            {
                player.sendMessage("§6" + getOption(8).getHotkey() + ": §f" + getOption(8).getName());
            }
            if (chatSettings.getPageNum() != 0)
            {
                player.sendMessage("§6" + getOption(9).getHotkey() + ": §f" + getOption(9).getName());
            }
        }
        player.sendMessage("§6" + getOption(0).getHotkey() + ": §f" + getOption(0).getName());
    }
}
