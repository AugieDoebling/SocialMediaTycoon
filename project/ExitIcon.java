 

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class ExitIcon here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ExitIcon extends Icon
{
    World page = null;
    /**
     * Act - do whatever the ExitIcon wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public ExitIcon(World page)
    {
        this.page = page;
    }
    
    public void act() 
    {
        if (Greenfoot.mouseClicked(this)) {
//<<<<<<< HEAD
            if(page == null)
            {
                System.exit(0);
            }
            else
            {
                Greenfoot.setWorld(page);
            }
//=======
            System.exit(0);
//>>>>>>> FETCH_HEAD
        }
    }    
}
