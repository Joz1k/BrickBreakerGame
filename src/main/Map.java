package main;

import java.awt.*;

public class Map {

    private int[][] map;
    private int brickHeight, brickWidth;
    public final int HPAD = 80, WPAD = 50;

    public Map(int row, int col) {
        initMap(row, col);

        brickWidth = (BBMain.WIDTH - 2 * HPAD) / col;
        brickHeight = (BBMain.HEIGHT / 2 - WPAD * 2) / row;

    }

    public void initMap(int row, int col) {
        map = new int[row][col];

        for(int i = 0; i < map.length; i++) {
            for(int j = 0; j < map[0].length; j++) {
                int ran = (int) (Math.random() * 5 + 1);
                map[i][j] = ran;
            }
        }
    }

    public void draw(Graphics2D g) {

        for (int row = 0; row < map.length; row++) {
            for (int col = 0; col < map[0].length; col++) {
                if (map[row][col] > 0) {
                    if (map[row][col] == 1) {
                        g.setColor(new Color(200, 200, 200));
                    }
                    if (map[row][col] == 2) {
                        g.setColor(new Color(150, 150, 150));
                    }
                    if (map[row][col] == 3) {
                        g.setColor(new Color(100, 100, 100));
                    }
                    if (map[row][col] == Power.PWIDTH) {
                        g.setColor(Power.PWCOLOR);
                    }
                    if (map[row][col] == Power.PSPEED) {
                        g.setColor(Power.PSCOLOR);
                    }
                    g.fillRect(col*brickWidth+HPAD, row * brickHeight + WPAD, brickWidth, brickHeight);
                    g.setColor(Color.BLACK);
                    g.drawRect(col*brickWidth+HPAD, row * brickHeight + WPAD, brickWidth, brickHeight);
                }
            }
        }
    }
    public boolean isWin() {
        boolean winner = false;
        int brickRemaining = 0;

        for (int row = 0; row < map.length; row++) {
            for (int col = 0; col < map[0].length; col++) {
                brickRemaining += map[row][col];
            }
        }

        if (brickRemaining == 0) {
            winner = true;
        }

        return winner;
    }
    public int[][] getMap() { return map; }
    public void setMap(int row, int col, int val) {
        map[row][col] = val;
    }
    public int getBrickWidth() { return brickWidth; }
    public int getBrickHeight() { return brickHeight; }
    public void hitB(int row, int col) {
        map[row][col] -= 1;
        if (map[row][col] < 0) {
            map[row][col] = 0;
        }
    }
}
