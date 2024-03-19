package main;

import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class GamePanel extends JPanel implements Runnable {

    private boolean running;
    private BufferedImage image;
    private Graphics2D g;
    private MyMouseListener mouseListener;
    private int mouseX;

    private ArrayList<Power> powers;
    private Ball ball;
    private Paddle paddle;
    private Map map;
    private HUD hud;
    public GamePanel() {
        init();
    }

    public void init() {
        mouseX = 0;
        ball = new Ball();
        paddle = new Paddle(100, 20);
        map = new Map(3, 7);
        hud = new HUD();
        mouseListener = new MyMouseListener();
        powers = new ArrayList<Power>();
        addMouseMotionListener(mouseListener);
        running = true;
        // генерим картинку
        image = new BufferedImage(BBMain.WIDTH, BBMain.HEIGHT, BufferedImage.TYPE_INT_RGB);
        // Returns this component's graphics context, which lets you draw on a component.
        g = (Graphics2D) image.getGraphics();

        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    }

    @Override
    public void run() {

        while(running) {
            //update
            update();
            //draw
            draw();
            //display
            repaint();

            try {
                Thread.sleep(10);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void checkCollision() {
        Rectangle ballRect = ball.getRect();
        Rectangle padRect = paddle.getRect();

        for (int i = 0; i < powers.size(); i++) {

            Rectangle pRect = powers.get(i).getRect();

            if (padRect.intersects(pRect)) {
                if (powers.get(i).getType() == Power.PWIDTH && !powers.get(i).checkCol()) {
                    paddle.setWidth(paddle.getWidth() * 2);
                    powers.get(i).setCol(true);
                }
            }
        }

        if (ballRect.intersects(padRect)) {
            ball.changeDirY();
            if (ball.getX() < mouseX + paddle.getWidth() / 4) {
                ball.setDX(ball.getDX() - .5);
            }

            if (ball.getX() < mouseX + paddle.getWidth() && ball.getX() > mouseX + paddle.getWidth() / 4) {
                ball.setDX(ball.getDX() + .5);
            }
        }

        collision: for (int row = 0; row < map.getMap().length; row++) {
            for (int col = 0; col < map.getMap()[0].length; col++) {
                if (map.getMap()[row][col] > 0) {
                    int brickX = col * map.getBrickWidth() + map.HPAD, brickY = row * map.getBrickHeight() + map.WPAD,
                            brickW = map.getBrickWidth(), brickH = map.getBrickHeight();

                    Rectangle brickRect = new Rectangle(brickX, brickY, brickW, brickH);
                    if (ballRect.intersects(brickRect)) {

                        if (map.getMap()[row][col] > 3) {
                            powers.add(new Power(brickX, brickY, map.getMap()[row][col], brickW, brickH));
                            map.setMap(row, col, 3);
                        } else {
                            map.hitB(row, col);
                        }

                        map.hitB(row, col);
                        ball.changeDirY();
                        hud.addScore(50);
                        break collision;
                    }
                }
            }
        }
    }

    public void update() {
        checkCollision();
        ball.update();
        paddle.update();
        for(Power power : powers) {
            power.update();
        }
    }

    public void draw() {

        g.setColor(Color.WHITE);

        g.fillRect(0, 0, BBMain.WIDTH, BBMain.HEIGHT);
        map.draw(g);
        ball.draw(g);
        paddle.draw(g);
        hud.draw(g);
        drawPowers();
        if (map.isWin() == true) {
            drawWin();
            running = false;
        }

        if (ball.isLose()) {
            drawLose();
            running = false;
        }
    }

    public void drawWin() {
        g.setColor(Color.RED);
        g.setFont(new Font("Courier New", Font.BOLD, 50));
        g.drawString("YOU WIN", 200, 200);
    }

    public void drawPowers() {
        for (Power power : powers) {
            power.draw(g);
        }
    }

    public void drawLose() {
        g.setColor(Color.RED);
        g.setFont(new Font("Courier New", Font.BOLD, 50));
        g.drawString("YOU LOSE", 200, 200);
    }

    public void paintComponent(Graphics g) {
        Graphics2D g2D = (Graphics2D) g;

        g2D.drawImage(image, 0, 0, BBMain.WIDTH, BBMain.HEIGHT, null);

        g2D.dispose();
    }

    public class MyMouseListener implements MouseMotionListener {


        @Override
        public void mouseDragged(MouseEvent e) {

        }

        @Override
        public void mouseMoved(MouseEvent e) {
            mouseX = e.getX();
            paddle.mouseMoved(e.getX());
        }

    }

//    public void display() {
//
//    }
}
