package main;

import java.awt.*;


public class Power {
    private int x, y, dy, type, w, h;
    private boolean onScreen;
    private boolean flag;
    private Color color;
    public final static int PWIDTH = 4;
    public final static int PSPEED = 5;
    public final static Color PWCOLOR = new Color(0, 0, 255);
    public final static Color PSCOLOR = new Color(255, 40, 60);
    public Power(int xStart, int yStart, int typeStart, int width, int height) {

        x = xStart;
        y = yStart;
        type = typeStart;
        w = width;
        h = height;
        if (type < 4) {
            type = 4;
        }
        if (type > 5) {
            type = 5;
        }
        if (type == PWIDTH) {
            color = PWCOLOR;
        }
        if (type == PSPEED) {
            color = PSCOLOR;
        }

        dy = (int) (Math.random() * 6 + 1);

    }

    public void draw(Graphics2D g) {
        g.setColor(color);
        g.fillRect(x, y, w, h);
    }

    public void update() {
        y += dy;
        if (y > BBMain.HEIGHT) {
            onScreen = false;
        }
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }


    public int getDy() {
        return dy;
    }

    public void setDy(int dy) {
        this.dy = dy;
    }

    public int getType() {
        return type;
    }

    public boolean isOnScreen() {
        return onScreen;
    }

    public void setOnScreen(boolean onScreen) {
        this.onScreen = onScreen;
    }
    public boolean checkCol() { return flag; }
    public void setCol(boolean flag) { this.flag = flag; }

    public Rectangle getRect() {
        return new Rectangle(x, y, w, h);
    }
}
