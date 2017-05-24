import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class NextButton here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class NextButton extends Button
{

    /**
     * Act - do whatever the ExitIcon wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    
    public void act() 
    {
       if (Greenfoot.mouseClicked(this)) {
            Greenfoot.setWorld(new Tutorial2());
        }
        // Add your action code here.
    }    
}
