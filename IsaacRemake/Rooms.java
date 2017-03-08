import java.util.*;
import java.awt.event.KeyEvent;
import java.awt.Font;

class Rooms {
    public Player p;
    public int row = 0;
    public int column = 0;
    public int roomID;
    public int[][] map = {{1,0,0},
                          {0,0,0},
                          {0,0,0}};
    
    public Rooms(Player player) {
        p = player;
    }
    
    public void roomTransition() {
        if (p.xPos >= 90 && (-5 < p.yPos && p.yPos < 5)) {
            column += 1;
            p.xPos = -84;
            System.out.println(row + " " + column);
        }
    }
    
    public void drawRoom(int row, int column) {
        StdDraw.setPenColor(255,200,200);
        StdDraw.filledRectangle(0,95,100,5);
        StdDraw.filledRectangle(0,-95,100,5);
        StdDraw.filledRectangle(95,0,5,100);
        StdDraw.filledRectangle(-95,0,5,100);
        
        drawDoors();
        roomTransition();
    }
    
    public void drawDoors() {
        int roomID = map[row][column];
        StdDraw.setPenColor(100,70,70);
        
        if (roomID == 0) {
            StdDraw.filledRectangle(0,-90,10,3);
        }
        if (roomID == 1) {
            StdDraw.filledRectangle(0,90,10,3);
        }
    }
    
    public void update(int r, int c) {
        row = r; column = c;
        drawRoom(row,column);
        roomTransition();
    }
}
