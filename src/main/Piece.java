package main;

import java.awt.*;

public class Piece {
    private double x, y, dx, dy, gravity;
    private Color color;
    private Map map;
    private int size;

    public Piece(double brickX, double brickY, Map map) {
        this.map = map;
        x = brickX + map.getBrickWidth() / 2.0;
        y = brickY + map.getBrickHeight() / 2.0;
        dx = Math.random() * 30 - 15;
        dy = Math.random() * 30 - 15;
        size = (int) (Math.random() * 15 + 2);
        color = new Color(200, 200, 200);
        gravity = 0.8;
    }

    public void update() {
        x += dx;
        y += dy;
        dy += gravity;
    }

    public void draw(Graphics2D g) {
        g.setColor(color);
        g.fillRect((int) x, (int) y, size, size);

    }
}
