import java.util.*;
import java.awt.event.KeyEvent;
import java.awt.Font;

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

public class BulletHeck {
    
    public static ArrayList<List<Double>> l = new ArrayList<List<Double>>();
    public static double[] possibleDirections = {0.4,0.6,0.4,0.6};
    public static Random rand = new Random();
    public static int[] goldCoords = {rand.nextInt(200)-100,rand.nextInt(200-100)};
    public static int score = 0;
    public static int leniencyValue = 5;
    
    public static void initialize() {
        StdDraw.enableDoubleBuffering();
        StdDraw.setScale(-100,100);
        StdDraw.setPenRadius(0.05);
        Font font = new Font("Arial", Font.BOLD, 25);
        StdDraw.setFont(font);
    }
    
    public static void drawBorder() {
        StdDraw.setPenColor(StdDraw.WHITE);
        StdDraw.filledRectangle(-100,0,5,100);
        StdDraw.filledRectangle(100,0,5,100);
        StdDraw.filledRectangle(0,-100,100,5);
        StdDraw.filledRectangle(0,100,100,5);
    }
    
    public static void createBullets() {
        ArrayList<Double> b;
        double velocity = 0.5;
        double count = 100.0;
        int displayX = rand.nextInt();
        
        for (double i=0.0;i<count;i++) {
            b = new ArrayList<Double>();
            b.add(100*Math.sin(i));
            b.add(100*Math.cos(i));
            b.add(0.25 * possibleDirections[(int)i%4]);
            b.add(-0.25 * possibleDirections[(int)i%4]);
            l.add(b);
        }
    }
    
    public static void drawBullets() {
        for (int i=0;i<l.size();i++) {
            l.get(i).set(0,l.get(i).get(0) + l.get(i).get(2));
            l.get(i).set(1,l.get(i).get(1) + l.get(i).get(3));
            
            if (l.get(i).get(0) > 100) l.get(i).set(2,-l.get(i).get(2));
            if (l.get(i).get(1) > 100) l.get(i).set(3,-l.get(i).get(3));
            
            if (l.get(i).get(0) < -100) l.get(i).set(2,-l.get(i).get(2));
            if (l.get(i).get(1) < -100) l.get(i).set(3,-l.get(i).get(3));
            
            StdDraw.setPenColor(StdDraw.WHITE);
            StdDraw.point(l.get(i).get(0),l.get(i).get(1));
        }
    }
    
    public static void drawGold(Player character) {
        StdDraw.setPenColor(StdDraw.YELLOW);
        StdDraw.filledRectangle(goldCoords[0],goldCoords[1],2,2);
        
        if (goldCoords[0]-10 < character.coordinates[0] && 
            character.coordinates[0] < goldCoords[0] + 10 &&
            goldCoords[1]-10 < character.coordinates[1] && 
            character.coordinates[1] < goldCoords[1] + 10) {
            goldCoords = new int[]{rand.nextInt(200)-100,rand.nextInt(200)-100};
            score++;
        } else;
    }
    
    public static void checkDeath(Player character) {
        for (int i=0;i<l.size();i++) {
            if (l.get(i).get(0)-leniencyValue < character.coordinates[0] && 
            character.coordinates[0] < l.get(i).get(0) + leniencyValue &&
            l.get(i).get(1)-leniencyValue < character.coordinates[1] && 
            character.coordinates[1] < l.get(i).get(1) + leniencyValue) System.exit(0);
        }
    }
    
    public static void main(String[] args) {
        createBullets();
        initialize();
        
        Player character = new Player();
                
        while (true) {
            StdDraw.clear(StdDraw.BLACK);
            character.update();
            drawBorder();
            drawBullets();
            drawGold(character);
            checkDeath(character);
            StdDraw.text(-90,85,Integer.toString(score));
            
            StdDraw.show();
        }
    }
}
