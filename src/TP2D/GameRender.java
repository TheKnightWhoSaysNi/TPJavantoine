package TP2D;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameRender extends JPanel {
    private Level Level;
    private Hero hero;

    public GameRender(Level Level, DynamicThings hero) {
        this.Level = Level;
        this.hero = Hero.getInstance();
    }

    public void setLevel(Level Level) {
        this.Level = Level;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Things t : Level.getRenderList()){
            t.draw(g);
        }
        hero.draw(g);
    }
}
