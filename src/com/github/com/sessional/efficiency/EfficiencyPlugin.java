/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.com.sessional.efficiency;

import com.github.com.sessional.efficiency.chatwindows.ChatWindow;
import com.github.com.sessional.efficiency.chatwindows.MainMenu;
import com.github.com.sessional.efficiency.events.ChatMenuListener;
import com.github.com.sessional.efficiency.events.PlayerSetupListener;
import com.github.com.sessional.efficiency.settings.ChatSettings.ChatMenu;
import com.github.com.sessional.efficiency.settings.PlayerSettings;
import java.util.HashMap;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @author Andrew
 */
public class EfficiencyPlugin extends JavaPlugin
{
    private HashMap<String, PlayerSettings> playerSettings;
    private HashMap<String, ChatWindow> chatWindows;
    
    public ChatWindow getChatWindow(String name)
    {
        return chatWindows.get(name);
    }
    
    @Override
    public void onEnable()
    {
        playerSettings = new HashMap<String, PlayerSettings>();
        chatWindows = new HashMap<String, ChatWindow>();
        chatWindows.put("MainMenu", new MainMenu(this));
        getServer().getPluginManager().registerEvents(new PlayerSetupListener(this), this);
        this.getServer().getPluginManager().registerEvents(new ChatMenuListener(this), this);
    }
    
    @Override
    public void onDisable()
    {
        
    }
    
    public PlayerSettings getPlayerSettings(Player player)
    {
        return playerSettings.get(player.getName());
    }
    
    public PlayerSettings getPlayerSettings(String playerName)
    {
        return playerSettings.get(playerName);
    }
    
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
    {
        Player player = null;
        if (sender instanceof Player)
            player = (Player)sender;
        
        if (player == null)
            return false;
        
        if (cmd.getName().equals("eff"))
        {
            getPlayerSettings(player).getChatSettings().setChatMenu(ChatMenu.Root);
        }
        
        return false;
    }

    public void createPlayerSettings(Player player)
    {
        playerSettings.put(player.getName(), new PlayerSettings(this, player));
    }
}
