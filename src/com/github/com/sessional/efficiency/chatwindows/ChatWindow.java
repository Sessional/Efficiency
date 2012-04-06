/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.com.sessional.efficiency.chatwindows;

import com.github.com.sessional.efficiency.EfficiencyPlugin;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Andrew
 */
public abstract class ChatWindow
{

    private HashMap<Integer, Option> options;
    private EfficiencyPlugin plugin;

    public ChatWindow(EfficiencyPlugin plugin)
    {
        this.plugin = plugin;
    }
    
    public List<Option> getOptions()
    {
        List<Option> retList = new ArrayList<Option>();
        for (Option o : options.values())
        {
            retList.add(o);
        }
        return retList;
    }

    public void addOption(Integer optionNumber, Option option)
    {
        options.put(optionNumber, option);
    }
    
    public void removeOption(Integer optionNumber)
    {
        options.remove(optionNumber);
    }

    public Option getOption(Integer optionNumber)
    {
        return options.get(optionNumber);
    }

    public abstract void executeOption(String option);

    public abstract void executeOption(int index);
}
