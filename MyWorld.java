import greenfoot.*;


public class MyWorld extends World {
    public Label scoreLabel;
    public static int score = 0;
    public MyWorld() {
        super(700, 400, 1, false);
        
        Mario mario = new Mario();
        addObject(mario, 300, 320);
        
        Floor floor0 = new Floor();
        addObject(floor0, 100, 430);
        
        Floor floor1 = new Floor();
        addObject(floor1, 200, 430);
        
        Floor floor2 = new Floor();
        addObject(floor2, 300, 430);
        
        Floor floor3 = new Floor();
        addObject(floor3, 400, 430);
        
        Floor floor4 = new Floor();
        addObject(floor4, 500, 430);
        
        Floor floor5 = new Floor();
        addObject(floor5, 600, 430);
        
        Brick brick0 = new Brick();
        addObject(brick0, 200, 200);
        
        Brick brick1 = new Brick();
        addObject(brick1, 250, 200);
        
        Coin coin1 = new Coin();
        addObject(coin1, 250, 250);

        Coin coin2 = new Coin();
        addObject(coin2, 350, 250);
        
        scoreLabel = new Label("Score: 0", 30);
        addObject(scoreLabel, 100, 50);


        
    }
}
