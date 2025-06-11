import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Mushroom here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Mushroom extends Actor
{
    /**
     * Act - do whatever the Mushroom wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public Mushroom(){
        GreenfootImage i = new GreenfootImage("images/mushroom.png");
        setImage(i);
        i.scale(50, 50);
    }
    
    public void act()
    {
        // Add your action code here.
    }
}
