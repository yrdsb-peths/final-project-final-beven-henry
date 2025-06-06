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
    private int hp = 3;
    private Label hpLabel;

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
        updateHpLabel();
        
        bonk();
        
        isFalling();

        fall();
        checkRight();
        checkLeft();
        
        collectCoins();

        
        

        
    }
    public void takeDamage() {
        hp--;
        updateHpLabel();
        if (hp <= 0) {
            getWorld().addObject(new Label("Game Over", 60), getWorld().getWidth() / 2, getWorld().getHeight() / 2);
            Greenfoot.stop();
        }
    }

    public void setHpLabel(Label label) {
        this.hpLabel = label;
        updateHpLabel();
    }

    public void updateHpLabel() {
        if (hpLabel != null) {
            hpLabel.setValue("HP: " + hp);
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
            v = -15;
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
        
        Actor under = getOneObjectAtOffset(0, getImage().getHeight()/2, Platform.class);
        
        return under != null;
    }
    
    public void moveToGround(){
        Actor ground = getOneObjectAtOffset(0, getImage().getHeight()/2, Platform.class);
        int newY = ground.getY() - (ground.getImage().getHeight() + getImage().getHeight())/2 -1;
        setLocation(getX(), newY);
    }
    
    public void isFalling(){
        if(onGround() == false){
            v = v + 1;
            fall();
        }
        if(onGround() == true){
            moveToGround();
            v = 0;
            canJump = true;
        }
        
        
    }
    
    public void checkRight(){
        Actor rightWall = getThingRight();
        if(rightWall != null){
            stopAtRight(rightWall);
        }
    }
    
    public void stopAtRight(Actor right){
        int wallWidth = right.getImage().getWidth();
        int newX = right.getX() - (wallWidth + getImage().getWidth())/2 + 1;
        setLocation(newX, getY());
    }
    
    public Actor getThingRight(){
        int spriteWidth = getImage().getWidth();
        int xDis = spriteWidth/2 + 1;
        return getOneObjectAtOffset(xDis, 0, Platform.class);
    }
    
    public void checkLeft(){
        Actor leftWall = getThingLeft();
        if(leftWall != null){
            stopAtLeft(leftWall);
        }
    }
    
    public void stopAtLeft(Actor left){
        int wallWidth = left.getImage().getWidth();
        int newX = left.getX() + (wallWidth + getImage().getWidth())/2 - 1;
        setLocation(newX, getY());
    }
    
    public Actor getThingLeft(){
        int spriteWidth = getImage().getWidth();
        int xDis = (spriteWidth+1)/2;
        return getOneObjectAtOffset(-xDis, 0, Platform.class);
    }
    
    
    public void collectCoins() {
        if (isTouching(Coin.class)) {
            removeTouching(Coin.class);
            MyWorld.score++;
            updateScoreLabel();
        }
    }

    public void updateScoreLabel() {
        World world = getWorld();
        if (world instanceof MyWorld) {
            MyWorld myWorld = (MyWorld) world;
            myWorld .scoreLabel.setValue("Score: " + MyWorld.score);
        }
    }
    public void bounce() {
        v = -15;
    }


    
    
}
