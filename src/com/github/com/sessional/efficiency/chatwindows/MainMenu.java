/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.com.sessional.efficiency.chatwindows;

import com.github.com.sessional.efficiency.EfficiencyPlugin;
import org.bukkit.entity.Player;

/**
 *
 * @author Andrew
 */
public class MainMenu extends ChatWindow
{
    /**
     * See Expertise (1)
     * Level Up (If Available)
     * Learned Techniques
     * Exit (0)
     */
    private int SEE_EXPERTISE = 1, BROWSE_TECHNIQUES = 2, LEVEL_UP = 3, LEARNED_TECHNIQUES = 4;
    private int EXIT = 0;
    /**
     * Number of options excluding EXIT
     */
    
    public MainMenu(EfficiencyPlugin plugin)
    {
        super(plugin);
        this.setNumOptions(3);
        this.addOption(SEE_EXPERTISE, new Option(SEE_EXPERTISE, "See Expertise", new ExpertiseMenu(plugin)));
        this.addOption(BROWSE_TECHNIQUES, new Option(BROWSE_TECHNIQUES, "Browse Techniques", new TechniqueWindow(plugin)));
        this.addOption(LEVEL_UP, new Option(LEVEL_UP, "Level Up", new LevelUpMenu(plugin)));
        this.addOption(EXIT, new Option(EXIT, "Exit", null));
    }

    @Override
    public void executeOption(String option)
    {
        
    }
    
    public void displayMenu(Player player)
    {
        
    }

    
    public void executeOption(int index, Player player)
    {
        
    }

    @Override
    public void executeOption(int index)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
