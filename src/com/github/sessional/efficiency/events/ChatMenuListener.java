/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.sessional.efficiency.events;

import com.github.sessional.efficiency.EfficiencyPlugin;
import com.github.sessional.efficiency.chatwindows.TalentDisplayMenu;
import com.github.sessional.efficiency.chatwindows.options.BackLink;
import com.github.sessional.efficiency.chatwindows.options.ExecutingLink;
import com.github.sessional.efficiency.chatwindows.options.ExitLink;
import com.github.sessional.efficiency.chatwindows.options.ForwardLink;
import com.github.sessional.efficiency.chatwindows.options.LinkedMenu;
import com.github.sessional.efficiency.chatwindows.options.Option;
import com.github.sessional.efficiency.chatwindows.options.PreviousLink;
import com.github.sessional.efficiency.settings.ChatSettings;
import com.github.sessional.efficiency.settings.PlayerSettings;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;

/**
 *
 * @author Andrew
 */
public class ChatMenuListener implements Listener
{

    private EfficiencyPlugin plugin;

    public ChatMenuListener(EfficiencyPlugin plugin)
    {
        this.plugin = plugin;
    }

    @EventHandler
    public void onChatEnter(PlayerChatEvent event)
    {
        Player player = null;
        if (event.getPlayer() != null)
        {
            player = event.getPlayer();
        }
        ChatSettings chatSettings = plugin.getPlayerSettings(player).getChatSettings();
        PlayerSettings playerSettings = plugin.getPlayerSettings(player);
        if (chatSettings.isPlayerInWindow())
        {
            Option o = chatSettings.getChatMenu().getOption(Integer.parseInt(event.getMessage()));
            if (o instanceof BackLink)
            {
                chatSettings.setChatMenu(chatSettings.getChatMenu().getParent());
            } else if (o instanceof ExitLink)
            {
                chatSettings.setChatMenu(null);
            } else if (o instanceof LinkedMenu)
            {
                LinkedMenu o2 = (LinkedMenu) o;
                chatSettings.setChatMenu(o2.getLinkedMenu());
            } else if (o instanceof PreviousLink)
            {
                chatSettings.goBackPage();
            } else if (o instanceof ForwardLink)
            {
                chatSettings.goForwardPage();
            } else if (o instanceof ExecutingLink)
            {
                if (chatSettings.getChatMenu() instanceof TalentDisplayMenu)
                {
                    TalentDisplayMenu tDM = (TalentDisplayMenu) chatSettings.getChatMenu();
                    if (tDM.getLinkedTalent().canLearn(player))
                    {
                        if (playerSettings.learnTalent(tDM.getLinkedTalent()))
                        {
                            player.sendMessage("Learned talent " + ChatColor.GREEN + tDM.getLinkedTalent().getName() + ChatColor.WHITE + " rank " + ChatColor.GOLD + playerSettings.getDiggingSettings().getRankForTalent(tDM.getLinkedTalent()) + ChatColor.WHITE + ".");
                        }
                    }
                    else
                    {
                        player.sendMessage("You do not meet the requirements for that technique!");
                    }
                }
                plugin.getPlayerSettings(player).getChatSettings().setChatMenu(null);
            }
            event.setCancelled(true);
        }
    }
}
