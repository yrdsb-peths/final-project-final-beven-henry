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
    public int facing = 0;
    public int imageIndex = 0;
    public int v = 0;
    public boolean dJump = true;
    public int accel = 0;
    public int spd = 7;
    public boolean canJump = true;
    private int hp = 3;
    private Label hpLabel;

    public Mario()
    {
        deltaTimer.mark();
        setImage(idle);
        idle.scale(50, 50);

        for(int i = 0; i < runAnimationRight.length; i++) {
            runAnimationRight[i] = new GreenfootImage("images/runAnimation/runRight" + i + ".png");
            runAnimationRight[i].scale(50, 50);
        }

        for(int i = 0; i < runAnimationLeft.length; i++) {
            runAnimationLeft[i] = new GreenfootImage("images/runAnimation/runRight" + i + ".png");
            runAnimationLeft[i].mirrorHorizontally();
            runAnimationLeft[i].scale(50, 50);
        }
    }
    
    //changes v value
    public void v(int x){
        v = x;
    }
    
    //returns v value
    public int getV(){
        return v;
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
        
        collect();

        
        

        
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
    
    /**
     * movement keys
     * 
     * left key to go left
     * 
     * right key to go right
     * 
     * up key to go up
     */
    
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
            v = -17;
            canJump = false;
            if(dJump == true){
                v = -17;
                dJump = false;
            }
        }
        
        if (!Greenfoot.isKeyDown("left") && !Greenfoot.isKeyDown("right"))
        {
            setImage(idle);
        }
    }
    
    
    //tries to break block when hit
    public void bonk(){
        Actor under = getOneObjectAtOffset(0, getImage().getHeight()/2, Platform.class);
         
            if(isTouching(Platform.class) && v < 0){
            
                removeTouching(Brick.class);
            
                v= -v/3;
            
            }
        
    }
    
    
    //makes actor go down to floor
    public void fall(){
        
        setLocation(getX(), getY() + v);
        
    }
    
    //checks if actor touching ground
    boolean onGround(){
        
        Actor under = getOneObjectAtOffset(0, getImage().getHeight()/2, Platform.class);
        
        return under != null;
    }
    
    //moves to ground when actor touches
    //doesnt fall through
    public void moveToGround(){
        Actor ground = getOneObjectAtOffset(0, getImage().getHeight()/2, Platform.class);
        int newY = ground.getY() - (ground.getImage().getHeight() + getImage().getHeight())/2 -1;
        setLocation(getX(), newY);
    }
    
    
    // checks if falling
    public void isFalling(){
        if(onGround() == false){
            
            v = v + 1;
            fall();
        }
        if(onGround() == true){
            moveToGround();
            v = 0;
            dJump = true;
            canJump = true;
        }
        
        
    }
    
    /**
     * first checks if touching a side of a wall
     * then stops actor to move through
     * checks for most sides
     */
    public void checkRight(){
        Actor rightWall = getThingRight();
        if(rightWall != null){
            stopAtRight(rightWall);
        }
    }
    
    public void stopAtRight(Actor right){
        int wallWidth = right.getImage().getWidth();
        int newX = right.getX() - (wallWidth + getImage().getWidth())/2 ;
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
        int newX = left.getX() + (wallWidth + getImage().getWidth())/2 ;
        setLocation(newX, getY());
    }
    
    public Actor getThingLeft(){
        int spriteWidth = getImage().getWidth();
        int xDis = (spriteWidth+1)/2;
        return getOneObjectAtOffset(-xDis, 0, Platform.class);
    }
    
    /**
     * collects and updates values corresponding to the item
     * coin for score
     * mush for lives
     */
    public void collect() {
        Actor mush = getOneIntersectingObject(Mushroom.class);
        if(isTouching(Coin.class)) {
            removeTouching(Coin.class);
            MyWorld.score++;
            updateScoreLabel();
        }
        if(isTouching(Mushroom.class)){
            removeTouching(Mushroom.class);
            if(!isTouching(Mushroom.class)){
                hp++;
                updateScoreLabel();
            }
            
        }
    }

    public void updateScoreLabel() {
        World world = getWorld();
        if (world instanceof MyWorld) {
            MyWorld myWorld = (MyWorld) world;
            myWorld .scoreLabel.setValue("Score: " + MyWorld.score);
        }
    }
    
    public void bounce(){
        v=-15;
    }

    

    
    
}
