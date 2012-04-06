/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.com.sessional.efficiency;

import com.github.com.sessional.efficiency.events.ChatMenuListener;
import com.github.com.sessional.efficiency.settings.ChatSettings.ChatWindow;
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
    
    @Override
    public void onEnable()
    {
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
        
        if (cmd.getName().equals("Efficiency"))
        {
            getPlayerSettings(player).getChatSettings().setChatWindow(ChatWindow.Root);
        }
        
        return false;
    }
}
