import greenfoot.*;

public class MyWorld extends World {
    public MyWorld() {
        super(600, 400, 1, false);
        Mario mario = new Mario();
        addObject(mario, 300, 320);
        Floor floor = new Floor();
        addObject(floor, 300, 430);
        
    }
}
