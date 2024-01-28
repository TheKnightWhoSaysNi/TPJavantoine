package TP2D;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import java.awt.*;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Level {
    private final int height;
    private final int width;
    private final TileManager tileManager;
    public ArrayList <Things> renderList = new ArrayList<>();

    private int level;

    private char[][][] map;

    BufferedImage pnjImage;
    BufferedImage water1Image;
    BufferedImage water2Image;
    BufferedImage textAuSecours;
    BufferedImage textMesMeubles;
    BufferedImage textVaLesChercher;
    BufferedImage textMerciFlemme;
    BufferedImage textMesVetements;
    BufferedImage imgMeubles;
    BufferedImage gameOverGood;
    BufferedImage gameOverBad;

    Things currentText;
    public SolidThings meubles;
    
    public Level(int level, int height, int width, TileManager tileManager) {
        this.level = level;
        this.height = height;
        this.width = width;
        this.tileManager = tileManager;

        
        try{ 
            pnjImage          = ImageIO.read(new File("./img/pnjapoal.png"));
            water1Image       = ImageIO.read(new File("./img/ClassicRPG_Sheet_scaled_3x_pngcrushed.png"));
            water2Image       = ImageIO.read(new File("./img/water2.png"));
            textAuSecours     = ImageIO.read(new File("./img/Texts/au_secours.png"));
            textMesMeubles    = ImageIO.read(new File("./img/Texts/mes_meubles.png"));
            textMesVetements  = ImageIO.read(new File("./img/Texts/vetements.png"));
            textVaLesChercher = ImageIO.read(new File("./img/Texts/va_les_chercher.png"));
            textMerciFlemme   = ImageIO.read(new File("./img/Texts/merci_flemme.png"));
            gameOverGood      = ImageIO.read(new File("./img/Texts/game_over_good.png"));
            gameOverBad       = ImageIO.read(new File("./img/Texts/game_over_bad.png"));
            imgMeubles        = ImageIO.read(new File("./img/meubles.png"));
        } catch(IOException e){
            e.printStackTrace();
        }

        if(level == 0) this.map = new char[][][] {
            {
                {'F', 'F', 'F', 'F', 'F', 'F', 'F', 'F', 'F', 'F', 'F', 'F', 'F', 'F', 'F'}, 
                {'F', 'G', 'F', 'F', 'F', 'F', 'F', 'F', 'F', 'F', 'F', 'F', 'F', 'F', 'F'}, 
                {'F', 'F', 'F', 'F', 'F', 'G', 'F', 'F', 'F', 'F', 'F', 'F', 'F', 'G', 'F'}, 
                {'F', 'F', 'F', 'F', 'F', 'F', 'F', 'F', 'F', 'F', 'G', 'F', 'F', 'F', 'F'}, 
                {'F', 'F', 'F', 'F', 'F', 'F', 'F', 'F', 'F', 'F', 'F', 'F', 'F', 'F', 'F'}, 
                {'F', 'F', 'F', 'G', 'F', 'F', 'F', 'F', 'F', 'F', 'F', 'F', 'F', 'F', 'F'}, 
                {'F', 'F', 'F', 'F', 'F', 'F', 'G', 'F', 'F', 'F', 'F', 'F', 'G', 'F', 'F'}, 
                {'~', '~', '~', '~', '~', '~', '~', '~', '~', '~', '~', '~', '~', '~', '~'}, 
                {'w', 'W', 'w', 'W', 'w', 'W', 'w', 'W', 'w', 'W', 'w', 'W', 'w', 'W', 'w'}, 
                {'W', 'w', 'W', 'w', 'W', 'w', 'W', 'w', 'W', 'w', 'W', 'w', 'W', 'w', 'W'}, 
                {'=', '=', '=', '=', '=', '=', '=', '=', '=', '=', '=', '=', '=', '=', '=',}, 
                {'F', 'G', 'F', 'F', 'F', 'F', 'F', 'F', 'F', 'G', 'F', 'F', 'F', 'G', 'F'}, 
                {'F', 'F', 'F', 'G', 'F', 'F', 'F', 'F', 'F', 'F', 'F', 'G', 'F', 'F', 'F'}, 
                {'F', 'F', 'F', 'F', 'F', 'F', 'F', 'G', 'F', 'F', 'F', 'F', 'F', 'F', 'F'}, 
                {'F', 'F', 'F', 'F', 'F', 'F', 'F', 'F', 'F', 'F', 'F', 'F', 'F', 'F', 'F'}, 
            },{
                {'>', '-', '-', '-', '-', '<', ' ', ' ', ' ', '>', '-', '-', '-', '-', '<'}, 
                {'|', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '|'}, 
                {'|', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '|'}, 
                {'|', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '|'}, 
                {'|', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '|'}, 
                {'|', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '|'}, 
                {'|', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '|'}, 
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}, 
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}, 
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}, 
                {'|', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '|'}, 
                {'|', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '|'}, 
                {'|', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '|'}, 
                {'|', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '|'}, 
                {'>', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '<'},
            }
        };        
        
        else this.map = new char[][][] {
            {
                {'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B'}, 
                {'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B'}, 
                {'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B'}, 
                {'B', 'B', '°', '°', '°', '°', '°', '°', '°', '°', '°', '°', '°', 'B', 'B'}, 
                {'B', 'B', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'B', 'B'}, 
                {'B', 'B', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'B', 'B'}, 
                {'B', 'B', 'F', 'F', 'F', 'F', 'F', 'F', 'F', 'F', 'F', 'F', 'F', 'B', 'B'}, 
                {'B', 'B', 'F', 'F', 'F', 'F', 'F', 'F', 'F', 'F', 'F', 'F', 'F', 'B', 'B'}, 
                {'B', 'B', 'F', 'F', 'F', 'F', 'F', 'F', 'F', 'F', 'F', 'F', 'F', 'B', 'B'}, 
                {'B', 'B', 'F', 'F', 'F', 'F', 'F', 'F', 'F', 'F', 'F', 'F', 'F', 'B', 'B'}, 
                {'B', 'B', 'F', 'F', 'F', 'F', 'F', 'F', 'F', 'F', 'F', 'F', 'F', 'B', 'B'}, 
                {'B', 'B', 'F', 'F', 'F', 'F', 'F', 'F', 'F', 'F', 'F', 'F', 'F', 'B', 'B'}, 
                {'B', 'B', 'F', 'F', 'F', 'F', 'F', 'F', 'F', 'F', 'F', 'F', 'F', 'B', 'B'}, 
                {'B', 'B', 'F', 'F', 'F', 'F', 'F', 'F', 'F', 'F', 'F', 'F', 'F', 'B', 'B'}, 
                {'B', 'B', 'F', 'F', 'F', 'F', 'F', 'F', 'F', 'F', 'F', 'F', 'F', 'B', 'B'}, 
            }, {
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}, 
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}, 
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}, 
                {' ', ' ', '>', '-', '-', '-', '-', '-', '-', '-', '-', '-', '<', ' ', ' '}, 
                {' ', ' ', '|', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'l', ' ', ' '}, 
                {' ', ' ', '|', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'l', ' ', ' '}, 
                {' ', ' ', '|', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'l', ' ', ' '}, 
                {' ', ' ', '|', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'J', ' ', 'l', ' ', ' '}, 
                {' ', ' ', '|', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'l', ' ', ' '}, 
                {' ', ' ', '|', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'l', ' ', ' '}, 
                {' ', ' ', '|', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'l', ' ', ' '}, 
                {' ', ' ', '|', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'l', ' ', ' '}, 
                {' ', ' ', '|', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'l', ' ', ' '}, 
                {' ', ' ', '|', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'l', ' ', ' '}, 
                {' ', ' ', '|', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'l', ' ', ' '}, 
            }
        };

        meubles = new SolidThings(450, 470, imgMeubles);
        
        respawnListOfThings();
    }
    private void respawnListOfThings(){

        int[] floor     = {0, 0};   
        int[] barrierV  = {0, 0};
        int[] barrierVR = {0, 0};
        int[] barrierH  = {0, 0};
        int[] barrierL  = {0, 0};
        int[] barrierR  = {0, 0};
        int[] grass     = {0, 0};   
        int[] black     = {0, 0};   
        int[] wall2     = {0, 0}; 
        int[] wall1     = {0, 0}; 
        int[] wall0     = {0, 0}; 
        int[] pnj       = {0, 0}; 
        int[] water1    = {0, 0}; 
        int[] water2    = {0, 0}; 
        int[] bordH     = {0, 0};
        int[] bordL     = {0, 0};
        
        switch(level){
            case 0 : 
                floor    = new int[] {5, 2};
                barrierV = new int[] {3, 5};
                barrierH = new int[] {4, 5};
                barrierL = new int[] {5, 6};
                barrierR = new int[] {4, 6};
                grass    = new int[] {8, 0};
                pnj      = new int[] {1, 0};
                water1   = new int[] {13, 0}; 
                water2   = new int[] {13, 1}; 
                bordH    = new int[] {5, 1};
                bordL    = new int[] {5, 3};
            break;

            case 1 : 
                floor    = new int[] {0, 0};
                barrierV = new int[] {0, 3};
                barrierVR= new int[] {2, 3};
                barrierH = new int[] {1, 2};
                barrierL = new int[] {0, 2};
                barrierR = new int[] {2, 2};
                black    = new int[] {1, 3};
                wall2    = new int[] {7, 1}; 
                wall1    = new int[] {7, 2}; 
                wall0    = new int[] {7, 3}; 
                pnj      = new int[] {5, 11}; 
            break;
        }

        renderList.clear();
        for(int i=0; i<this.map.length; i++){
            for (int x=0;x<width;x++){
                for (int y=0;y<height;y++){
                    switch (this.map[i][y][x]){
                        case 'F' :  renderList.add(new Things(x* tileManager.getWidth(),y* tileManager.getHeigth(), tileManager.getTile(level==1?floor[0]+(x%3):floor[0], floor[1])));
                                    break;
                        case '|' :  if(level == 1) renderList.add(new Things(x* tileManager.getWidth(),y* tileManager.getHeigth(), tileManager.getTile(barrierV[0], barrierV[1])));
                                    else renderList.add(new SolidThings(x* tileManager.getWidth(),y* tileManager.getHeigth(), tileManager.getTile(barrierV[0], barrierV[1])));
                                    break;
                        case 'l' :  if(level == 1) renderList.add(new Things(x* tileManager.getWidth(),y* tileManager.getHeigth(), tileManager.getTile(barrierVR[0], barrierVR[1])));
                                    else renderList.add(new SolidThings(x* tileManager.getWidth(),y* tileManager.getHeigth(), tileManager.getTile(barrierVR[0], barrierVR[1])));
                                    break;
                        case '-' :  renderList.add(new SolidThings(x* tileManager.getWidth(),y* tileManager.getHeigth(), tileManager.getTile(barrierH[0], barrierH[1])));
                                    break;
                        case '>' :  renderList.add(new SolidThings(x* tileManager.getWidth(),y* tileManager.getHeigth(), tileManager.getTile(barrierL[0], barrierL[1])));
                                    break;
                        case '<' :  renderList.add(new SolidThings(x* tileManager.getWidth(),y* tileManager.getHeigth(), tileManager.getTile(barrierR[0], barrierR[1])));
                                    break;
                        case 'B' :  renderList.add(new SolidThings(x* tileManager.getWidth(),y* tileManager.getHeigth(), tileManager.getTile(black[0], black[1])));
                                    break;
                        case 'E' :  renderList.add(new Things(x* tileManager.getWidth(),y* tileManager.getHeigth(), tileManager.getTile(0,1)));
                                    break;
                        case 'G' :  renderList.add(new Things(x* tileManager.getWidth(),y* tileManager.getHeigth(), tileManager.getTile(grass[0], grass[1])));
                                    break;
                        case '°' :  renderList.add(new SolidThings(x* tileManager.getWidth(),y* tileManager.getHeigth(), tileManager.getTile(wall2[0], wall2[1])));
                                    break; 
                        case 'o' :  renderList.add(new SolidThings(x* tileManager.getWidth(),y* tileManager.getHeigth(), tileManager.getTile(wall1[0], wall1[1])));
                                    break;             
                        case 'O' :  renderList.add(new Things(x* tileManager.getWidth(),y* tileManager.getHeigth(), tileManager.getTile(wall0[0], wall0[1])));
                                    break;            
                        case 'J' :  renderList.add(new AnimatedThings(x* tileManager.getWidth(),y* tileManager.getHeigth(), pnjImage));
                                    break;            
                        case 'w' :  renderList.add(new AnimatedThings(x* tileManager.getWidth(),y* tileManager.getHeigth(), water1Image));
                                    break;            
                        case 'W' :  renderList.add(new AnimatedThings(x* tileManager.getWidth(),y* tileManager.getHeigth(), water2Image));
                                    break;            
                        case '~' :  renderList.add(new SolidThings(x* tileManager.getWidth(),y* tileManager.getHeigth(), tileManager.getTile(bordL[0], bordL[1])));
                                    break;            
                        case '=' :  renderList.add(new Things(x* tileManager.getWidth(),y* tileManager.getHeigth(), tileManager.getTile(bordH[0], bordH[1])));
                                    break;
                    }
                }
            }
        }

        if(level == 0) renderList.add(meubles); // Display furniture
    }
    

    public void setText(int i){
        switch(i){
            case 0: currentText = new Things(160, 20, textAuSecours);       break;
            case 1: currentText = new Things(160, 20, textMesMeubles);      break;
            case 2: currentText = new Things(160, 20, textMesVetements);    break;
            case 3: currentText = new Things(160, 20, textVaLesChercher);   break;
            case 4: currentText = new Things(160, 20, textMerciFlemme);     break;
            case 10: currentText = new Things(160, 20, gameOverGood);       break;
            case 50: currentText = new Things(160, 20, gameOverBad);        break;
        }
        renderList.add(currentText);
    }

    public void removeText(){
        renderList.remove(currentText);
    }

    public void displayLevelInConsole(HitBox hero){
        for(int y=0;y<height;y++){
            for(int x=0;x<width;x++){
                if (x==(hero.getX()/ tileManager.getWidth()) && y==(hero.getY()/ tileManager.getHeigth())) {
                    System.out.print("H");
                }
                else {
                    System.out.print((map[x][y]));
                }
            }
            System.out.println("");
        }

    }

    public ArrayList<Things> getRenderList() {
        return renderList;
    }
}
