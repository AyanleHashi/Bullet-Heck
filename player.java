import java.util.*;
import java.awt.event.KeyEvent;

class Player {
    public static final double[] coordinates = {0,0};
    public static final double playerVelocity = 0.3;
    
    public static void move() {
        if (StdDraw.isKeyPressed(KeyEvent.VK_RIGHT)) coordinates[0] += playerVelocity;
        if (StdDraw.isKeyPressed(KeyEvent.VK_LEFT)) coordinates[0] -= playerVelocity;
        if (StdDraw.isKeyPressed(KeyEvent.VK_UP)) coordinates[1] += playerVelocity;
        if (StdDraw.isKeyPressed(KeyEvent.VK_DOWN)) coordinates[1] -= playerVelocity;
        
        if (coordinates[0] >= 92.5) coordinates[0] = 92.5;
        if (coordinates[0] <= -92.5) coordinates[0] = -92.5;
        if (coordinates[1] >= 92.5) coordinates[1] = 92.5;
        if (coordinates[1] <= -92.5) coordinates[1] = -92.5;
    }
    
    public static void update() {
        move();
        StdDraw.setPenColor(StdDraw.RED);
        StdDraw.filledRectangle(coordinates[0],coordinates[1],2.5,2.5);
    }
}
