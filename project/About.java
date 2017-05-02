package project;

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class About here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class About extends World
{

    /**
     * Constructor for objects of class About.
     * 
     */
    public About()
    {    
        super(960, 540, 1); 
        
        prepare();
    }
    
    public void prepare()
    {
        // Icons
        ExitIcon exitIcon = new ExitIcon(new MainMenu());
        addObject(exitIcon, 25, 20);
    }
}
