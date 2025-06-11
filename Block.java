import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Block here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Block extends Platform
{
    boolean use = false;
    /**
     * Act - do whatever the Block wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public Block(){
        GreenfootImage i = new GreenfootImage("images/block.png");
        setImage(i);
        i.scale(50, 50);
        
    }
    
    public void spawnMush(){
        getWorld().addObject(new Mushroom(), getX(), getY() - getImage().getHeight());
    }
    
    public void act()
    {
        Mario mario = (Mario) getOneIntersectingObject(Mario.class);
        
        if(use == false){
            if(mario != null && mario.getV() <= 0){
                GreenfootImage used = new GreenfootImage("images/used.png");
                used.scale(50, 50);
                setImage(used);
                spawnMush();
                use = true;
            }
        }
    }
}
