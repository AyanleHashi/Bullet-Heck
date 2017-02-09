import java.util.*;
import java.awt.event.KeyEvent;

public class Shield {
    
    public static int[] shieldLoc = {0,10,10,1,0};
    public static ArrayList<List<Double>> arrows = new ArrayList<List<Double>>();
    public static Random random = new Random();
    public static double[][] arrowStates = {{0,100,1,5,0},
                                            {100,0,5,1,1},
                                            {0,-100,1,5,2},
                                            {-100,0,5,1,3}};
    public static void initialize() {
        StdDraw.enableDoubleBuffering();
        StdDraw.setScale(-100,100);
        StdDraw.setPenRadius(0.05);
        StdDraw.setPenColor(StdDraw.RED);
    }
    
    public static void playerShield() {
        if (StdDraw.isKeyPressed(KeyEvent.VK_RIGHT)) shieldLoc = new int[]{10,0,1,10,1};
        if (StdDraw.isKeyPressed(KeyEvent.VK_LEFT)) shieldLoc = new int[]{-10,0,1,10,3};
        if (StdDraw.isKeyPressed(KeyEvent.VK_UP)) shieldLoc = new int[]{0,10,10,1,0};
        if (StdDraw.isKeyPressed(KeyEvent.VK_DOWN)) shieldLoc = new int[]{0,-10,10,1,2};
        
        StdDraw.setPenColor(0,150,255);
        StdDraw.filledRectangle(shieldLoc[0],shieldLoc[1],shieldLoc[2],shieldLoc[3]);
    }
    
    public static void generateRandomArrow() {
        ArrayList<Double> r = new ArrayList<Double>();
        double rand = random.nextInt(4);
        for (int i=0;i<5;i++) {
            r.add(arrowStates[(int)rand][i]);
        }
        arrows.add(r);
    }
    
    public static void updateArrows() {
        for (int i=0;i<arrows.size();i++) {
            if (arrows.get(i).get(4) == 0.0) arrows.get(i).set(1,arrows.get(i).get(1)-0.05);
            if (arrows.get(i).get(4) == 1.0) arrows.get(i).set(0,arrows.get(i).get(0)-0.05);
            if (arrows.get(i).get(4) == 2.0) arrows.get(i).set(1,arrows.get(i).get(1)+0.05);
            if (arrows.get(i).get(4) == 3.0) arrows.get(i).set(0,arrows.get(i).get(0)+0.05);
            StdDraw.filledRectangle(arrows.get(i).get(0),arrows.get(i).get(1),arrows.get(i).get(2),arrows.get(i).get(3));
        }
    }
    
    public static void checkCollision() {
        for (int i=0;i<arrows.size();i++) {
            /*if (arrows.get(i).get(4) == shieldLoc[4] && shieldLoc[4] == 0 && arrows.get(i).get(1) < 15) arrows.remove(i);
            if (arrows.get(i).get(4) == shieldLoc[4] && shieldLoc[4] == 1 && arrows.get(i).get(0) < 15) arrows.remove(i);
            if (arrows.get(i).get(4) == shieldLoc[4] && shieldLoc[4] == 2 && arrows.get(i).get(1) < 15) arrows.remove(i);
            if (arrows.get(i).get(4) == shieldLoc[4] && shieldLoc[4] == 3 && arrows.get(i).get(0) < 15) arrows.remove(i);*/
        }
    }
    
    public static void main(String[] args) {
        initialize();
        int genCount = 0;
        int arrowDelay = 205;
        
        while (true) {
            StdDraw.clear(StdDraw.BLACK);
            if (genCount == arrowDelay) generateRandomArrow();
            updateArrows();
            checkCollision();
            
            playerShield();
            StdDraw.setPenColor(StdDraw.GREEN);
            StdDraw.filledRectangle(0,0,2.5,2.5);
            
            StdDraw.show();
            genCount++;
            if (genCount > arrowDelay) genCount = 0;
        }
    }
}
