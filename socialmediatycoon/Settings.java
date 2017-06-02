  

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Settings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Settings extends World
{
    public static int prevLevel = 50;
    public static int soundVol = 50;
    
    /**
     * Constructor for objects of class Settings.
     * 
     */
    public Settings()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(960, 540, 1); 
        prepare();
    }
    
     private void prepare(){
        // Top Toolbar
        GreenfootImage topBar = new GreenfootImage("toolbar_topbar.png");
        getBackground().drawImage(topBar, 0, 0);
        
        GreenfootImage settingsText = new GreenfootImage("text_settings.png");
        getBackground().drawImage(settingsText, 438, 7);
        
        GreenfootImage textWelcome = new GreenfootImage("text_welcome.png");
        getBackground().drawImage(textWelcome, 817, 10);
        
        //Toolbar Icons
        ExitIcon exitIcon = new ExitIcon(new MainMenu());
        addObject(exitIcon, 25, 20);
        
        // Settings Window
        GreenfootImage settingsWindow = new GreenfootImage("window_settings.png");
        getBackground().drawImage(settingsWindow, 250, 100);
        
        ConfirmButton confirmButton = new ConfirmButton();
        addObject(confirmButton, 570, 400);
        
        CancelButton cancelButton = new CancelButton();
        addObject(cancelButton, 390, 400);
        
        //GreenfootImage soundBar = new GreenfootImage("image_sound.png");
        //getBackground().drawImage(soundBar, 489, 270);
        
        GreenfootImage fullscreen = new GreenfootImage("text_fullscreen.png");
        getBackground().drawImage(fullscreen, 489, 220);
        
        GreenfootImage resolution = new GreenfootImage("text_resolution.png");
        getBackground().drawImage(resolution, 489, 170);
        
        DropdownButton resDropdown = new DropdownButton();
        addObject(resDropdown, 680, 183);
        
        DropdownButton fullscreenDropdown = new DropdownButton();
        addObject(fullscreenDropdown, 553, 233);
        
        Slider volumeSlider = new Slider(soundVol);
        addObject(volumeSlider, 567, 283);
    }

}
