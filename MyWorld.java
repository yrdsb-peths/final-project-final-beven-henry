import greenfoot.*;

public class MyWorld extends World {
    public MyWorld() {
        super(600, 400, 1);
        Mario mario = new Mario();
        addObject(mario, 300, 320);
    }
}
