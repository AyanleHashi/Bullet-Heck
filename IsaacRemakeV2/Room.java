import java.util.*;
import java.awt.*;
//Figure out how the heck to fix transition, most likely moves the player several rooms too far
public class Room {
    public boolean[] doors = new boolean[4];
    public Player player;

    public Room(Player p) {
        player = p;
    }

    public void drawRoom() {
        StdDraw.setPenColor(190, 130, 100);
        StdDraw.filledRectangle(0, 90, 100, 10);
        StdDraw.filledRectangle(0, -90, 100, 10);
        StdDraw.filledRectangle(90, 0, 10, 100);
        StdDraw.filledRectangle(-90, 0, 10, 100);
    }

    public void drawDoors() {
        StdDraw.setPenColor(100, 60, 60);
        if (doors[0]) StdDraw.filledRectangle(0, 90, 11, 11);
        if (doors[1]) StdDraw.filledRectangle(90, 0, 11, 11);
        if (doors[2]) StdDraw.filledRectangle(0, -90, 11, 11);
        if (doors[3]) StdDraw.filledRectangle(-90, 0, 11, 11);
    }

    public void transition() {
        if (player.xPos >= 74 && (-5 < player.yPos && player.yPos < 5) && doors[1]) {
            player.xPos -= 149;
            player.roomX += 1;
        }
        if (player.xPos <= -74 && (-5 < player.yPos && player.yPos < 5) && doors[3]) {
            player.xPos += 149;
            player.roomX -= 1;
        }
        if (player.yPos >= 74 && (-5 < player.xPos && player.xPos < 5) && doors[0]) {
            player.yPos -= 149;
            player.roomY += 1;
        }
        if (player.yPos <= -74 && (-5 < player.xPos && player.xPos < 5) && doors[2]) {
            player.yPos += 149;
            player.roomX -= 1;
        }
    }

    public void update() {
        drawRoom();
        drawDoors();
        transition();
        System.out.println(doors[0] + " " + doors[1] + " " + doors[2] + " " + doors[3]);
    }
}
