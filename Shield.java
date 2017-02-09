import java.util.*;
import java.awt.event.KeyEvent;

public class Shield {
    
    public static int[] shieldLoc = {0,10,10,1};
    public static ArrayList<List<Double>> a = new ArrayList<List<Double>>();
    
    public static void initialize() {
        StdDraw.enableDoubleBuffering();
        StdDraw.setScale(-100,100);
        StdDraw.setPenRadius(0.05);
        StdDraw.setPenColor(StdDraw.RED);
    }
    
    public static void playerShield() {
        if (StdDraw.isKeyPressed(KeyEvent.VK_RIGHT)) shieldLoc = new int[]{10,0,1,10};
        if (StdDraw.isKeyPressed(KeyEvent.VK_LEFT)) shieldLoc = new int[]{-10,0,1,10};
        if (StdDraw.isKeyPressed(KeyEvent.VK_UP)) shieldLoc = new int[]{0,10,10,1};
        if (StdDraw.isKeyPressed(KeyEvent.VK_DOWN)) shieldLoc = new int[]{0,-10,10,1};
        
        StdDraw.setPenColor(0,150,255);
        StdDraw.filledRectangle(shieldLoc[0],shieldLoc[1],shieldLoc[2],shieldLoc[3]);
    }
    
    public static void main(String[] args) {
        initialize();
        
        while (true) {
            StdDraw.clear(StdDraw.BLACK);
            playerShield();
            StdDraw.setPenColor(StdDraw.GREEN);
            StdDraw.filledRectangle(0,0,2.5,2.5);
            StdDraw.show();
        }
    }
}
