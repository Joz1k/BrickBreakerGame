package main;

import java.awt.*;

public class Ball {

    private double x, y, dx, dy;
    private final int ballSize = 30;
    public Ball() {
        x = 200;
        y = 200;
        dx = 1;
        dy = 3;
    }

    public void update() {
        setPosition();
    }

    public void setPosition() {
        x+=dx;
        y+=dy;

        if (x < 0) {
            dx = -dx;
        }
        if (y < 0) {
            dy = -dy;
        }
        if (x > BBMain.WIDTH - ballSize) {
            dx = -dx;
        }
        if (y > BBMain.HEIGHT - ballSize) {
            dy = -dy;
        }
    }

    public void draw(Graphics2D g) {
        g.setColor(Color.DARK_GRAY);
        g.setStroke(new BasicStroke(4));
        g.drawOval((int)x,(int) y,ballSize,ballSize);
    }

    public Rectangle getRect() {
        return new Rectangle((int)x, (int)y, ballSize, ballSize);
    }

    public void changeDirY() { dy = -dy; }
    public void changeDirX() { dx = -dx; }
    public void setDX(double newX) { dx = newX; }
    public double getDX() { return dx; }
    public double getX() { return x; }

    public boolean isLose() {
        boolean loser = false;
        if (y > BBMain.HEIGHT - ballSize*2) {
            loser = true;
        }

        return loser;
    }
}
