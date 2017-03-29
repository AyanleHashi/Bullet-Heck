import java.util.*;

class Boss {
    public static double x = 0;
    public static double y = 65;
    public static double xvelocity = 0.25;
    public static double yvelocity = 0.25;
    public static double health = 200;
    public static double shotSpeed = 0.25;
    public static Player player;
    public static Random random = new Random();
    public static List<List<Double>> shots = new ArrayList<List<Double>>(); //x,y,xvel,yvel,size
    
    public static int count = 0;
    
    public Boss(Player p) {
        player = p;
    }
    
    public static void draw() {
        StdDraw.setPenColor(0,0,0);
        StdDraw.filledCircle(x,y,30);
        
        StdDraw.setPenColor(255,255,255);
        StdDraw.filledCircle(x-10,y-10,5);
        StdDraw.filledCircle(x+10,y-10,5);
    }
    
    public static void blink() {
        StdDraw.setPenColor(0,0,0);
        StdDraw.filledCircle(x-10,y-10,5);
        StdDraw.filledCircle(x+10,y-10,5);
    }
    
    public static void displayHealth() {
        StdDraw.setPenColor(200,0,0);
        StdDraw.filledRectangle(health/2-100,-100,health/2.0,5);
    }
    
    public static void move() {
        x += xvelocity;
        if (x > 65 || x < -65) xvelocity *= -1;
        //y += yvelocity;
        //if (y > 65 || y < -65) yvelocity *= -1;
    }
    
    public static void circleAttack() {
        StdDraw.setPenColor(0,0,0);
        List<Double> temp = new ArrayList<Double>();
        for (int i=-90;i<=90;i+=5) {
            temp = new ArrayList<Double>();
            temp.add(x);
            temp.add(y);
            temp.add(Math.sin(i));
            temp.add(Math.cos(i));
            temp.add(3.0);
            shots.add(temp);
        }
    }
    
    public static double getAngle(double x1,double x2,double y1,double y2) {
        double angle = (double) Math.toDegrees(Math.atan2(y2 - y1, x2 - x1));
        return angle;
    }
    
    public static void directAttack() {
        StdDraw.setPenColor(255,255,255);
        List<Double> temp = new ArrayList<Double>();
        for (double i=-90;i<=90;i+=15) {
            temp = new ArrayList<Double>();
            double angle = getAngle(x,player.xPos,y,player.yPos);
            temp.add(x);
            temp.add(y);
            temp.add(1.0 * Math.cos(Math.toRadians(angle)));
            temp.add(1.0 * Math.sin(Math.toRadians(angle)));
            temp.add(5.0);
            shots.add(temp);
        }
    }
    
    public static void drawShots() {
        for (int i=0;i<shots.size();i++) {
            shots.get(i).set(0,shots.get(i).get(0) + shots.get(i).get(2) * shotSpeed);
            shots.get(i).set(1,shots.get(i).get(1) + shots.get(i).get(3) * shotSpeed);
            
            StdDraw.setPenColor(0,0,0);
            StdDraw.filledCircle(shots.get(i).get(0),shots.get(i).get(1),shots.get(i).get(4));
            
            if (shots.get(i).get(0) > 100 || shots.get(i).get(0) < -100) shots.remove(i);
            if (shots.get(i).get(1) > 100 || shots.get(i).get(1) < -100) shots.remove(i);
        }
    }
    
    public static void takeDamage() {
        for (int i=0;i<player.projectiles.size();i++) {
            if ((x-20 < player.projectiles.get(i).get(0) && player.projectiles.get(i).get(0) < x+20) && 
                (y-20 < player.projectiles.get(i).get(1) && player.projectiles.get(i).get(1) < y+20)) {
                    player.projectiles.remove(i);
                    health -= 10;
            }
        }
        if (health == 0) {
            System.out.println("congrat");
            System.exit(0);
        }
    }
    
    public static void update() {
        if (count == 10) circleAttack();
        if (count % 100 == 0) directAttack();
        if (count > 1000) count = 0;
        
        drawShots();
        move();
        draw();
        takeDamage();
        if (200 < count && count < 300) blink();
        
        count++;
    }
}
