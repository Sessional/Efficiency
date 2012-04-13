/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.sessional.efficiency.Tree.Talent;

/**
 *
 * @author Andrew
 */
public abstract class Talent
{
    private String name;
    private String description;
    private int ranks;
    private int[] chances;
    
    public Talent(String name, String description, int ranks)
    {
        this.name = name;
        this.description = description;
        this.ranks = ranks;
        chances = new int[ranks];
    }
}
