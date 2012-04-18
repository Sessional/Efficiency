/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.sessional.efficiency;

import com.github.sessional.efficiency.Tree.DiggingTree;
import com.github.sessional.efficiency.Tree.DiggingTree.DiggingTechniques;
import com.github.sessional.efficiency.Tree.Talent.DiggingTalent;
import com.github.sessional.efficiency.Tree.Talent.Talent;
import com.github.sessional.efficiency.chatwindows.BrowseMenu;
import com.github.sessional.efficiency.chatwindows.ChatMenu;
import com.github.sessional.efficiency.chatwindows.ExpertiseMenu;
import com.github.sessional.efficiency.chatwindows.MainMenu;
import com.github.sessional.efficiency.chatwindows.TechniqueMenu;
import com.github.sessional.efficiency.chatwindows.DiggingDisplayMenu;
import com.github.sessional.efficiency.chatwindows.TalentDisplayMenu;
import com.github.sessional.efficiency.events.ChatMenuListener;
import com.github.sessional.efficiency.events.DiggingListener;
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
    private HashMap<String, Talent> talents;
    
    public ChatMenu getChatMenu(String name)
    {
        return chatMenus.get(name);
    }
    
    @Override
    public void onEnable()
    {
        playerSettings = new HashMap<String, PlayerSettings>();
        chatMenus = new HashMap<String, ChatMenu>();
        talents = new HashMap<String, Talent>();
        intializeTalents();
        initializeMenus();
        
        getServer().getPluginManager().registerEvents(new DiggingListener(this), this);
        getServer().getPluginManager().registerEvents(new PlayerSetupListener(this), this);
        getServer().getPluginManager().registerEvents(new ChatMenuListener(this), this);
    }

    public void intializeTalents()
    {
        DiggingTalent apprenticeDigging = new DiggingTalent(this,
                DiggingTree.getNameFromTechnique(DiggingTechniques.APPRENTICE_DIGGING),
                DiggingTree.getDescriptionFromTechnique(DiggingTechniques.APPRENTICE_DIGGING), 1);
        apprenticeDigging.setRankChance(0, 0);
        apprenticeDigging.setRankChance(1, 5);
        apprenticeDigging.setRequirement(null);
        addTalent(DiggingTree.getNameFromTechnique(DiggingTechniques.APPRENTICE_DIGGING), apprenticeDigging);
        
        DiggingTalent journeymanDigging = new DiggingTalent(this,
                DiggingTree.getNameFromTechnique(DiggingTechniques.JOURNEYMAN_DIGGING),
                DiggingTree.getDescriptionFromTechnique(DiggingTechniques.JOURNEYMAN_DIGGING), 1);
        journeymanDigging.setRankChance(0, 0);
        journeymanDigging.setRankChance(1, 10);
        journeymanDigging.setRequirement(apprenticeDigging);
        journeymanDigging.setRequiredLevel(5);
        addTalent(DiggingTree.getNameFromTechnique(DiggingTechniques.JOURNEYMAN_DIGGING), journeymanDigging);
        
        DiggingTalent expertDigging = new DiggingTalent(this,
                DiggingTree.getNameFromTechnique(DiggingTechniques.EXPERT_DIGGING),
                DiggingTree.getDescriptionFromTechnique(DiggingTechniques.EXPERT_DIGGING), 1);
        expertDigging.setRankChance(0, 0);
        expertDigging.setRankChance(1, 15);
        expertDigging.setRequirement(journeymanDigging);
        expertDigging.setRequiredLevel(10);
        addTalent(DiggingTree.getNameFromTechnique(DiggingTechniques.EXPERT_DIGGING), expertDigging);
        
        DiggingTalent masterDigging = new DiggingTalent(this,
                DiggingTree.getNameFromTechnique(DiggingTechniques.MASTER_DIGGING),
                DiggingTree.getDescriptionFromTechnique(DiggingTechniques.MASTER_DIGGING), 1);
        masterDigging.setRankChance(0, 0);
        masterDigging.setRankChance(1, 20);
        masterDigging.setRequirement(expertDigging);
        masterDigging.setRequiredLevel(15);
        addTalent(DiggingTree.getNameFromTechnique(DiggingTechniques.MASTER_DIGGING), masterDigging);
    }
    
    public Talent getTalent(DiggingTechniques technique)
    {
        return getTalent(DiggingTree.getNameFromTechnique(technique));
    }
    
    public Talent getTalent(String name)
    {
        return talents.get(name);
    }
    
    public void addTalent(String name, Talent talent)
    {
        if (talents.containsKey(name))
        {
            talents.remove(name);
        }
        talents.put(name, talent);
    }
    
    public void initializeMenus()
    {
        MainMenu mainMenu = new MainMenu(this, null);
        ExpertiseMenu expertiseMenu = new ExpertiseMenu(this, mainMenu);
        BrowseMenu browseMenu = new BrowseMenu(this, mainMenu);
        TechniqueMenu techniqueMenu = new TechniqueMenu(this, mainMenu);
        DiggingDisplayMenu diggingTree = new DiggingDisplayMenu(this, browseMenu);
        TalentDisplayMenu apprenticeDiggingTalent = new TalentDisplayMenu(this, diggingTree,
                getTalent(DiggingTree.getNameFromTechnique(DiggingTechniques.APPRENTICE_DIGGING)));
        TalentDisplayMenu journeymanDiggingTalent = new TalentDisplayMenu(this, diggingTree,
                getTalent(DiggingTree.getNameFromTechnique(DiggingTechniques.JOURNEYMAN_DIGGING)));
        TalentDisplayMenu expertDiggingTalent = new TalentDisplayMenu(this, diggingTree,
                getTalent(DiggingTree.getNameFromTechnique(DiggingTechniques.EXPERT_DIGGING)));
        TalentDisplayMenu masterDiggingTalent = new TalentDisplayMenu(this, diggingTree,
                getTalent(DiggingTree.getNameFromTechnique(DiggingTechniques.MASTER_DIGGING)));
        addChatMenu(ChatMenu.getNameFromType(ChatMenu.MenuTypes.MAIN_MENU), mainMenu);
        addChatMenu(ChatMenu.getNameFromType(ChatMenu.MenuTypes.EXPERTISE_MENU), expertiseMenu);
        addChatMenu(ChatMenu.getNameFromType(ChatMenu.MenuTypes.BROWSE_MENU), browseMenu);
        addChatMenu(ChatMenu.getNameFromType(ChatMenu.MenuTypes.TECHNIQUE_MENU), techniqueMenu);
        addChatMenu(DiggingTree.getNameFromTechnique(DiggingTechniques.APPRENTICE_DIGGING), apprenticeDiggingTalent);
        addChatMenu(DiggingTree.getNameFromTechnique(DiggingTechniques.JOURNEYMAN_DIGGING), journeymanDiggingTalent);
        addChatMenu(DiggingTree.getNameFromTechnique(DiggingTechniques.EXPERT_DIGGING), expertDiggingTalent);
        addChatMenu(DiggingTree.getNameFromTechnique(DiggingTechniques.MASTER_DIGGING), masterDiggingTalent);
        addChatMenu(ChatMenu.getNameFromType(ChatMenu.MenuTypes.DIGGING_DISPLAY_MENU), diggingTree);
        mainMenu.init();
        expertiseMenu.init();
        browseMenu.init();
        techniqueMenu.init();
        diggingTree.init();
        apprenticeDiggingTalent.init();
        journeymanDiggingTalent.init();
        expertDiggingTalent.init();
        masterDiggingTalent.init();
    }
    
    public void addChatMenu(String name, ChatMenu chatMenu)
    {
        if (chatMenus.containsKey(name))
            chatMenus.remove(name);
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
            getPlayerSettings(player).getChatSettings().setChatMenu(getChatMenu(ChatMenu.getNameFromType(ChatMenu.MenuTypes.MAIN_MENU)));
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
