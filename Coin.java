import greenfoot.*;

public class Coin extends Actor {
    public Coin() {
        GreenfootImage img = new GreenfootImage("coin.png"); // make sure this image exists in your images folder
        img.scale(30, 30);
        setImage(img);
    }

    public void act() {
        setRotation(getRotation() + 5); 
    }
}
