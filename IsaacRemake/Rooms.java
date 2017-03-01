import java.util.*;
import java.awt.event.KeyEvent;
import java.awt.Font;

class Rooms {
    public Player p;
    public int row = 0;
    public int column = 0;
    public int[][] rooms = {{0,0,0},
                            {0,0,0},
                            {0,0,0}};
    
    public Rooms(Player player) {
        p = player;
    }
    
    public void drawRoom(int row, int column) {
        StdDraw.setPenColor(255,200,200);
        StdDraw.filledRectangle(0,90,100,10);
        StdDraw.filledRectangle(0,-90,100,10);
        StdDraw.filledRectangle(90,0,10,100);
        StdDraw.filledRectangle(-90,0,10,100);
        
        drawDoors(row,column);
    }
    
    public void drawDoors(int row, int column) {
        int room = rooms[row][column];
        
        if (room == 0) {
            StdDraw.setPenColor(100,70,70);
            StdDraw.filledRectangle(0,-80,10,3);
        }
    }
}
