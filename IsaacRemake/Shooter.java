import java.util.*;
import java.awt.event.KeyEvent;
import java.awt.Font;
import java.awt.Color;

public class Shooter {
    public static ArrayList<BasicEnemy> basicEnemies = new ArrayList<BasicEnemy>();
    public static Player player = new Player();
    public static Rooms rooms = new Rooms(player);
    public static int[] room = {0,0};
   
    public static void initialize() {
        StdDraw.enableDoubleBuffering();
        StdDraw.setScale(-100,100);
        StdDraw.setPenRadius(0.05);
    }
    
    public static void createEnemies() {
        for (int i=0;i<1;i++) {
            basicEnemies.add(new BasicEnemy(player));
        }
    }
    
    public static void updateEnemies() {
        for (BasicEnemy basicEnemy: basicEnemies) {
            basicEnemy.update();
        }
    }
    
    public static void main(String[] args) {
        initialize();
        
        createEnemies();
        
        while (true) {
           StdDraw.clear(new Color(100,70,70));
           
           //updateEnemies();
           rooms.drawRoom(room[0],room[1]);
           
           player.update();
           StdDraw.show();
        }
    }
}
