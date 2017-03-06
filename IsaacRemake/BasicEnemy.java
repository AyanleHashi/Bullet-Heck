import java.util.*;
import java.awt.event.KeyEvent;
import java.awt.Font;

class BasicEnemy {
    public static Random random = new Random();
    
    public double xPos;
    public double yPos;
    public double vel;
    public Player p;
    public Font font;
    public double[] possibleVels = {0.001,0.002};
    
    public BasicEnemy(Player player) {
        xPos = random.nextInt(200)-100;
        yPos = random.nextInt(200)-100;
        p = player;
        StdDraw.setFont(new Font("Arial",Font.BOLD,25));
        vel = possibleVels[random.nextInt(possibleVels.length)];
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
    }
}
