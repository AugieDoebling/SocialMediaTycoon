 

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class ConfirmButton here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ConfirmButton extends Button
{
    /**
     * Act - do whatever the ConfirmButton wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if (Greenfoot.mouseClicked(this)) {
            Settings.soundVol = Slider.value;
            Settings.prevLevel = Settings.soundVol;
            Greenfoot.setWorld(new MainMenu());
        }
    }    
}
