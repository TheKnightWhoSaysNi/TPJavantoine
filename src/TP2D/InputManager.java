package TP2D;

public class InputManager {
    private static volatile InputManager instance = null;
    private boolean up = false, down = false, left = false, right = false;

    private InputManager(){}


    public final static InputManager getInstance(){
        if (InputManager.instance == null) {
            synchronized(InputManager.class) {
                if (InputManager.instance == null) {
                    InputManager.instance = new InputManager();
                }
            }
        }
        return InputManager.instance;
    }


    public void setOrientation(Orientation o) {
        switch (o) {
            case UP:
                up = true;
                break;
            case DOWN:
                down = true;
                break;
            case LEFT:
                left = true;
                break;
            case RIGHT:
                right = true;
                break;
        }
    }

    public void resetOrientation(Orientation o) {
        switch (o) {
            case UP:
                up = false;
                break;
            case DOWN:
                down = false;
                break;
            case LEFT:
                left = false;
                break;
            case RIGHT:
                right = false;
                break;
        }
    }

    public boolean isOrientationTrue(){
        return up||down||left||right;
    }

}
