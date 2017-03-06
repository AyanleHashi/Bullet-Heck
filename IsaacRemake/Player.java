import java.util.*;
import java.awt.event.KeyEvent;
import java.awt.Font;

class Player {
    
    public static double xPos = 0;
    public static double yPos = 0;
    public static double health = 3;
    public static final double playerVelocity = 0.15;
    public static final double projectileVel = 3;
    public static ArrayList<List<Double>> projectiles = new ArrayList<List<Double>>();
    public static final int initialDelay = 200;
    public static int delay = initialDelay;
    
    public static Player p = new Player();
    
    public static int iFrames = 0;
    
    public static void createProjectiles() {
        ArrayList<Double> inner = new ArrayList<Double>();
        if (StdDraw.isKeyPressed(KeyEvent.VK_RIGHT) && delay <= 0) {
            inner.add(xPos);
            inner.add(yPos);
            inner.add(0.1);
            inner.add(0.0);
            projectiles.add(inner);
            delay = initialDelay;
        }
        else if (StdDraw.isKeyPressed(KeyEvent.VK_LEFT) && delay <= 0) {
            inner.add(xPos);
            inner.add(yPos);
            inner.add(-0.1);
            inner.add(0.0);
            projectiles.add(inner);
            delay = initialDelay;
        }
        else if (StdDraw.isKeyPressed(KeyEvent.VK_UP) && delay <= 0) {
            inner.add(xPos);
            inner.add(yPos);
            inner.add(0.0);
            inner.add(0.1);
            projectiles.add(inner);
            delay = initialDelay;
        }
        else if (StdDraw.isKeyPressed(KeyEvent.VK_DOWN) && delay <= 0) {
            inner.add(xPos);
            inner.add(yPos);
            inner.add(0.0);
            inner.add(-0.1);
            projectiles.add(inner);
            delay = initialDelay;
        }
        delay--;
    }
    
    public static void drawProjectiles() {
        StdDraw.setPenColor(230,230,230);
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
        if (StdDraw.isKeyPressed(KeyEvent.VK_D)) xPos += playerVelocity;
        if (StdDraw.isKeyPressed(KeyEvent.VK_A)) xPos -= playerVelocity;
        if (StdDraw.isKeyPressed(KeyEvent.VK_W)) yPos += playerVelocity;
        if (StdDraw.isKeyPressed(KeyEvent.VK_S)) yPos -= playerVelocity;
        
        if (xPos >= 75) xPos = 75;
        if (xPos <= -75) xPos = -75;
        if (yPos >= 75) yPos = 75;
        if (yPos <= -75) yPos = -75;
    }
    
    public static void flicker() {
        if (iFrames > 0) {
            StdDraw.setPenColor(255,0,0);
            StdDraw.filledCircle(xPos,yPos,5.1);
        }
    }
    
    public static void takeDamage(boolean taken) {
        if (taken && iFrames < 0) {
            iFrames = 500;
            health -= 0.5;
            System.out.println(health);
        } 
    }
    
    public static void update() {
        createProjectiles();
        drawProjectiles();
        move();
        StdDraw.setPenColor(255,125,125);
        StdDraw.filledCircle(xPos,yPos,5);
        flicker();
        iFrames--;
    }
}
