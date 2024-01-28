package TP2D;

import javax.swing.*;
import java.awt.*;

public class DynamicThings extends AnimatedThings{

    protected Orientation orientation=Orientation.RIGHT;
    protected boolean isWalking = false;

    public DynamicThings(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    public DynamicThings(int x, int y, Image image) {
        super(x, y, image);
    }

    public void move(double moveX, double moveY){
        this.x=this.x+moveX;
        this.y=this.y+moveY;
    }

    public void setImage(Image image){
        this.image=image;
    }

    public void setOrientation(Orientation orientation) {
        this.orientation = orientation;
    }

    public void setWalking(boolean walking) {
        isWalking = walking;
    }
}
