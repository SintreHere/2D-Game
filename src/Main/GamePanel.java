package Main;

import Entity.Player;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable{

    final int originalTileSize = 16;
    final int scale = 3;


    public final  int tileSize = originalTileSize * scale;
    final int maxScreenCol = 16;

    final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenCol;
    final int screenHeight = tileSize * maxScreenRow;

    int FPS = 120;


    KeyHandler keyH = new KeyHandler();
    Thread gameThread;

    Player player = new Player(this,keyH);


    int playerX=100;
    int playerY=100;
    int playerSpeed = 4;


    public GamePanel() {

        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        super.addKeyListener(keyH);
        this.setFocusable(true);
        this.requestFocusInWindow();
        this.startGameThread();
    }



    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {

        double drawInterval = 1000000000/FPS; //0.016 seconds

        double nextDrawTime = System.nanoTime() + drawInterval;


        while(gameThread!=null) {


            update();
            repaint();


            double remainingTime = nextDrawTime - System.nanoTime();
            remainingTime = remainingTime/1000000;


            if(remainingTime < 0) {
                remainingTime = 0;
            }

            try {
                Thread.sleep((long)remainingTime);


                nextDrawTime += drawInterval;
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }

    }

    public void update() {
        Player.update();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);



        Graphics2D g2 = (Graphics2D)g;
        g2.setColor(Color.white);
        Player.draw(g2);
    }

}
