package Entity;

import Main.GamePanel;
import Main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends entity {

    private static Player instance ;

    static GamePanel gp;
    static KeyHandler keyH;
    private static int spriteNum;
    private static int spriteCounter;
    public final int screenX;
    public final int screenY;

    public Player (GamePanel gp, KeyHandler keyH){
        this.gp = gp;
        this.keyH = keyH;

        screenX = gp.screenWidth/2 - (gp.tileSize/2);
        screenY = gp.screenHeight/2 - (gp.tileSize/2);

        setDefaultValues();
        getPlayerImage();
        instance = this; // Assign the instance to the static field
    }


    public void setDefaultValues(){
        worldX=gp.tileSize * 23;
        worldY=gp.tileSize *21;
        speed=2;
        direction = "down";
        spriteNum = 1; // Start with the first sprite
        spriteCounter = 0; // Reset sprite counter

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


    public static void update() {
        if (instance == null) {
            return; // Ensure the instance exists before updating
        }

        boolean moving = false;

        if (instance.keyH.upPressed) {
            instance.worldY -= instance.speed;
            instance.direction = "up";
            moving = true;
        }

        if (instance.keyH.downPressed) {
            instance.worldY += instance.speed;
            instance.direction = "down";
            moving = true;
        }

        if (instance.keyH.leftPressed) {
            instance.worldX -= instance.speed;
            instance.direction = "left";
            moving = true;
        }

        if (instance.keyH.rightPressed) {
            instance.worldX += instance.speed;
            instance.direction = "right";
            moving = true;
        }

        if (moving) {
            instance.spriteCounter++;
            if (instance.spriteCounter > 15) { // Adjust for half a second
                instance.spriteNum = (instance.spriteNum == 1) ? 2 : 1;
                instance.spriteCounter = 0;
            }
        }
    }

    // Static draw method
    public static void draw(Graphics2D g2) {
        if (instance == null) {
            return; // Ensure the instance exists before drawing
        }

        BufferedImage image = null;

        switch (instance.direction) {
            case "up":
                image = (instance.spriteNum == 1) ? instance.up1 : instance.up2;
                break;
            case "down":
                image = (instance.spriteNum == 1) ? instance.down1 : instance.down2;
                break;
            case "left":
                image = (instance.spriteNum == 1) ? instance.left1 : instance.left2;
                break;
            case "right":
                image = (instance.spriteNum == 1) ? instance.right1 : instance.right2;
                break;
            default:
                image = instance.down1;
                break;
        }

        g2.drawImage(image, instance.screenX, instance.screenY, instance.gp.tileSize, instance.gp.tileSize, null);
    }
}
