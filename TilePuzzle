import java.awt.Font;
import java.awt.event.KeyEvent;
import java.util.*;

public class TilePuzzle {

    public static final int[][] board = {{7,8,0},{4,5,6},{1,2,3}};
    
    public static void initialize() {
        StdDraw.enableDoubleBuffering();
        StdDraw.setScale(-100,100);
        StdDraw.setPenRadius(0.05);
        Font font = new Font("Arial", Font.BOLD, 50);
        StdDraw.setFont(font);
    }

    public static void swap(int x1,int y1,int x2,int y2) {
        board[x1][y1] = (board[x1][y1] + board[x2][y2]) - (board[x2][y2] = board[x1][y1]);
    }
    
    public static void draw() {
        for (int y=0;y<board.length;y++) {
            for (int x=0;x<board[y].length;x++) {
                StdDraw.text(x*90-90,-y*90+90,Integer.toString(board[y][x]));
            }
        }
    }
    
    public static int getKeys() {
        int num = 0;
        if (StdDraw.isKeyPressed(KeyEvent.VK_NUMPAD1)) num = 1;
        if (StdDraw.isKeyPressed(KeyEvent.VK_NUMPAD2)) num = 2;
        if (StdDraw.isKeyPressed(KeyEvent.VK_NUMPAD3)) num = 3;
        if (StdDraw.isKeyPressed(KeyEvent.VK_NUMPAD4)) num = 4;
        if (StdDraw.isKeyPressed(KeyEvent.VK_NUMPAD5)) num = 5;
        if (StdDraw.isKeyPressed(KeyEvent.VK_NUMPAD6)) num = 6;
        if (StdDraw.isKeyPressed(KeyEvent.VK_NUMPAD7)) num = 7;
        if (StdDraw.isKeyPressed(KeyEvent.VK_NUMPAD8)) num = 8;
        if (StdDraw.isKeyPressed(KeyEvent.VK_NUMPAD9)) num = 9;
        
        return num;
    }
    
    public static boolean checkValidSwitch(int num) {
        int numx=0,numy=0,zx=0,zy=0;
        ArrayList s = new ArrayList();
        boolean valid = false;
        
        for (int y=0;y<board.length;y++) {
            for (int x=0;x<board[y].length;x++) {
                if (board[x][y] == num) {
                    numx = x;
                    numy = y;
                }
                if (board[x][y] == 0) {
                    zx = x;
                    zy = y;
                }
            }
        }
        
        try {
            s.add(board[numx][numy-1]);
        } catch (IndexOutOfBoundsException e) {}
        
        try {
            s.add(board[numx][numy+1]);
        } catch (IndexOutOfBoundsException e) {}
        
        try {
            s.add(board[numx+1][numy]);
        } catch (IndexOutOfBoundsException e) {}
        
        try {
            s.add(board[numx-1][numy]);
        } catch (IndexOutOfBoundsException e) {}
        
        if (s.contains(0)) {
            valid = true;
            swap(numx,numy,zx,zy);
        }
        
        return valid;
    }
    
    public static void main(String[] args) {
        initialize();
                
        while (true) {
            StdDraw.clear(StdDraw.WHITE);
            draw();
            checkValidSwitch(getKeys());
            StdDraw.show();
            StdDraw.pause(80);
        }
    }
}
