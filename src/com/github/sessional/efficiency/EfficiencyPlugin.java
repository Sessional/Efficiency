/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.sessional.efficiency;

import com.github.sessional.efficiency.chatwindows.BrowseMenu;
import com.github.sessional.efficiency.chatwindows.ChatMenu;
import com.github.sessional.efficiency.chatwindows.ExpertiseMenu;
import com.github.sessional.efficiency.chatwindows.MainMenu;
import com.github.sessional.efficiency.chatwindows.TechniqueMenu;
import com.github.sessional.efficiency.events.ChatMenuListener;
import com.github.sessional.efficiency.events.PlayerSetupListener;
import com.github.sessional.efficiency.settings.PlayerSettings;
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
    private HashMap<String, ChatMenu> chatMenus;
    
    public ChatMenu getChatMenu(String name)
    {
        return chatMenus.get(name);
    }
    
    @Override
    public void onEnable()
    {
        playerSettings = new HashMap<String, PlayerSettings>();
        chatMenus = new HashMap<String, ChatMenu>();
        initializeMenus();
        
        getServer().getPluginManager().registerEvents(new PlayerSetupListener(this), this);
        getServer().getPluginManager().registerEvents(new ChatMenuListener(this), this);
    }
    
    public void initializeMenus()
    {
        MainMenu mainMenu = new MainMenu(this, null);
        ExpertiseMenu expertiseMenu = new ExpertiseMenu(this, mainMenu);
        BrowseMenu browseMenu = new BrowseMenu(this, mainMenu);
        TechniqueMenu techniqueMenu = new TechniqueMenu(this, mainMenu);
        addChatMenu("MainMenu", mainMenu);
        addChatMenu("ExpertiseMenu", expertiseMenu);
        addChatMenu("BrowseMenu", browseMenu);
        addChatMenu("TechniqueMenu", techniqueMenu);
        mainMenu.init();
        expertiseMenu.init();
        browseMenu.init();
        techniqueMenu.init();
    }
    
    public void addChatMenu(String name, ChatMenu chatMenu)
    {
        chatMenus.put(name, chatMenu);
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
            getPlayerSettings(player).getChatSettings().setChatMenu(getChatMenu("MainMenu"));
        }
        
        return false;
    }

    public void createPlayerSettings(Player player)
    {
        playerSettings.put(player.getName(), new PlayerSettings(this, player));
    }

    public void deletePlayerSettings(Player player)
    {
        if (playerSettings.containsKey(player.getName()))
            playerSettings.remove(player.getName());
    }
}
