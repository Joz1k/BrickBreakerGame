package main;

import java.awt.*;

public class HUD {

    private int score;
    public HUD () {
        init();
    }

    public void init() {
        score = 0;
    }

    public void draw(Graphics2D g) {
        g.setFont(new Font("Courier New", Font.PLAIN, 14));
        g.setColor(Color.GREEN);
        g.drawString("Score: " + score, 20, 20);
    }

    public int getScore() { return score; }

    public void addScore(int add) {
        score += add;
    }
}
