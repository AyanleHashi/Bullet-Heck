import java.util.*;
import java.awt.event.KeyEvent;
import java.awt.Font;

class Rooms {
    public Player p;
    public int row;
    public int column;
    public int roomID;
    public int[][] map = new int[3][3];
    
    public Rooms(Player player, int r, int c) {
        p = player;
        row = r;
        column = c;
        createMap();
    }
    
    public void createMap() {
        Random random = new Random();
        for (int y=0;y<map.length;y++) {
            for (int x=0;x<map[y].length;x++) {
                map[y][x] = random.nextInt(3);
            }
        }
        System.out.println(Arrays.toString(map));
        /*HERE*/
    }
    
    public void roomTransition(int r, int c) {
        roomID = map[r][c];
        if (p.xPos >= 85 && (-5 < p.yPos && p.yPos < 5) && roomID == 0) {
            column += 1;
            p.xPos = -84;
        }
        else  if (p.xPos <= -85 && (-5 < p.yPos && p.yPos < 5) && roomID == 1) {
            column -= 1;
            p.xPos = 84;
        }
    }
    
    public void drawRoom(int row, int column) {
        StdDraw.setPenColor(255,200,200);
        StdDraw.filledRectangle(0,95,100,5);
        StdDraw.filledRectangle(0,-95,100,5);
        StdDraw.filledRectangle(95,0,5,100);
        StdDraw.filledRectangle(-95,0,5,100);
        
        drawDoors();
        roomTransition(row,column);
    }
    
    public void drawDoors() {
        int roomID = map[row][column];
        StdDraw.setPenColor(100,70,70);
        
        if (roomID == 0) {
            StdDraw.filledRectangle(90,0,5,10);
        }
        if (roomID == 1) {
            StdDraw.filledRectangle(-90,0,5,10);
        }
    }
    
    public void update() {
        roomID = map[row][column];
        drawRoom(row,column);
        roomTransition(row,column);
    }
}
