 

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MainMenu here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MainMenu extends World
{

    /**
     * Constructor for objects of class MainMenu.
     * 
     */
    public MainMenu()
    {  
        // To Set Image: Right click on the name of this class in Green Foot -> Set Image...
        // Create a new world with 2019 x 1320 pixels (default)
        super(960, 540, 1); 
        
        prepare();
    }
    
    /**
     * Prepare the world for the start of the program. That is: create the initial
     * objects and add them to the world.
     */
    private void prepare()
    {
        // Buttons
        NewGameButton newGameButton = new NewGameButton();
        addObject(newGameButton, 310, 250);
        
        AboutButton aboutButton = new AboutButton();
        addObject(aboutButton, 460, 417);
        
        TutorialButton tutorialButton = new TutorialButton();
        addObject(tutorialButton, 310, 332);
        
        LoadGameButton loadGameButton = new LoadGameButton();
        addObject(loadGameButton, 613, 250);
        
        SettingsButton settingsButton = new SettingsButton();
        addObject(settingsButton, 613, 332);
        
        // Toolbar
        GreenfootImage topBar = new GreenfootImage("toolbar_topbar.png"); //the image that is drawed;
        getBackground().drawImage(topBar, 0, 0);
        
        GreenfootImage textMainMenu = new GreenfootImage("text_main_menu.png"); //the image that is drawed;
        getBackground().drawImage(textMainMenu, 435, 7);
        
        GreenfootImage textWelcome = new GreenfootImage("text_welcome.png"); //the image that is drawed;
        getBackground().drawImage(textWelcome, 817, 10);
        
        // Icons
        ExitIcon exitIcon = new ExitIcon(null);
        addObject(exitIcon, 25, 20);
        
        // Text
        GreenfootImage textGameTitle = new GreenfootImage("text_game_title.png"); //the image that is drawed;
        getBackground().drawImage(textGameTitle, 291, 107);
    }
}
