package Main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{



    public boolean upPressed = false , downPressed = false, leftPressed= false, rightPressed= false;

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void keyPressed(KeyEvent e) {
        // TODO Auto-generated method stub

        int code = e.getKeyCode();
        System.out.println(code);
        if(code == KeyEvent.VK_C || code == KeyEvent.VK_UP) {
            upPressed = true;
        }

        if(code == KeyEvent.VK_DOWN || code == KeyEvent.VK_KP_DOWN) {
            downPressed = true;
        }

        if(code == KeyEvent.VK_LEFT || code == KeyEvent.VK_KP_LEFT) {
            leftPressed = true;
        }

        if(code == KeyEvent.VK_RIGHT || code == KeyEvent.VK_KP_RIGHT) {
            rightPressed = true;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub

        int code = e.getKeyCode();

        if(code == KeyEvent.VK_KP_UP || code == KeyEvent.VK_UP) {
            upPressed = false;
        }

        if(code == KeyEvent.VK_DOWN || code == KeyEvent.VK_KP_DOWN) {
            downPressed = false;
        }

        if(code == KeyEvent.VK_LEFT || code == KeyEvent.VK_KP_LEFT) {
            leftPressed = false;
        }

        if(code == KeyEvent.VK_RIGHT || code == KeyEvent.VK_KP_RIGHT) {
            rightPressed = false;
        }

    }



}
