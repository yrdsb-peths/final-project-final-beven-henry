import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class brick here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Brick extends Platform
{
    /**
     * Act - do whatever the brick wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    //hi
    public Brick(){
        GreenfootImage i = new GreenfootImage("images/brick.png");
        setImage(i);
        i.scale(50, 50);
    }
    public void act()
    {
        
    }
}
