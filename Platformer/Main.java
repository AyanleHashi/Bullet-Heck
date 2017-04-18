public class Main {
    public static void initialize() {
        StdDraw.enableDoubleBuffering();
        StdDraw.setScale(-100,100);
        StdDraw.setPenRadius(0.05);
    }

    public static void main(String[] args) {
        initialize();
        Player player = new Player();

        while (true) {
            StdDraw.clear(StdDraw.BLACK);
            player.update();
            StdDraw.show();
        }
    }
}
