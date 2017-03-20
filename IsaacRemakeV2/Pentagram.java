public class Pentagram implements Item {
    Player player = new Player();
    double x = 0;
    double y = 0;

    public void modifier() {
        player.attack += 2;
    }

    public void draw(double x, double y) {
        StdDraw.setPenColor(255,0,0);
        StdDraw.filledCircle(x,y,5);
        pickup(x,y);
    }
    public void pickup(double x, double y) {
        if ((x-5 < player.xPos && player.xPos < x+5) && (y-5 < player.yPos && player.yPos < y+5));
    }
}
