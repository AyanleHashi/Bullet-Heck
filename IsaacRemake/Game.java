import java.util.*;
import java.awt.event.KeyEvent;
import java.awt.Font;
import java.awt.Color;

public class Game {
    //TODO: rooms, bombs
    
    public static Player player = new Player();
    public static ArrayList<BasicEnemy> basicEnemies = new ArrayList<BasicEnemy>();
    public static Rooms rooms = new Rooms(player,0,0);
   
    public static void initialize() {
        StdDraw.enableDoubleBuffering();
        StdDraw.setScale(-100,100);
        StdDraw.setPenRadius(0.05);
        StdDraw.setFont(new Font("Arial",Font.BOLD,25));
    }
    
    public static void checkDamage(Player p,BasicEnemy e) {
        if ((e.xPos-5 < p.xPos && p.xPos < e.xPos+5) && (e.yPos-5 < p.yPos && p.yPos < e.yPos+5)) {
            p.takeDamage(true);
        }
    }
    
    public static void createEnemies(int count) {
        for (int i=0;i<count;i++) {
            basicEnemies.add(new BasicEnemy(player));
        }
    }
    
    public static void updateEnemies() {
        for (int i=0;i<basicEnemies.size();i++) {
            basicEnemies.get(i).update();
            checkDamage(player,basicEnemies.get(i));
            if (basicEnemies.get(i).health == 0) basicEnemies.remove(i);
        }
    }
    
    public static void main(String[] args) {
        initialize();
        
        //createEnemies(3);
        
        
        while (true) {
           StdDraw.clear(new Color(100,70,70));
           
           updateEnemies();
           rooms.update();
           
           player.update();
           if (player.health == 0.0) {
               System.out.println("Game over!");
               System.exit(0);
               break;
           }
           
           StdDraw.show();
        }
    }
}
