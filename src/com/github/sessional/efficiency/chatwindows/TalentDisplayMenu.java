/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.sessional.efficiency.chatwindows;

import com.github.sessional.efficiency.EfficiencyPlugin;
import com.github.sessional.efficiency.Tree.Talent.Talent;
import com.github.sessional.efficiency.chatwindows.options.BackLink;
import com.github.sessional.efficiency.chatwindows.options.ExecutingLink;

/**
 *
 * @author Andrew
 */
public class TalentDisplayMenu extends ChatMenu
{
    Talent linkedTalent;
    public TalentDisplayMenu(EfficiencyPlugin plugin, ChatMenu parent, Talent linkedTalent)
    {
        super(plugin, parent);
        setName("Talent Display Menu");
        this.linkedTalent = linkedTalent;
    }

    public enum TalentStyle
    {

        Display, Learnable
    }

    public Talent getLinkedTalent()
    {
        return linkedTalent;
    }
    
    public void init()
    {
        setNumOptions(0);
        ExecutingLink executingLink = new ExecutingLink(getPlugin(), "Learn", 9);
        BackLink backLink = new BackLink(getPlugin(), "Back", 0);
        addOption(9, executingLink);
        addOption(0, backLink);
    }
}
