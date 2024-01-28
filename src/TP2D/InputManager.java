package TP2D;

import java.awt.event.KeyEvent;

public class InputManager {
    private static volatile InputManager instance = null;
    private boolean up = false, down = false, left = false, right = false;

    DynamicThings thingToMove;

    private InputManager(DynamicThings thingToMove){
        this.thingToMove = thingToMove;
    }

    public final static InputManager getInstance(DynamicThings thingToMove){
        if (InputManager.instance == null) {
            synchronized(InputManager.class) {
                if (InputManager.instance == null) {
                    InputManager.instance = new InputManager(thingToMove);
                }
            }
        }
        return InputManager.instance;
    }


    public void setOrientation(Orientation o) {
        switch (o) {
            case UP:    up = true;      break;
            case DOWN:  down = true;    break;
            case LEFT:  left = true;    break;
            case RIGHT: right = true;   break;
        }
        
        thingToMove.setOrientation(o);
        thingToMove.setWalking(true);
    }

    public void resetOrientation(Orientation o) {
        switch (o) {
            case UP:    up = false;     break;
            case DOWN:  down = false;   break;
            case LEFT:  left = false;   break;
            case RIGHT: right = false;  break;
        }

        if(up)          setOrientation(Orientation.UP);
        else if(down)   setOrientation(Orientation.DOWN);
        else if(left)   setOrientation(Orientation.LEFT);
        else if(right)  setOrientation(Orientation.RIGHT);
        else thingToMove.setWalking(false);
    }
    
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()){
            case KeyEvent.VK_UP:    this.setOrientation(Orientation.UP);    break;
            case KeyEvent.VK_DOWN:  this.setOrientation(Orientation.DOWN);  break;
            case KeyEvent.VK_LEFT:  this.setOrientation(Orientation.LEFT);  break;
            case KeyEvent.VK_RIGHT: this.setOrientation(Orientation.RIGHT); break;
        }
        //this.repaint();
    }

    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()){
            case KeyEvent.VK_UP:    this.resetOrientation(Orientation.UP);      break;
            case KeyEvent.VK_DOWN:  this.resetOrientation(Orientation.DOWN);    break;
            case KeyEvent.VK_LEFT:  this.resetOrientation(Orientation.LEFT);    break;
            case KeyEvent.VK_RIGHT: this.resetOrientation(Orientation.RIGHT);   break;
        }
    }
}
