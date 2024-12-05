package Entity;

import Main.GamePanel;
import Main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends entity {


    static GamePanel gp;
    static KeyHandler keyH;

    public Player (GamePanel gp, KeyHandler keyH){
        this.gp = gp;
        this.keyH = keyH;

        setDefaultValues();
        getPlayerImage();
    }


    public void setDefaultValues(){
        x=100;
        y=100;
        speed=2;
        direction = "down";


    }


    public void getPlayerImage(){

        try{
            up1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/top-1.png"));
            up2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/top-2.png"));
            left1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/left-1.png"));
            left2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/left-2.png"));
            right1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/right-1.png"));
            right2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/right-2.png"));
            down1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/bottom-1.png"));
            down2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/bottom-2.png"));
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }


    public static void update(){

        if(keyH.upPressed)
        {
            y -= speed;
            direction = "up";
        }

        if(keyH.downPressed == true)
        {
            y += speed;
            direction = "down";

        }

        if(keyH.leftPressed == true)
        {
            x -= speed;
            direction = "left";

        }

        if(keyH.rightPressed == true)
        {
            x += speed;
            direction = "right";

        }


    }

    public static void draw(Graphics2D g2){
        //  g2.fillRect(x, y, gp.tileSize , gp.tileSize ); -> for rectangle
        //   g2.dispose();

        BufferedImage image = null;

        switch (direction){

            case "up":
                image = up1;
                break;

            case "down":
                image = down1;
                break;


            case "left":
                image = left1;
                break;


            case "right":
                image = right1;
                break;

            default:
                image = down1;
                break;
        }

        g2.drawImage(image, x,y,gp.tileSize,gp.tileSize,null);


    }

}
