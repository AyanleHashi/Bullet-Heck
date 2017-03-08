import java.util.*;
import java.awt.event.KeyEvent;
import java.awt.Font;
import java.util.concurrent.ThreadLocalRandom;

class BasicEnemy {
    public static Random random = new Random();
    
    public double xPos;
    public double yPos;
    public double health = 2;
    public double vel;
    public Player p;
    public Font font;
    
    public BasicEnemy(Player player) {
        xPos = random.nextInt(200)-100;
        yPos = random.nextInt(200)-100;
        p = player;
        StdDraw.setFont(new Font("Arial",Font.BOLD,25));
        vel = ThreadLocalRandom.current().nextDouble(0,0.005);
    }
    
    public void takeDamage() {
        for (int i=0;i<p.projectiles.size();i++) {
            if ((xPos-5 < p.projectiles.get(i).get(0) && p.projectiles.get(i).get(0) < xPos+5) && 
                (yPos-5 < p.projectiles.get(i).get(1) && p.projectiles.get(i).get(1) < yPos+5)) {
                    p.projectiles.remove(i);
                    health -= 1;
            }
        }
    }
    
    public double getAngle(double x1,double x2,double y1,double y2) {
        double angle = (double) Math.toDegrees(Math.atan2(y2 - y1, x2 - x1));
        return angle;
    }
    
    public void locatePlayer() {
        double wallSize = 78;
        
        double angle = getAngle(xPos,p.xPos,yPos,p.yPos);
        double distance = Math.hypot(Math.abs(xPos-p.xPos),Math.abs(yPos-p.yPos));
        
        double deltaX = distance * Math.cos(Math.toRadians(angle));
        double deltaY = distance * Math.sin(Math.toRadians(angle));
        xPos += vel * deltaX;
        yPos += vel * deltaY;
        
        if (xPos > wallSize) xPos = wallSize;
        if (xPos < -wallSize) xPos = -wallSize;
        if (yPos > wallSize) yPos = wallSize;
        if (yPos < -wallSize) yPos = -wallSize;
    }
    
    public void draw() {
        StdDraw.setPenColor(100,100,100);
        StdDraw.point(xPos,yPos);
    }
    
    public void update() {
        draw();
        locatePlayer();
        takeDamage();
    }
}
