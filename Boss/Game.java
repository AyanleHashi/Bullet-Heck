import java.util.*;

public class Game {
    public static void initialize() {
        StdDraw.enableDoubleBuffering();
        StdDraw.setScale(-100,100);
        StdDraw.setPenRadius(0.05);
    }
    
    public static void drawWalls() {
        StdDraw.setPenColor(150,150,150);
        
        StdDraw.filledRectangle(0,100,100,5);
        StdDraw.filledRectangle(0,-100,100,5);
        StdDraw.filledRectangle(100,0,5,100);
        StdDraw.filledRectangle(-100,0,5,100);
    }
    
    public static void main(String[] args) {
        initialize();
        Player player = new Player();
        
        
        while (true) {
            StdDraw.clear(StdDraw.DARK_GRAY);
            
            drawWalls();
            Boss.draw();
            player.update();
            
            StdDraw.show();
        }
    }
}
