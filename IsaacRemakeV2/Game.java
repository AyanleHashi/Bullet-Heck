import java.util.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class Game {
    public static Player player = new Player();
    
    public static int[][] floorLayout = new int[8][8];
    public static Room[][] roomLayout = new Room[8][8];
    
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

    public static void initializeRooms() {
        for (int row=0;row<roomLayout.length;row++) {
            for (int col=0;col<roomLayout[row].length;col++) {
                roomLayout[row][col] = new Room(player);
            }
        }
    }
    
    public static void generateFloor(int roomCount) {
        Random random = new Random();
        int x = 4, y = 4;
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
    }
    
    public static void generateDoors() {
        for (int y=0;y<floorLayout.length;y++) {
            for (int x=0;x<floorLayout[y].length;x++) {
                try {
                    if (floorLayout[y+1][x] == 1 && floorLayout[y][x] == 1) roomLayout[y][x].doors[2] = true;
                    if (floorLayout[y][x+1] == 1 && floorLayout[y][x] == 1) roomLayout[y][x].doors[1] = true;
                    if (floorLayout[y-1][x] == 1 && floorLayout[y][x] == 1) roomLayout[y][x].doors[0] = true;
                    if (floorLayout[y][x-1] == 1 && floorLayout[y][x] == 1) roomLayout[y][x].doors[3] = true;
                } catch (ArrayIndexOutOfBoundsException e) {}
            }
        }
    }
    
    public static void drawMap() {
        for (int y=0;y<floorLayout.length;y++) {
            for (int x=0;x<floorLayout[y].length;x++) {
                if (floorLayout[y][x] == 1) {
                    StdDraw.setPenColor(100,100,100);
                    StdDraw.filledRectangle(x*10-90,-y*10+90,4.9,4.9);

                    if (x == player.roomX && y == player.roomY) {
                        StdDraw.setPenColor(255,255,255);
                        StdDraw.filledRectangle(x*10-90,-y*10+90,4.9,4.9);
                    }

                    //if (1==2);

                    else {
                        StdDraw.setPenColor(200, 200, 200);
                        if (roomLayout[y][x].doors[0]) StdDraw.filledRectangle(x * 10 - 90, -y * 10 + 95, 1, 1);
                        if (roomLayout[y][x].doors[1]) StdDraw.filledRectangle(x * 10 - 85, -y * 10 + 90, 1, 1);
                        if (roomLayout[y][x].doors[2]) StdDraw.filledRectangle(x * 10 - 90, -y * 10 + 85, 1, 1);
                        if (roomLayout[y][x].doors[3]) StdDraw.filledRectangle(x * 10 - 95, -y * 10 + 90, 1, 1);
                    }
                }
            }
        }
    }
    
    public static void print(Room[][] arr) {
        for (int y=0;y<arr.length;y++) {
            System.out.println(Arrays.toString(arr[y]));
        }
    }
    
    public static void main(String[] args) {
        initializeScreen();
        initializeRooms();
        generateFloor(15);
        generateDoors();
        
        while (true) {
            StdDraw.clear(new Color(100,60,60));
            
            if (StdDraw.isKeyPressed(KeyEvent.VK_M)) drawMap();
            roomLayout[player.roomX][player.roomY].update();

            player.update();
            StdDraw.show();

            System.out.println(player.roomX+ ", " + player.roomY);
        }
    }
}
