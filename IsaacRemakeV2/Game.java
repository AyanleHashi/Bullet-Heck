import java.util.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class Game {
    public static Player player = new Player();
    
    public static int[][] floorLayout;
    public static Room[][] roomLayout;
    
    public static void initializeScreen() {
        StdDraw.enableDoubleBuffering();
        StdDraw.setScale(-100,100);
        StdDraw.setPenRadius(0.05);
        StdDraw.setPenColor(100,100,100);
    }
    
    public static int count(int n, int[][] a) {
        int count = 0;
        for (int row=0;row<a.length;row++) {
            for (int col=0;col<a[row].length;col++) {
                if (a[row][col] == n) count++;
            }
        }
        return count;
    }

    public static void initializeRooms(int x, int y) {
        floorLayout = new int[y][x];
        roomLayout = new Room[y][x];
        Random random = new Random();
        for (int row=0;row<roomLayout.length;row++) {
            for (int col=0;col<roomLayout[row].length;col++) {
                roomLayout[row][col] = new Room(player,random.nextInt(3));
            }
        }
    }
    
    public static void generateFloor(int roomCount, int x, int y) {
        initializeRooms(x,y);
        Random random = new Random();
        x = 4;
        y = 4;
        boolean a;
        
        while (count(1,floorLayout) < roomCount) {
            try {
                if (floorLayout[y][x] == 0) floorLayout[y][x] = 1;
                a = random.nextBoolean();
                
                if (a) x += random.nextInt(3)-1;
                else y += random.nextInt(3)-1;
            } catch (ArrayIndexOutOfBoundsException e) {
                x = 4;
                y = 4;
            }
        }
        generateDoors();
    }
    
    public static void generateDoors() {
        for (int y=0;y<floorLayout.length;y++) {
            for (int x=0;x<floorLayout[y].length;x++) {
                try {
                    if (floorLayout[y+1][x] == 1 && floorLayout[y][x] == 1) roomLayout[y][x].doors[2] = true;
                } catch (ArrayIndexOutOfBoundsException e) {}

                try {
                    if (floorLayout[y][x+1] == 1 && floorLayout[y][x] == 1) roomLayout[y][x].doors[1] = true;
                } catch (ArrayIndexOutOfBoundsException e) {}

                try {
                    if (floorLayout[y-1][x] == 1 && floorLayout[y][x] == 1) roomLayout[y][x].doors[0] = true;
                } catch (ArrayIndexOutOfBoundsException e) {}

                try {
                    if (floorLayout[y][x-1] == 1 && floorLayout[y][x] == 1) roomLayout[y][x].doors[3] = true;
                } catch (ArrayIndexOutOfBoundsException e) {}
            }
        }
    }
    
    public static int doorCount(Room room) {
        int count = 0;
        if (room.doors[0]) count++;
        if (room.doors[1]) count++;
        if (room.doors[2]) count++;
        if (room.doors[3]) count++;
        return count;
    }
    
    public static void generateItemRoom() {
        for (int y=0;y<roomLayout.length;y++) {
            for (int x=0;x<roomLayout[y].length;x++) {
                if (doorCount(roomLayout[y][x]) == 1) {
                    roomLayout[y][x].roomType = 1;
                    return;
                }
            }
        }
    }
    
    public static void drawMap() {
        for (int y=0;y<floorLayout.length;y++) {
            for (int x=0;x<floorLayout[y].length;x++) {
                if (floorLayout[y][x] == 1) {
                    StdDraw.setPenColor(100,100,100);
                    if ((y == player.roomX && x == player.roomY)) {
                        StdDraw.setPenColor(255,255,255);
                        StdDraw.filledRectangle(x*10-90,-y*10+90,4.9,4.9);
                    }
                    
                    else if (roomLayout[y][x].roomType == 1) {
                        StdDraw.setPenColor(255,215,0);
                        StdDraw.filledRectangle(x*10-90,-y*10+90,4.9,4.9);
                    }
                    
                    else StdDraw.filledRectangle(x*10-90,-y*10+90,4.9,4.9);
                    StdDraw.setPenColor(200, 200, 200);
                    if (roomLayout[y][x].doors[0]) StdDraw.filledRectangle(x * 10 - 90, -y * 10 + 95, 1, 1);
                    if (roomLayout[y][x].doors[1]) StdDraw.filledRectangle(x * 10 - 85, -y * 10 + 90, 1, 1);
                    if (roomLayout[y][x].doors[2]) StdDraw.filledRectangle(x * 10 - 90, -y * 10 + 85, 1, 1);
                    if (roomLayout[y][x].doors[3]) StdDraw.filledRectangle(x * 10 - 95, -y * 10 + 90, 1, 1);
                }
            }
        }
    }
    
    public static void main(String[] args) {
        initializeScreen();

        generateFloor(10,8,8);
        generateItemRoom();
        
        while (true) {
            StdDraw.clear(new Color(100,60,60));

            roomLayout[player.roomX][player.roomY].update();
            if (StdDraw.isKeyPressed(KeyEvent.VK_M)) drawMap();

            player.update();
            StdDraw.show();

            if (player.health == 0) System.exit(0);
        }
    }
}
