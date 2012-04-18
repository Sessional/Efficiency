/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.sessional.efficiency.Tree;

import com.github.sessional.efficiency.EfficiencyPlugin;
import com.github.sessional.efficiency.Tree.Talent.DiggingTalent;
import java.util.HashMap;
import org.bukkit.entity.Player;

/**
 *
 * @author Andrew
 */
public class DiggingTree extends Tree
{
    public enum DiggingTechniques
    {
        APPRENTICE_DIGGING,
        JOURNEYMAN_DIGGING,
        EXPERT_DIGGING,
        MASTER_DIGGING,
        IMPROVED_DIGGING,
        IMPROVED_FLINT,
        
    }
    
    public static String getNameFromTechnique(DiggingTechniques technique)
    {
        switch (technique)
        {
            case APPRENTICE_DIGGING:
                return "Apprentice Digging";
            case JOURNEYMAN_DIGGING:
                return "Journeyman Digging";
            case EXPERT_DIGGING:
                return "Expert Digging";
            case MASTER_DIGGING:
                return "Master Digging";
            case IMPROVED_DIGGING:
                return "Improved Digging";
            case IMPROVED_FLINT:
                return "Improved Flint Finding";
            default:
                return "Not a valid technique!";
        }
    }
    
    public static String getDescriptionFromTechnique(DiggingTechniques technique)
    {
        switch (technique)
        {
            case APPRENTICE_DIGGING:
                return "A technique in digging which improves your chance to maintain durability on a shovel while you dig.";
            case JOURNEYMAN_DIGGING:
                return "A technique in digging which improves your chance to maintain durability on a shovel while you dig.";
            case EXPERT_DIGGING:
                return "A technique in digging which improves your chance to maintain durability on a shovel while you dig.";
            case MASTER_DIGGING:
                return "A technique in digging which improves your chance to maintain durability on a shovel while you dig.";
            case IMPROVED_DIGGING:
                return "A technique in digging which improves your chance to retrieve more units of a substance from a single block.";
            case IMPROVED_FLINT:
                return "A technique in digging whcih improves your chance to find flint from gravel";
            default:
                return "Not a valid technique!";
        }
    }
    
    private HashMap<String, DiggingTalent> talents;
    
    public DiggingTree(EfficiencyPlugin plugin, Player player)
    {
        talents.put(DiggingTree.getNameFromTechnique(DiggingTechniques.APPRENTICE_DIGGING),
                (DiggingTalent) plugin.getTalent(DiggingTree.getNameFromTechnique(DiggingTechniques.APPRENTICE_DIGGING)));
    }
}
