import java.util.*;
import java.awt.event.KeyEvent;
import java.awt.Font;

class BasicEnemy {
    public static Random random = new Random();
    
    public double xPos;
    public double yPos;
    public double vel = 0.05;
    public Player p;
    public Font font;
    
    public BasicEnemy(Player player) {
        xPos = random.nextInt(200)-100;
        yPos = random.nextInt(200)-100;
        p = player;
        StdDraw.setFont(new Font("Arial",Font.BOLD,25));
    }
    
    public double getAngle(double x1,double x2,double y1,double y2) {
        double angle = (double) Math.toDegrees(Math.atan2(y2 - y1, x2 - x1));
        if(angle < 0) angle += 360;
        
        return angle;
    }
    
    public void locatePlayer() {
        /*if (p.xCoord > xPos) xPos += vel;
        if (p.xCoord < xPos) xPos -= vel;
        if (p.yCoord > yPos) yPos += vel;
        if (p.yCoord < yPos) yPos -= vel;*/
        
        double angle = getAngle(xPos,p.xCoord,yPos,p.yCoord);
        double distance = Math.hypot(Math.abs(xPos-p.xCoord),Math.abs(yPos-p.yCoord));
        
        double deltaX = distance * Math.sin(angle) * (180.0 / Math.PI);
        xPos += 0.0005 * deltaX;
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
