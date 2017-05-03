import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Upgrade here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Upgrade extends World
{

    /**
     * Constructor for objects of class Upgrade.
     * 
     */
    public Upgrade()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(960, 540, 1); 
        prepare();
    }
    
     private void prepare(){
        // Top Toolbar
        GreenfootImage topBar = new GreenfootImage("toolbar_topbar.png"); //the image that is drawed;
        getBackground().drawImage(topBar, 0, 0);
        
        GreenfootImage textUpgrade = new GreenfootImage("text_upgrade.png"); //the image that is drawed;
        getBackground().drawImage(textUpgrade, 400, 7);
        
        GreenfootImage textUsers = new GreenfootImage("text_users.png"); //the image that is drawed;
        getBackground().drawImage(textUsers, 783, 10);
        
        GreenfootImage textMoney = new GreenfootImage("text_money.png"); //the image that is drawed;
        getBackground().drawImage(textMoney, 871, 10);
        
        //Toolbar Icons
        ExitIcon exitIcon = new ExitIcon(null);
        addObject(exitIcon, 25, 20);
        
        SettingsIcon settingsIcon = new SettingsIcon();
        addObject(settingsIcon, 93, 20);
        
        PauseIcon pauseIcon = new PauseIcon();
        addObject(pauseIcon, 58, 20);
        
        //Bottom toolbar
        GreenfootImage bottomBar = new GreenfootImage("toolbar_bottom.png");
        getBackground().drawImage(bottomBar, 0, 482);
        
        ReachButton reachButton = new ReachButton();
        addObject(reachButton, 105, 510);
        
        ActivityButton activityButton = new ActivityButton();
        addObject(activityButton, 355, 510);
        
        UpgradeButton upgradeButton = new UpgradeButton();
        addObject(upgradeButton, 610, 510);
        
        TweetButton tweetButton = new TweetButton();
        addObject(tweetButton, 850, 510);
        
        //Upgrade Windows
        GreenfootImage item1 = new GreenfootImage("window_item1.png");
        getBackground().drawImage(item1, 82, 100);
        
        GreenfootImage item2 = new GreenfootImage("window_item2.png");
        getBackground().drawImage(item2, 379, 100);
        
        GreenfootImage item3 = new GreenfootImage("window_item3.png");
        getBackground().drawImage(item3, 670, 100);
        
        PurchaseButton purchaseButton = new PurchaseButton();
        addObject(purchaseButton, 480, 400);
    }
}
