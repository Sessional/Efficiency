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
    private int SEE_EXPERTISE = 1, LEVEL_UP = 2, LEARNED_TECHNIQUES = 3;
    private int EXIT = 0;
    /**
     * Number of options excluding EXIT
     */
    private int numOptions;
    
    public MainMenu(EfficiencyPlugin plugin)
    {
        super(plugin);
        this.addOption(SEE_EXPERTISE, new Option(1, "See Expertise", new ExpertiseMenu(plugin)));
        this.addOption(LEVEL_UP, new Option(2, "Level Up", new LevelUpMenu()));
        this.addOption(EXIT, new Option(0, "Exit", null));
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
