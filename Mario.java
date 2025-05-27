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
    GreenfootImage[] runAnimationLeft = new GreenfootImage[4];
    GreenfootImage[] runAnimationRight = new GreenfootImage[4];
    SimpleTimer deltaTimer = new SimpleTimer();
    int facing = 0;
    int imageIndex = 0;
    int v = 0;
    int accel = 0;
    int spd = 7;
    boolean canJump = true;
    public Mario()
    {
        deltaTimer.mark();
        setImage(idle);
        idle.scale(70, 70);

        for(int i = 0; i < runAnimationRight.length; i++) {
            runAnimationRight[i] = new GreenfootImage("images/runAnimation/runRight" + i + ".png");
            runAnimationRight[i].scale(70, 70);
        }

        for(int i = 0; i < runAnimationLeft.length; i++) {
            runAnimationLeft[i] = new GreenfootImage("images/runAnimation/runRight" + i + ".png");
            runAnimationLeft[i].mirrorHorizontally();
            runAnimationLeft[i].scale(70, 70);
        }
    }
    public void act()
    {
        movement();
        
        bonk();
        
        isFalling();
        
        
        
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
    
    public void movement(){
        if(Greenfoot.isKeyDown("left") ) 
        {
            setLocation(getX() - spd, getY());
            facing = 1;
            runAnimation();
        } 
        else if(Greenfoot.isKeyDown("right")) 
        {
            setLocation(getX() + spd, getY());
            facing = 0;
            runAnimation();
        }
        
        if(Greenfoot.isKeyDown("up") && canJump == true){
            v = -21;
            canJump = false;
        }
        
        if (!Greenfoot.isKeyDown("left") && !Greenfoot.isKeyDown("right"))
        {
            setImage(idle);
        }
    }
    
    
    
    public void bonk(){
        if(isTouching(Brick.class) && v < 0   ){
            
            removeTouching(Brick.class);
            v = -v/3;
            
        }
    }
    
    public void fall(){
        
            setLocation(getX(), getY() + v);
        
    }
    
    boolean onGround(){
        Actor under0 = getOneObjectAtOffset(0, getImage().getHeight()/2, Floor.class);
        Actor under = getOneObjectAtOffset(0, getImage().getHeight()/2, Brick.class);
        
        return under0 != null || under != null;
    }
    
    
    
    public void isFalling(){
        if(onGround() == false){
            v = v + 1;
            fall();
        }
        if(onGround() == true){
            setLocation(getX(), getY() -1 );
            v = 0;
            canJump = true;
        }
        
        
    }
    
    
    
}
