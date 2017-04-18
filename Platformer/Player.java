import java.awt.event.KeyEvent;

public class Player {
    public static double x = 0;
    public static double y = 0;
    public static double gravity = 0.001;
    public static double velocity = 0;
    public static double jumpTimer = 0;

    public static void move() {
        if (StdDraw.isKeyPressed(KeyEvent.VK_A) && x > -95) x -= 0.25;
        if (StdDraw.isKeyPressed(KeyEvent.VK_D) && x < 95) x += 0.25;
        if (StdDraw.isKeyPressed(KeyEvent.VK_W) && jumpTimer < 0) {
            velocity = 0.25;
            jumpTimer = 0.5 / gravity;
        }
        if (StdDraw.isKeyPressed(KeyEvent.VK_S)) velocity = -0.5;

        jump();
    }

    public static void jump() {
        if (y > -95) {
            velocity -= gravity;
            y += velocity;
        }
        if (y < -95) {
            y += 0.1;
            jumpTimer = 0;
        }
    }

    public static void update() {
        StdDraw.setPenColor(255, 0, 0);
        StdDraw.filledCircle(x, y, 5);
        move();
        jumpTimer -= 1;
    }
}
