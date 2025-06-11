import greenfoot.*;


public class MyWorld extends World {
    public Label scoreLabel;
    public static int score = 0;
    public Label hpLabel;
    
    //0 = air, 1 = brick, 2 = ?block
    private int[][] levelLayout = {
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
        {0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 1},
        {0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 1},
        {0, 0, 0, 0, 1, 1, 2, 1, 1, 0, 0, 0, 0, 1},
        {0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1},
        {0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1},
        {0, 0, 1, 1, 0, 0, 0, 0, 0, 1, 1, 0, 0, 1}
    };
    
    public void buildLevel(){
        int tileSize = 50;
        for (int row = 0; row < levelLayout.length; row++) {
            for (int col = 0; col < levelLayout[row].length; col++) {
                int value = levelLayout[row][col];
                int x = col * tileSize + tileSize / 2;
                int y = row * tileSize + tileSize / 2;

                if (value == 1) {
                    addObject(new Brick(), x, y);
                }
                if (value == 2) {
                    addObject(new Block(), x, y);
                }
                if (value == 3) {
                    addObject(new Goomba(), x, y);
                }
            }
        }
    }
    
    public MyWorld() {
        super(700, 400, 1, false);
        buildLevel();
        Mario mario = new Mario();
        addObject(mario, 300, 320);
        
        hpLabel = new Label("HP: 3", 30);
        addObject(hpLabel, 600, 50);
        mario.setHpLabel(hpLabel);
    
        scoreLabel = new Label("Score: 0", 30);
        addObject(scoreLabel, 100, 50);
        
        
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
        
    
        Coin coin1 = new Coin();
        addObject(coin1, 250, 250);

        Coin coin2 = new Coin();
        addObject(coin2, 350, 250);
        
        
        Goomba g = new Goomba();
        addObject(g, 325, 150);



        
         
    
        
    }
    
}
