import java.util.*;
import java.awt.event.KeyEvent;

public class Jump {
   public static double[] coords = {0,0};
   public static double horizontalVelocity = 0.2;
   public static double gravity = 0.1;
   
   public static void initialize() {
      StdDraw.enableDoubleBuffering();
      StdDraw.setScale(-100,100);
      StdDraw.setPenRadius(0.05);
      StdDraw.setPenColor(255,255,255);
   }
   
   public static void drawBorder() {
      StdDraw.setPenColor(StdDraw.WHITE);
      StdDraw.filledRectangle(-100,0,5,100);
      StdDraw.filledRectangle(100,0,5,100);
      StdDraw.filledRectangle(0,-100,100,5);
      StdDraw.filledRectangle(0,100,100,5);
   }
   
   public static void player() {
      double upwardsVelocity = 0;
      if (StdDraw.isKeyPressed(KeyEvent.VK_D) && coords[0] < 90) coords[0] += horizontalVelocity;
      if (StdDraw.isKeyPressed(KeyEvent.VK_A) && coords[0] > -90) coords[0] -= horizontalVelocity;
      
      //if (StdDraw.isKeyPressed(KeyEvent.VK_W) && coords[1] > -90) upwardsVelocity += 1;
      
      if (coords[1] > -90) upwardsVelocity -= gravity;
      if (coords[1] > -90) coords[1] += upwardsVelocity;
      
      StdDraw.setPenColor(StdDraw.BLUE);
      StdDraw.point(coords[0],coords[1]);
   }
   
   public static void main(String[] args) {
      initialize();
      
      while (true) {
         StdDraw.clear(StdDraw.BLACK);
         
         drawBorder();
         player();
         
         StdDraw.show();
      }
   }
}
