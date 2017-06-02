import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Slider here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Slider extends Actor
{
    private GreenfootImage background, slide_button;
    public static int value; 
    private int maxValue, valueWidth, valueX, rightValue;
    private float pixelsPerValue;
    
    public Slider(int value) {
        background = new GreenfootImage("image_sound.png");
        slide_button = new GreenfootImage("slider_selector.png");
        maxValue = 100;
        valueWidth = 127;
        rightValue = 127;
        valueX = 0;
        this.value = value;
        pixelsPerValue = (float) rightValue / maxValue;
        background.drawImage(slide_button, valueToPos(value), 0);
        setImage(background);
    }
    
    public void addedToWorld(World world) {
        updateImage();
        valueX = getX() * getWorld().getCellSize() - valueWidth/2;
    }
    
    /**
     * Act - do whatever the Slider wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
       if(Greenfoot.mouseDragged(this) || Greenfoot.mousePressed(this)) {
           MouseInfo mouseInfo = Greenfoot.getMouseInfo();
           int x = Math.max(valueX, mouseInfo.getX());
           x -= valueX;
           x = Math.min(x, rightValue);
          
           setValue(posToValue(x));
           updateImage();
           MainMenu.bg_music.setVolume(this.value);
       }
    }    
    
    public int posToValue(int x){
        int v = Math.round(x / pixelsPerValue);
        return v;
    }
    
    public int valueToPos(int v){
        int x = Math.round(v * pixelsPerValue);
        return x;
    }
    
    public void setValue(int value){
        if(value < 0){
            value = 0;
        }
        else if(value > maxValue){
            value = maxValue;
        }
        this.value = value;
    }
    
    public void updateImage(){
        GreenfootImage image = new GreenfootImage("image_sound.png");
        image.drawImage(slide_button, valueToPos(value), 0);
        setImage(image);
    }
}
