/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.sessional.efficiency.settings;

/**
 *
 * @author Andrew
 */
public class ProfessionSettings
{
    
    private int level;
    
    public int getExpertiseLevel()
    {
        return level;
    }
    
    public void setExpertiseLevel(int level)
    {
        this.level = level;
    }
    
    public void incrementExpertiseLevel()
    {
        setExpertiseLevel(getExpertiseLevel() + 1);
    }
}
