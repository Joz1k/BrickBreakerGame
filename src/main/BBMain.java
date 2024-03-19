package main;

import javax.swing.JFrame;

public class BBMain {
    public static final int WIDTH = 640, HEIGHT = 480;

    public static void main(String[] args) {
        JFrame jFrame = new JFrame("Brick Breaker");

        GamePanel thePanel = new GamePanel();

        Thread theThread = new Thread(thePanel);

        jFrame.setLocationRelativeTo(null);
        jFrame.setResizable(false);
        jFrame.setSize(WIDTH, HEIGHT);
        jFrame.add(thePanel);
        theThread.start();
        jFrame.setDefaultCloseOperation(jFrame.EXIT_ON_CLOSE);
        jFrame.setVisible(true);
    }
}
