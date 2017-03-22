import java.util.*;

class Boss {
    public static double x = 0;
    public static double y = 65;
    public static double direction = 0.25;
    public static double health = 200;
    public static List<List<Double>> shots = new ArrayList<List<Double>>(); //x,y,xvel,yvel
    
    public static void draw() {
        StdDraw.setPenColor(0,0,0);
        StdDraw.filledCircle(x,y,30);
        
        StdDraw.setPenColor(255,255,255);
        StdDraw.filledCircle(x-10,y-5,5);
        StdDraw.filledCircle(x+10,y-5,5);
        
        displayHealth();
        move();
    }
    
    public static void displayHealth() {
        StdDraw.setPenColor(200,0,0);
        StdDraw.filledRectangle(health/2-100,-100,health/2.0,5);
    }
    
    public static void move() {
        x += direction;
        if (x > 65 || x < -65) direction *= -1;
    }
    
    public static void attack() {
        
    }
}
