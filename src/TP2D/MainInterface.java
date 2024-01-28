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
    
    TileManager tileManager1 = new TileManager(48,48,"./img/ClassicRPG_Sheet_scaled_3x_pngcrushed.png"); // TM for level 1
    TileManager tileManager2 = new TileManager(48,48,"./img/tileset_16x16_interior_scaled_3x_pngcrushed.png"); // TM for level 2

    Level[] levels = new Level[2];
    
    private int current_level = 0;
    
    Hero hero = Hero.getInstance();

    InputManager inputManager = InputManager.getInstance(hero);
    GameRender panel;

    public MainInterface() throws HeadlessException {
        super();
        
        levels[0] = new Level(0, 15,15,tileManager1);
        levels[1] = new Level(1, 15,15,tileManager2);

        panel = new GameRender(levels[current_level],hero);

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.getContentPane().add(panel);
        this.setVisible(true);
        this.setSize(new Dimension((int)(15.33*tileManager1.getWidth()),(int)(15.7*tileManager1.getWidth())));
        this.addKeyListener(this);

        ActionListener animationTimer=new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hero.moveIfPossible(6,levels[current_level]);

                System.out.print(hero.x);
                System.out.print(" ");
                System.out.println(hero.y);
                
                switch(current_level){  // Switch level based on player position
                    case 0 : if(hero.y < 10)  setLevel(1); break;
                    case 1 : 
                        if(hero.y > 653) setLevel(0); 
                        if(hero.x > 470 && hero.x < 490 && hero.y > 385 && hero.y < 410 && (text_step == 0 || text_step >= 10)){
                            levels[current_level].removeText();
                            levels[current_level].setText(text_step);
                            if(text_step < 10) text_step = 1;
                            else text_step = 100;
                        }
                    break;
                }

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
    public void keyTyped(KeyEvent touche) {






        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_SPACE && current_level == 1 && text_step<4 && text_step > 0){
            levels[current_level].removeText();
            levels[current_level].setText(text_step);
            text_step++;
        }
        if(text_step == 4){
            if(e.getKeyCode() == KeyEvent.VK_E){
                levels[current_level].removeText();
                text_step++;
            }
        } else {
            if(hero.x >= 430 && hero.x <= 600 && hero.orientation == Orientation.DOWN && current_level == 0 && text_step == 5){ // Grapling is possible
                if(e.getKeyCode() == KeyEvent.VK_E && levels[0].meubles.y > hero.y+60){
                    levels[0].meubles.move(0, -10);
                }
            }

            if(text_step == 100); // stop moving when game over 
            else inputManager.keyPressed(e);
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        inputManager.keyReleased(e);

        if(e.getKeyCode() == KeyEvent.VK_E){
            if(levels[0].meubles.y >280 && levels[0].meubles.y < 450) {
                levels[0].renderList.remove(levels[0].meubles); // Fall in water
                text_step = 50;
            }
            else if (levels[0].meubles.y < 450) text_step = 10; // Furnitures are retrieved 
        }
    }


    private int text_step = 0;
    private void setLevel(int level){
        double[] newPosition = new double[]{hero.x, hero.y};
        switch(level){
            case 0:
                newPosition = new double[]{336, 20};
            break;

            case 1:
                newPosition = new double[]{336, 650};
            break;

            default: break;
        }

        hero.x = newPosition[0];
        hero.y = newPosition[1];
        this.current_level = level;
        this.panel.setLevel(levels[current_level]);
    }
}
