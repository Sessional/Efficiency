/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.sessional.efficiency.chatwindows;

import com.github.sessional.efficiency.EfficiencyPlugin;
import com.github.sessional.efficiency.Tree.DiggingTree;
import com.github.sessional.efficiency.Tree.DiggingTree.DiggingTechniques;
import com.github.sessional.efficiency.chatwindows.options.BackLink;
import com.github.sessional.efficiency.chatwindows.options.LinkedMenu;

/**
 *
 * @author Andrew
 */
public class DiggingDisplayMenu extends ChatMenu
{
    
    public DiggingDisplayMenu(EfficiencyPlugin plugin, ChatMenu parent)
    {
        super(plugin, parent);
        setName("Digging Tree Browse Menu");
    }
    
    public void init()
    {
        setNumOptions(5);
        LinkedMenu apprenticeDiggingMenu = new LinkedMenu(getPlugin(),
                DiggingTree.getNameFromTechnique(DiggingTechniques.APPRENTICE_DIGGING),
                1, getPlugin().getChatMenu(DiggingTree.getNameFromTechnique(DiggingTechniques.APPRENTICE_DIGGING)));
        LinkedMenu journeymanDiggingMenu = new LinkedMenu(getPlugin(),
                DiggingTree.getNameFromTechnique(DiggingTechniques.JOURNEYMAN_DIGGING),
                2, getPlugin().getChatMenu(DiggingTree.getNameFromTechnique(DiggingTechniques.JOURNEYMAN_DIGGING)));
        LinkedMenu expertDiggingMenu = new LinkedMenu(getPlugin(),
                DiggingTree.getNameFromTechnique(DiggingTechniques.EXPERT_DIGGING),
                3, getPlugin().getChatMenu(DiggingTree.getNameFromTechnique(DiggingTechniques.EXPERT_DIGGING)));
        LinkedMenu masterDiggingMenu = new LinkedMenu(getPlugin(),
                DiggingTree.getNameFromTechnique(DiggingTechniques.MASTER_DIGGING),
                4, getPlugin().getChatMenu(DiggingTree.getNameFromTechnique(DiggingTechniques.MASTER_DIGGING)));
        LinkedMenu improvedFlintMenu = new LinkedMenu(getPlugin(),
                DiggingTree.getNameFromTechnique(DiggingTechniques.IMPROVED_FLINT),
                5, getPlugin().getChatMenu(DiggingTree.getNameFromTechnique(DiggingTechniques.IMPROVED_FLINT)));
        BackLink backLink = new BackLink(getPlugin(), "Back", 0);
        addOption(0, backLink);
        addOption(1, apprenticeDiggingMenu);
        addOption(2, journeymanDiggingMenu);
        addOption(3, expertDiggingMenu);
        addOption(4, masterDiggingMenu);
        addOption(5, improvedFlintMenu);
    }
}
