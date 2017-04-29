import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class ExitIcon here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ExitIcon extends Icon
{
    /**
     * Act - do whatever the ExitIcon wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if (Greenfoot.mouseClicked(this)) {
            System.exit(1);
        }
    }    
}
