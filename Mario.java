import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Mario here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Mario extends Actor
{
    /**
     * Act - do whatever the Mario wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        int x = getX();
        int y = getY();
        int ySpd = 4;
        int speed = 1;
        if(Greenfoot.isKeyDown("a")){
            move(-speed);
        }
        if(Greenfoot.isKeyDown("d")){
            move(speed);
        }
        if(Greenfoot.isKeyDown("w")){
            setLocation(x, y-1);
        }
        
    }
}
