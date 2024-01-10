package TP2D;

import com.sun.tools.javac.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class MainInterface extends JFrame implements KeyListener {
    TileManager tileManager = new TileManager(48,48,"./img/tileSet.png");
    Dungeon dungeon = new Dungeon(15,10,tileManager);
    Hero hero = Hero.getInstance();
    GameRender panel = new GameRender(dungeon,hero);

    InputManager inputManager = InputManager.getInstance(hero);

    public MainInterface() throws HeadlessException {
        super();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.getContentPane().add(panel);
        this.setVisible(true);
        this.setSize(new Dimension(400,600));
        this.addKeyListener(this);

        ActionListener animationTimer=new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hero.moveIfPossible(6,dungeon);
                repaint();
            }
        };
        Timer timer = new Timer(50,animationTimer);
        timer.start();
    }

    public static void main(String[] args){
        MainInterface mainInterface = new MainInterface();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        inputManager.keyPressed(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        inputManager.keyReleased(e);
    }
}
