/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.sessional.efficiency.events;

import com.github.sessional.efficiency.EfficiencyPlugin;
import com.github.sessional.efficiency.chatwindows.options.BackLink;
import com.github.sessional.efficiency.chatwindows.options.ExecutingLink;
import com.github.sessional.efficiency.chatwindows.options.ExitLink;
import com.github.sessional.efficiency.chatwindows.options.ForwardLink;
import com.github.sessional.efficiency.chatwindows.options.LinkedMenu;
import com.github.sessional.efficiency.chatwindows.options.Option;
import com.github.sessional.efficiency.chatwindows.options.PreviousLink;
import com.github.sessional.efficiency.settings.ChatSettings;
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
        if (chatSettings.isPlayerInWindow())
        {
            System.out.println("Chat command: " + event.getPlayer().getName());

            Option o = chatSettings.getChatMenu().getOption(Integer.parseInt(event.getMessage()));
            if (o instanceof BackLink)
            {
                o = (BackLink) o;
                chatSettings.setChatMenu(chatSettings.getChatMenu().getParent());
            } else if (o instanceof ExitLink)
            {
                o = (ExitLink) o;
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
                System.out.println("Executed Option!");
            }
            
            event.setCancelled(true);
        }
    }
}
