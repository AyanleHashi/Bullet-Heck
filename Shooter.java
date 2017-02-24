import java.util.*;
import java.awt.event.KeyEvent;

class Player {
    
    public static double xCoord = 0;
    public static double yCoord = 0;
    public static final double playerVelocity = 0.15;
    public static final double projectileVel = 3;
    public static ArrayList<List<Double>> projectiles = new ArrayList<List<Double>>();
    public static ArrayList<List<Double>> projectilesBase = new ArrayList<List<Double>>();
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
        for (int i=0;i<projectiles.size();i++) {
            projectiles.get(i).set(0,projectiles.get(i).get(0) + projectiles.get(i).get(2) * projectileVel);
            projectiles.get(i).set(1,projectiles.get(i).get(1) + projectiles.get(i).get(3) * projectileVel);
            StdDraw.filledCircle(projectiles.get(i).get(0),projectiles.get(i).get(1),2.5);
        }
    }
    
    public static void move() {
        if (StdDraw.isKeyPressed(KeyEvent.VK_D)) xCoord += playerVelocity;
        if (StdDraw.isKeyPressed(KeyEvent.VK_A)) xCoord -= playerVelocity;
        if (StdDraw.isKeyPressed(KeyEvent.VK_W)) yCoord += playerVelocity;
        if (StdDraw.isKeyPressed(KeyEvent.VK_S)) yCoord -= playerVelocity;
        
        if (xCoord >= 97.5) xCoord = 97.5;
        if (xCoord <= -97.5) xCoord = -97.5;
        if (yCoord >= 97.5) yCoord = 97.5;
        if (yCoord <= -97.5) yCoord = -97.5;
    }
    
    public static void update() {
        createProjectiles();
        drawProjectiles();
        move();
        StdDraw.setPenColor(255,125,125);
        StdDraw.filledCircle(xCoord,yCoord,5);
    }
}

class Enemy {
    public static Random random = new Random();
    
    public double xPos;
    public double yPos;
    public double vel = 0.05;
    
    public Enemy() {
        xPos = random.nextInt(200)-100;
        yPos = random.nextInt(200)-100;
    }
    
    public void locatePlayer(Player p) {
        if (p.xCoord > xPos) xPos += vel;
        if (p.xCoord < xPos) xPos -= vel;
        if (p.yCoord > yPos) yPos += vel;
        if (p.yCoord < yPos) yPos -= vel;
    }
    
    public void draw() {
        StdDraw.setPenColor(100,100,100);
        StdDraw.point(xPos,yPos);
    }
    
    public void update(Player p) {
        draw();
        locatePlayer(p);
    }
}

public class Shooter {
    public static ArrayList<Enemy> enemies = new ArrayList<Enemy>();
    public static Player player = new Player();
   
    public static void initialize() {
        StdDraw.enableDoubleBuffering();
        StdDraw.setScale(-100,100);
        StdDraw.setPenRadius(0.05);
    }
    
    public static void createEnemies() {
        for (int i=0;i<10;i++) {
            enemies.add(new Enemy());
        }
    }
    
    public static void updateEnemies() {
        for (int i=0;i<enemies.size();i++) {
            enemies.get(i).update(player);
        }
    }
    
    public static void main(String[] args) {
        initialize();
        
        createEnemies();
        
        while (true) {
           StdDraw.clear(StdDraw.BLACK);
           player.update();
           
           updateEnemies();
           
           StdDraw.show();
        }
    }
}
