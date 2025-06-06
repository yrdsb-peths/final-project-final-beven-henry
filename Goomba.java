import greenfoot.*;

public class Goomba extends SmoothMover {
    private int direction = -1;
    private int speed = 1;

    public Goomba() {
        GreenfootImage img = new GreenfootImage("goomba.png"); 
        img.scale(50, 50);
        setImage(img);
    }

    public void act() {
        move(direction * speed);
        if (getOneObjectAtOffset(direction * getImage().getWidth() / 2, getImage().getHeight() / 2, Platform.class) == null ||
            getOneIntersectingObject(Brick.class) != null) {
            direction *= -1;
        }

        checkMarioCollision();
    }

    private void checkMarioCollision() {
        Mario mario = (Mario) getOneIntersectingObject(Mario.class);
        if (mario != null) {
            int marioBottom = mario.getY() + mario.getImage().getHeight() / 2;
            int goombaTop = getY() - getImage().getHeight() / 2;

        if (marioBottom <= goombaTop + 5) {
            // Mario is stomping
            getWorld().removeObject(this);
            mario.bounce();
        } else {
            // Side/bottom hit — damage Mario
            mario.takeDamage();
            // Optional knockback
            int knockback = 20;
            if (mario.getX() < getX()) {
                mario.setLocation(mario.getX() - knockback, mario.getY());
            } else {
                mario.setLocation(mario.getX() + knockback, mario.getY());
            }
        }
        }
    }

}


