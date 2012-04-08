/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.com.sessional.efficiency.chatwindows;

/**
 *
 * @author Andrew
 */
public class Option
{
    private String text;
    private int hotkey;
    private ChatWindow linkedWindow;
    
    public Option(int hotkey, String text, ChatWindow linkedWindow)
    {
        this.hotkey = hotkey;
        this.text = text;
        this.linkedWindow = linkedWindow;
    }
    
    public String getName()
    {
        return text;
    }
    
    public int getHotkey()
    {
        return hotkey;
    }
    
    public ChatWindow getLinkedWindow()
    {
        return linkedWindow;
    }
}
