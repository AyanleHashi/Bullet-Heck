import java.awt.event.KeyEvent;
import java.awt.*;

public class BrickBreak{
    public static void main (String[] args){
        StdDraw.enableDoubleBuffering();
        drawboard();
        double bouncer = 0;
        double[] ballpos = {0,0};
        double[] ballvel = {.3,.4};
        stillalive(ballpos[1]);
        while (stillalive(ballpos[1])){
            StdDraw.clear();
            bouncer = bouncerpos(bouncer);
            drawbouncer(bouncer);
            ballpos = calcballpos(ballpos,ballvel);
            ballvel = collision(ballpos,ballvel,bouncer);
            drawball(ballpos);
            StdDraw.show();
            StdDraw.pause(5);
        }   
        System.out.println("over");  
        StdDraw.clear(StdDraw.RED); 
        StdDraw.setPenColor(StdDraw.BLUE);
        Font font = new Font("Arial", Font.BOLD, 60);
        StdDraw.setFont(font);
        StdDraw.text(0,0,"GAME OVER");
        StdDraw.show();    
    }
    
    public static void drawball(double[] x){
        StdDraw.setPenColor(StdDraw.RED);
        StdDraw.setPenRadius(.05);
        StdDraw.point(x[0],x[1]);
     }
    
    public static double[] collision (double[] x, double[] v, double b){
        double[] a = v;
        if (x[0]>=b-15 && x[0]<=b+15 && x[1] <=-95 && x[1] >=-100){
            double scaler = (Math.sqrt(Math.pow(Math.abs(v[1]),2) + Math.pow(v[0]+((x[0]-b)/20),2)));
            a[0] = (v[0]+((x[0]-b)/20))/(scaler*2);
            a[1] = Math.abs(v[1])/(scaler*2);
            System.out.println( a[0] + " ,  " + a[1]); 
            if (a[1]<.1) a[1] +=.1;    
        }
        if (x[0]<=-95) a[0] = a[0]*-1;
        if (x[0]>=95) a[0] = a[0]*-1;
        if (x[1]>=95) a[1] = a[1]*-1;
        return a;     
    }  
            
    
    public static double[] calcballpos (double[] x, double[] v){
        x[0] += v[0];
        x[1] += v[1];
        return x;
    }
    
    public static void drawbouncer(double x){
        StdDraw.setPenColor(StdDraw.BLUE);
        StdDraw.setPenRadius(.05);
        StdDraw.line(x-10,-100,x+10,-100);
    }
    
    public static double bouncerpos(double x){
        if (StdDraw.isKeyPressed(KeyEvent.VK_RIGHT)) x += 0.5;
        if (StdDraw.isKeyPressed(KeyEvent.VK_LEFT)) x -= 0.5; 
        if (x >= 95) x = 95;
        if (x <= -95) x = -95;
        return x; 
    }
    
    public static boolean stillalive(double y){
        if (y<=-110) return false;
        else return true;
    }
    
    public static void drawboard(){
        StdDraw.setScale(-100,100);
    }
}
        
        
        
        
        
        
        


/*public class StandardDraw {

   public static double[] checkKeyPresses(double x, double y) {
      if (StdDraw.isKeyPressed(KeyEvent.VK_RIGHT)) x += 0.5;
      if (StdDraw.isKeyPressed(KeyEvent.VK_LEFT)) x -= 0.5;
      if (StdDraw.isKeyPressed(KeyEvent.VK_UP)) y += 0.5;
      if (StdDraw.isKeyPressed(KeyEvent.VK_DOWN)) y -= 0.5;
      
      if (x >= 95) x = 95;
      if (x <= -95) x = -95;
      if (y >= 95) y = 95;
      if (y <= -95) y = -95;
      
      double[] check = {x,y};
      return check;
   }

   public static void main(String[] args) {
      StdDraw.setScale(-100,100);
      StdDraw.setPenRadius(0.05);
      StdDraw.setPenColor(StdDraw.BLUE);
      double x = 0.0;
      double y = 0.0;
      if (StdDraw.mousePressed()) x++;
      System.out.println(StdDraw.mouseX());
      double[] check = {0,0};
      
      while (true) {
         StdDraw.clear();
         check = checkKeyPresses(check[0],check[1]);
         StdDraw.point(check[0],check[1]);
      }
   }
}*/