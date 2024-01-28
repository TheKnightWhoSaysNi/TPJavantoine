package TP2D;

import javax.swing.*;
import java.awt.*;

public class AnimatedThings extends SolidThings {
    public AnimatedThings(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    public AnimatedThings(int x, int y, Image image) {
        super(x, y, image);
    }

    @Override
    public void draw(Graphics g){
        int index = (int) ((System.currentTimeMillis()/200)%4);
        g.drawImage(image,(int)x,(int)y,(int)x+48,(int)y+48,index*48,0,(index+1)*48,48,null,null);
    }
}
