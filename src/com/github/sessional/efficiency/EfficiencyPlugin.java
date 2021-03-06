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
import com.github.sessional.efficiency.settings.DiggingSettings;
import com.github.sessional.efficiency.settings.PlayerSettings;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        addTalent(apprenticeDigging.getName(), apprenticeDigging);
        
        DiggingTalent journeymanDigging = new DiggingTalent(this,
                DiggingTree.getNameFromTechnique(DiggingTechniques.JOURNEYMAN_DIGGING),
                DiggingTree.getDescriptionFromTechnique(DiggingTechniques.JOURNEYMAN_DIGGING), 1);
        journeymanDigging.setRankChance(0, 0);
        journeymanDigging.setRankChance(1, 10);
        journeymanDigging.setRequirement(apprenticeDigging);
        journeymanDigging.setRequiredLevel(5);
        addTalent(journeymanDigging.getName(), journeymanDigging);
        
        DiggingTalent expertDigging = new DiggingTalent(this,
                DiggingTree.getNameFromTechnique(DiggingTechniques.EXPERT_DIGGING),
                DiggingTree.getDescriptionFromTechnique(DiggingTechniques.EXPERT_DIGGING), 1);
        expertDigging.setRankChance(0, 0);
        expertDigging.setRankChance(1, 15);
        expertDigging.setRequirement(journeymanDigging);
        expertDigging.setRequiredLevel(10);
        addTalent(expertDigging.getName(), expertDigging);
        
        DiggingTalent masterDigging = new DiggingTalent(this,
                DiggingTree.getNameFromTechnique(DiggingTechniques.MASTER_DIGGING),
                DiggingTree.getDescriptionFromTechnique(DiggingTechniques.MASTER_DIGGING), 1);
        masterDigging.setRankChance(0, 0);
        masterDigging.setRankChance(1, 20);
        masterDigging.setRequirement(expertDigging);
        masterDigging.setRequiredLevel(15);
        addTalent(masterDigging.getName(), masterDigging);
        
        DiggingTalent improvedFlint = new DiggingTalent(this,
                DiggingTree.getNameFromTechnique(DiggingTechniques.IMPROVED_FLINT),
                DiggingTree.getDescriptionFromTechnique(DiggingTechniques.IMPROVED_FLINT), 5);
        improvedFlint.setRankChance(0, 0);
        improvedFlint.setRankChance(1, 2);
        improvedFlint.setRankChance(2, 4);
        improvedFlint.setRankChance(3, 6);
        improvedFlint.setRankChance(4, 8);
        improvedFlint.setRankChance(5, 10);
        improvedFlint.setRequiredLevel(5);
        improvedFlint.setRequirement(journeymanDigging);
        addTalent(improvedFlint.getName(), improvedFlint);
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
        TalentDisplayMenu improvedFlint = new TalentDisplayMenu(this, diggingTree,
                getTalent(DiggingTree.getNameFromTechnique(DiggingTechniques.IMPROVED_FLINT)));
        addChatMenu(ChatMenu.getNameFromType(ChatMenu.MenuTypes.MAIN_MENU), mainMenu);
        addChatMenu(ChatMenu.getNameFromType(ChatMenu.MenuTypes.EXPERTISE_MENU), expertiseMenu);
        addChatMenu(ChatMenu.getNameFromType(ChatMenu.MenuTypes.BROWSE_MENU), browseMenu);
        addChatMenu(ChatMenu.getNameFromType(ChatMenu.MenuTypes.TECHNIQUE_MENU), techniqueMenu);
        addChatMenu(DiggingTree.getNameFromTechnique(DiggingTechniques.APPRENTICE_DIGGING), apprenticeDiggingTalent);
        addChatMenu(DiggingTree.getNameFromTechnique(DiggingTechniques.JOURNEYMAN_DIGGING), journeymanDiggingTalent);
        addChatMenu(DiggingTree.getNameFromTechnique(DiggingTechniques.EXPERT_DIGGING), expertDiggingTalent);
        addChatMenu(DiggingTree.getNameFromTechnique(DiggingTechniques.MASTER_DIGGING), masterDiggingTalent);
        addChatMenu(DiggingTree.getNameFromTechnique(DiggingTechniques.IMPROVED_FLINT), improvedFlint);
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
        improvedFlint.init();
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
        
        if (getPlayerSettings(player) == null)
        {
            createPlayerSettings(player);
        }
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
    
    public void savePlayerSettings(Player player)
    {
        saveDirectories(player);
    }
    
    public void checkAndCreateDirectories(Player player)
    {
        File dataFolder = getDataFolder();
        String fileSep = File.separator;
        File playerDirectory = new File(dataFolder + fileSep  + player.getName());
        if (!playerDirectory.exists())
        {
            playerDirectory.mkdirs();
        }
        File digFile = new File(playerDirectory, "Dig.txt");
        if (!digFile.exists())
        {
            try
            {
                digFile.createNewFile();
            } catch (IOException ex)
            {
                System.out.println("Can not create a dig file!");
                return;
            }
        }
    }
    
    public void saveDirectories(Player player)
    {
        File dataFolder = getDataFolder();
        String fileSep = File.separator;
        File playerDirectory = new File(dataFolder + fileSep + player.getName());
        if (!playerDirectory.exists())
        {
            return;
        }
        DiggingSettings digSettings = getPlayerSettings(player).getDiggingSettings();
        String digString = digSettings.getTalentString();
        int expLevel = digSettings.getExpertiseLevel();
        File digFile = new File(playerDirectory, "Dig.txt");
        if (!digFile.exists())
        {
            return;
        }
        try
        {
            FileWriter fw = new FileWriter(digFile);
            BufferedWriter buff = new BufferedWriter(fw);
            fw.write(expLevel + "\n");
            buff.write(digString);
            buff.close();
            fw.close();
        } catch (Exception ex)
        {
            System.out.println("Corrupted Player Directory: " + player.getName());
        }
    }
    
    public void loadFiles(Player player)
    {
        File dataFolder = getDataFolder();
        String fileSep = File.separator;
        File playerDirectory = new File(dataFolder + fileSep + player.getName());
        
        if (!playerDirectory.exists())
        {
            checkAndCreateDirectories(player);
            return;
        }
        
        File diggingFile = new File(playerDirectory, "Dig.txt");
        if (diggingFile.exists())
        {
            FileReader fr = null;
            try
            {
                fr = new FileReader(diggingFile);
                BufferedReader buff = new BufferedReader(fr);
                String expLevel = buff.readLine();
                if (expLevel == null)
                {
                    return;
                }
                int exLevel = Integer.parseInt(expLevel);
                if (this.getPlayerSettings(player) == null)
                {
                    this.createPlayerSettings(player);
                    this.getPlayerSettings(player).getDiggingSettings().setExpertiseLevel(exLevel);
                } else
                {
                    this.getPlayerSettings(player).getDiggingSettings().setExpertiseLevel(exLevel);
                }
                String currentLine = buff.readLine();
                while (currentLine != "\n" && currentLine != null && currentLine != "null")
                {
                    String talentLine = currentLine;
                    String[] talent = talentLine.split(":");
                    String talentName = talent[0];
                    int talentLevel = Integer.parseInt(talent[1]);
                    getPlayerSettings(player).getDiggingSettings().setRankForTalent(talentName, talentLevel);
                    currentLine = buff.readLine();
                }
                
            } catch (Exception ex)
            {
                Logger.getLogger(EfficiencyPlugin.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
