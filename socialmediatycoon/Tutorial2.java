import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Tutorial here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Tutorial2 extends World
{

    /**
     * Constructor for objects of class Tutorial.
     * 
     */
    public Tutorial2()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(960, 540, 1); 
        
        prepare();
    }
    
      private void prepare()
    {
        // Buttons
        NextButtonBlur nextPage = new NextButtonBlur(); //right arrow for tutorial
        addObject(nextPage, 630, 470);
        
        PreviousButton previousPage = new PreviousButton(); //right arrow for tutorial
        addObject(previousPage, 340, 470);
        
        // Toolbar
        GreenfootImage topBar = new GreenfootImage("toolbar_topbar.png");//the image that is drawed;
        getBackground().drawImage(topBar, 0, 0);
        
        GreenfootImage textTutorial = new GreenfootImage("text_tutorial.png");//the image that is drawed;
        getBackground().drawImage(textTutorial, 460, 7);
        
        // Icons
        ExitIcon exitIcon = new ExitIcon(new MainMenu());
        addObject(exitIcon, 25, 20);
        
        // Text
        GreenfootImage step4 = new GreenfootImage("Step4.png"); // tutorial step 1
        getBackground().drawImage(step4, 100, 100);
        
        GreenfootImage step5 = new GreenfootImage("Step5.png"); // tutorial step 2
        getBackground().drawImage(step5, 395, 100);
        
        //GreenfootImage step3 = new GreenfootImage("Step3.png"); // tutorial step 3
        //getBackground().drawImage(step3, 680, 100);
        
        GreenfootImage rightArrow = new GreenfootImage("right_arrow.png"); //right arrow for tutorial
        getBackground().drawImage(rightArrow, 315, 250);
        //getBackground().drawImage(rightArrow, 608, 250);
        //getBackground().drawImage(rightArrow, 890, 250);
        
       
    }
}
