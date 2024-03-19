package main;

import java.awt.*;

public class Paddle {

    private double x;
    private int width, height, startWidth;
    private long widthTimer;
    private boolean flagWidth;
    public final int Y = BBMain.HEIGHT - 100;
    public Paddle (int w, int h) {
        flagWidth = false;
        width = w;
        startWidth = w;
        height = h;
        x = (BBMain.WIDTH  - width) / 2;
    }

    public void update() {
        if ((System.nanoTime() - widthTimer) / 1000 > 9000000 && flagWidth) {
            width = startWidth;
            flagWidth = false;
        }
    }

    public void draw(Graphics2D g) {
        g.setColor(Color.DARK_GRAY);
        g.fillRect((int) x, Y, width, height);

        if (flagWidth) {
            g.setColor(Color.WHITE);
            g.setFont(new Font("Courier New", Font.BOLD, 18));
            g.drawString("Время действия: " + (9 - (System.nanoTime() - widthTimer) / 1000000000), (int) x, Y + 18);
        }
    }

    public void mouseMoved(int xPos) {
        x = xPos;
        if (x > BBMain.WIDTH - width) {
            x = BBMain.WIDTH - width;
        }
    }

    public Rectangle getRect() {
        return new Rectangle((int)x, Y, width, height);
    }


    public int getWidth() { return width; }
    public void setWidth(int w) {
        this.width = w;
        flagWidth = true;
        setWidthTimer();
    }

    public void setWidthTimer() {
        this.widthTimer = System.nanoTime();
    }
}
