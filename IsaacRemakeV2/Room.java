import java.util.*;

class Room {
    public boolean[] doors = new boolean[4];
    public int roomType = 0;
    public Player player;
    public ArrayList<BasicEnemy> basicEnemies = new ArrayList<BasicEnemy>();
    public boolean playerCanLeaveRoom = true;

    public Room(Player p, int enemyCount) {
        player = p;
        for (int i=0;i<enemyCount;i++) {
            basicEnemies.add(new BasicEnemy(player));
        }
    }

    public void drawRoom() {
        StdDraw.setPenColor(190, 130, 100);
        StdDraw.filledRectangle(0, 90, 100, 10);
        StdDraw.filledRectangle(0, -90, 100, 10);
        StdDraw.filledRectangle(90, 0, 10, 100);
        StdDraw.filledRectangle(-90, 0, 10, 100);
        
        if (roomType == 1) StdDraw.filledRectangle(0,0,10,10);
    }

    public void drawDoors() {
        StdDraw.setPenColor(100, 60, 60);
        if (doors[0]) StdDraw.filledRectangle(0, 90, 11, 11);
        if (doors[1]) StdDraw.filledRectangle(90, 0, 11, 11);
        if (doors[2]) StdDraw.filledRectangle(0, -90, 11, 11);
        if (doors[3]) StdDraw.filledRectangle(-90, 0, 11, 11);
    }

    public void transition() {
        if (player.xPos > 74 && (-5 < player.yPos && player.yPos < 5) && doors[1] && playerCanLeaveRoom) {
            player.xPos -= 145;
            player.roomY += 1;
            player.projectiles = new ArrayList<List<Double>>();
        }
        if (player.xPos < -74 && (-5 < player.yPos && player.yPos < 5) && doors[3] && playerCanLeaveRoom) {
            player.xPos += 145;
            player.roomY -= 1;
            player.projectiles = new ArrayList<List<Double>>();
        }
        if (player.yPos > 74 && (-5 < player.xPos && player.xPos < 5) && doors[0] && playerCanLeaveRoom) {
            player.yPos -= 145;
            player.roomX -= 1;
            player.projectiles = new ArrayList<List<Double>>();
        }
        if (player.yPos < -74 && (-5 < player.xPos && player.xPos < 5) && doors[2] && playerCanLeaveRoom) {
            player.yPos += 145;
            player.roomX += 1;
            player.projectiles = new ArrayList<List<Double>>();
        }
    }

    public void checkEnemies() {
        if (basicEnemies.size() != 0) playerCanLeaveRoom = false;
        else playerCanLeaveRoom = true;
    }

    public void updateEnemies() {
        checkEnemies();
        if (roomType == 0) {
            for (int i=basicEnemies.size()-1;i>=0;i--) {
                basicEnemies.get(i).update();
                checkDamage(basicEnemies.get(i));
                
                if (basicEnemies.get(i).health == 0) basicEnemies.remove(i);
            }
        }
    }

    public void checkDamage(BasicEnemy e) {
        if ((e.xPos-5 < player.xPos && player.xPos < e.xPos+5) && (e.yPos-5 < player.yPos && player.yPos < e.yPos+5)) {
            player.takeDamage(true);
        }
    }

    public void update() {
        drawRoom();
        drawDoors();
        transition();
        updateEnemies();
    }
}
