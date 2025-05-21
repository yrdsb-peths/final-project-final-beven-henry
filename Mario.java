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
    GreenfootImage idle = new GreenfootImage("marioidle.png");
    GreenfootImage[] runAnimationLeft = new GreenfootImage[3];
    GreenfootImage[] runAnimationRight = new GreenfootImage[3];
    SimpleTimer deltaTimer = new SimpleTimer();
    int facing = 0;
    int imageIndex = 0;
    public Mario()
    {
        deltaTimer.mark();
        setImage(idle);
        idle.scale(100, 100);

        for(int i = 1; i < runAnimationRight.length; i++) {
            runAnimationRight[i] = new GreenfootImage("images/runAnimation/runRight" + i + ".png");
            runAnimationRight[i].scale(100, 100);
        }

        for(int i = 1; i < runAnimationLeft.length; i++) {
            runAnimationLeft[i] = new GreenfootImage("images/runAnimation/runRight" + i + ".png");
            runAnimationLeft[i].mirrorHorizontally();
            runAnimationLeft[i].scale(100, 100);
        }
    }
    public void act()
    {

        if(Greenfoot.isKeyDown("left")) 
        {
            move(-5);
            facing = 1;
            runAnimation();
        } 
        else if(Greenfoot.isKeyDown("right")) 
        {
            move(5);
            facing = 0;
            runAnimation();
        }
        
        if (!Greenfoot.isKeyDown("left") && !Greenfoot.isKeyDown("right"))
        {
            setImage(idle);
        }
    }
    public void runAnimation() 
    {
        if (deltaTimer.millisElapsed() > 250)
            {
            if(facing == 0)
            {
                setImage(runAnimationRight[imageIndex]);
            }
            if(facing == 1)
            {
                setImage(runAnimationLeft[imageIndex]);
            }
            imageIndex = (imageIndex + 1) % runAnimationRight.length;
            
            deltaTimer.mark();
        }

    }
}
