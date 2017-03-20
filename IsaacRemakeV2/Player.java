import java.util.*;
import java.awt.event.KeyEvent;
import java.awt.Font;

class Player {

    public static double xPos = 0;
    public static double yPos = 0;
    public static int health = 3;
    public static int coins = 0;
    public static double attack = 2;

    public static final double playerVelocity = 0.20;
    public static final double projectileVel = 3;
    public static ArrayList<List<Double>> projectiles = new ArrayList<List<Double>>();

    public static final int initialDelay = 150;
    public static int delay = initialDelay;

    public static double wallSize = 75;

    public static int iFrames = 0;

    public static int roomX = 4;
    public static int roomY = 4;

    public static ArrayList<Item> items = new ArrayList<Item>();

    public Font font;
    public Player() {
        font = new Font("Arial", Font.BOLD, 25);
        StdDraw.setFont(font);
    }

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

            StdDraw.filledCircle(projectiles.get(i).get(0),projectiles.get(i).get(1),attack);

            if (projectiles.get(i).get(0) >= wallSize+4) projectiles.remove(i);
            else if (projectiles.get(i).get(0) <= -wallSize-4) projectiles.remove(i);
            else if (projectiles.get(i).get(1) >= wallSize+4) projectiles.remove(i);
            else if (projectiles.get(i).get(1) <= -wallSize-4) projectiles.remove(i);
        }
    }

    public static void move() {
        if (StdDraw.isKeyPressed(KeyEvent.VK_D)) xPos += playerVelocity;
        if (StdDraw.isKeyPressed(KeyEvent.VK_A)) xPos -= playerVelocity;
        if (StdDraw.isKeyPressed(KeyEvent.VK_W)) yPos += playerVelocity;
        if (StdDraw.isKeyPressed(KeyEvent.VK_S)) yPos -= playerVelocity;

        if (xPos >= wallSize) xPos = wallSize;
        if (xPos <= -wallSize) xPos = -wallSize;
        if (yPos >= wallSize) yPos = wallSize;
        if (yPos <= -wallSize) yPos = -wallSize;
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
            health -= 1;
        }
    }

    public static void displayHealth() {
        StdDraw.setPenColor(255,0,0);
        for (double i=0;i<health;i++) {
            StdDraw.filledCircle(i*9-95,95,4);
        }

        StdDraw.setPenColor(255,215,0);
        StdDraw.text(-95,70,Integer.toString(coins));
    }

    public static void update() {
        createProjectiles();
        drawProjectiles();
        move();
        StdDraw.setPenColor(255,125,125);
        StdDraw.filledCircle(xPos,yPos,5);
        flicker();
        displayHealth();
        iFrames--;
    }
}
