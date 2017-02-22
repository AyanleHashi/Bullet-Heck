import java.util.*;
import java.awt.event.KeyEvent;

class Player {
    
    public static double[] coordinates = {0,0};
    public static final double playerVelocity = 0.15;
    public static final double projectileVel = 3;
    public static ArrayList<List<Double>> projectiles = new ArrayList<List<Double>>();
    public static ArrayList<List<Double>> projectilesBase = new ArrayList<List<Double>>();
    public static final int initialDelay = 200;
    public static int delay = initialDelay;
    
    public static Player p = new Player();
    
    public static void createProjectiles() {
        ArrayList<Double> inner = new ArrayList<Double>();
        if (StdDraw.isKeyPressed(KeyEvent.VK_RIGHT) && delay <= 0) {
            inner.add(coordinates[0]);
            inner.add(coordinates[1]);
            inner.add(0.1);
            inner.add(0.0);
            projectiles.add(inner);
            delay = initialDelay;
        }
        else if (StdDraw.isKeyPressed(KeyEvent.VK_LEFT) && delay <= 0) {
            inner.add(coordinates[0]);
            inner.add(coordinates[1]);
            inner.add(-0.1);
            inner.add(0.0);
            projectiles.add(inner);
            delay = initialDelay;
        }
        else if (StdDraw.isKeyPressed(KeyEvent.VK_UP) && delay <= 0) {
            inner.add(coordinates[0]);
            inner.add(coordinates[1]);
            inner.add(0.0);
            inner.add(0.1);
            projectiles.add(inner);
            delay = initialDelay;
        }
        else if (StdDraw.isKeyPressed(KeyEvent.VK_DOWN) && delay <= 0) {
            inner.add(coordinates[0]);
            inner.add(coordinates[1]);
            inner.add(0.0);
            inner.add(-0.1);
            projectiles.add(inner);
            delay = initialDelay;
        }
        delay--;
    }
    
    public static void drawProjectiles() {
        StdDraw.setPenColor(StdDraw.WHITE);
        for (int i=0;i<projectiles.size();i++) {
            projectiles.get(i).set(0,projectiles.get(i).get(0) + projectiles.get(i).get(2) * projectileVel);
            projectiles.get(i).set(1,projectiles.get(i).get(1) + projectiles.get(i).get(3) * projectileVel);
            StdDraw.filledCircle(projectiles.get(i).get(0),projectiles.get(i).get(1),2.5);
        }
    }
    
    public static void move() {
        if (StdDraw.isKeyPressed(KeyEvent.VK_D)) coordinates[0] += playerVelocity;
        if (StdDraw.isKeyPressed(KeyEvent.VK_A)) coordinates[0] -= playerVelocity;
        if (StdDraw.isKeyPressed(KeyEvent.VK_W)) coordinates[1] += playerVelocity;
        if (StdDraw.isKeyPressed(KeyEvent.VK_S)) coordinates[1] -= playerVelocity;
        
        if (coordinates[0] >= 97.5) coordinates[0] = 97.5;
        if (coordinates[0] <= -97.5) coordinates[0] = -97.5;
        if (coordinates[1] >= 97.5) coordinates[1] = 97.5;
        if (coordinates[1] <= -97.5) coordinates[1] = -97.5;
    }
    
    public static void update() {
        createProjectiles();
        drawProjectiles();
        move();
        StdDraw.setPenColor(255,125,125);
        StdDraw.filledCircle(coordinates[0],coordinates[1],5);
    }
}

class Enemy {
    public static ArrayList<List<Double>> enemies = new ArrayList<List<Double>>();
    //Format: [xPos,yPos,health]
    public static Random random = new Random();
    
    public static void createEnemy() {
        
    }
}

public class Shooter {
   
   public static void initialize() {
        StdDraw.enableDoubleBuffering();
        StdDraw.setScale(-100,100);
        StdDraw.setPenRadius(0.05);
   }
    
    public static void main(String[] args) {
        initialize();
        Player player = new Player();
        
        Enemy enemy = new Enemy();
        System.out.println(enemy.enemies);
        
        while (true) {
           StdDraw.clear(StdDraw.BLACK);
           player.update();
           StdDraw.show();
        }
    }
}
