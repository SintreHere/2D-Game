package Main;

import Entity.Player;
import Tile.TileManager;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable{

    final int originalTileSize = 16;
    final int scale = 3;


    public final  int tileSize = originalTileSize * scale;
   public final int maxScreenCol = 16;

   public final int maxScreenRow = 12;
   public final int screenWidth = tileSize * maxScreenCol;
   public final int screenHeight = tileSize * maxScreenRow;


   public final int maxWorldCol = 100;
    public final int maxWorldRow = 100;
    public final int worldWidth = tileSize + maxWorldCol;
    public final int worldHeight = tileSize + maxWorldRow;


    int FPS = 120;

    TileManager tm = new TileManager(this);
    KeyHandler keyH = new KeyHandler();
    Thread gameThread;

    public Player player = new Player(this,keyH);


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
        player.update();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;

        tm.draw(g2);
        player.draw(g2);
        g2.dispose();
    }

}
