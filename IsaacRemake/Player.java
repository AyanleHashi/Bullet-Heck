import java.util.*;
import java.awt.event.KeyEvent;
import java.awt.Font;

class Player {
    
    public static double xCoord = 0;
    public static double yCoord = 0;
    public static final double playerVelocity = 0.15;
    public static final double projectileVel = 3;
    public static ArrayList<List<Double>> projectiles = new ArrayList<List<Double>>();
    public static final int initialDelay = 150;
    public static int delay = initialDelay;
    
    public static Player p = new Player();
    
    public static void createProjectiles() {
        ArrayList<Double> inner = new ArrayList<Double>();
        if (StdDraw.isKeyPressed(KeyEvent.VK_RIGHT) && delay <= 0) {
            inner.add(xCoord);
            inner.add(yCoord);
            inner.add(0.1);
            inner.add(0.0);
            projectiles.add(inner);
            delay = initialDelay;
        }
        else if (StdDraw.isKeyPressed(KeyEvent.VK_LEFT) && delay <= 0) {
            inner.add(xCoord);
            inner.add(yCoord);
            inner.add(-0.1);
            inner.add(0.0);
            projectiles.add(inner);
            delay = initialDelay;
        }
        else if (StdDraw.isKeyPressed(KeyEvent.VK_UP) && delay <= 0) {
            inner.add(xCoord);
            inner.add(yCoord);
            inner.add(0.0);
            inner.add(0.1);
            projectiles.add(inner);
            delay = initialDelay;
        }
        else if (StdDraw.isKeyPressed(KeyEvent.VK_DOWN) && delay <= 0) {
            inner.add(xCoord);
            inner.add(yCoord);
            inner.add(0.0);
            inner.add(-0.1);
            projectiles.add(inner);
            delay = initialDelay;
        }
        delay--;
    }
    
    public static void drawProjectiles() {
        StdDraw.setPenColor(StdDraw.WHITE);
        for (int i=projectiles.size()-1;i>=0;i--) {
            projectiles.get(i).set(0,projectiles.get(i).get(0) + projectiles.get(i).get(2) * projectileVel);
            projectiles.get(i).set(1,projectiles.get(i).get(1) + projectiles.get(i).get(3) * projectileVel);
            
            StdDraw.filledCircle(projectiles.get(i).get(0),projectiles.get(i).get(1),2.5);
            
            if (projectiles.get(i).get(0) >= 78) projectiles.remove(i);
            else if (projectiles.get(i).get(0) <= -78) projectiles.remove(i);
            else if (projectiles.get(i).get(1) >= 78) projectiles.remove(i);
            else if (projectiles.get(i).get(1) <= -78) projectiles.remove(i);
        }
    }
    
    public static void move() {
        if (StdDraw.isKeyPressed(KeyEvent.VK_D)) xCoord += playerVelocity;
        if (StdDraw.isKeyPressed(KeyEvent.VK_A)) xCoord -= playerVelocity;
        if (StdDraw.isKeyPressed(KeyEvent.VK_W)) yCoord += playerVelocity;
        if (StdDraw.isKeyPressed(KeyEvent.VK_S)) yCoord -= playerVelocity;
        
        if (xCoord >= 75) xCoord = 75;
        if (xCoord <= -75) xCoord = -75;
        if (yCoord >= 75) yCoord = 75;
        if (yCoord <= -75) yCoord = -75;
    }
    
    public static void update() {
        createProjectiles();
        drawProjectiles();
        move();
        StdDraw.setPenColor(255,125,125);
        StdDraw.filledCircle(xCoord,yCoord,5);
    }
}
