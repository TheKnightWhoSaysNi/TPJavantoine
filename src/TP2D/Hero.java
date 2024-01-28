package TP2D;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.util.Date;

public final class Hero extends DynamicThings{

    private static volatile Hero instance = null;

    private Hero() {
        super(150,150, 48,52);
        try{this.setImage(ImageIO.read(new File("./img/heroTileSheet.png")));}
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public final static Hero getInstance() {
        if (Hero.instance == null) {
            synchronized(Hero.class) {
                if (Hero.instance == null) {
                    Hero.instance = new Hero();
                }
            }
        }
        return Hero.instance;
    }

    public void moveIfPossible(double d, Level Level){
        boolean movePossible=true;
        double dx = 0, dy = 0;

        if(this.isWalking){
            switch(this.orientation){
                case LEFT  : dx = -d; dy = 0;  break;
                case RIGHT : dx =  d; dy = 0;  break;
                case UP    : dx =  0; dy = -d; break;
                case DOWN  : dx =  0; dy =  d; break;
            }
        }

        HitBox playerHitBox = this.getHitBox();

        HitBox testHitBox = new HitBox(playerHitBox.getX()+dx,playerHitBox.getY()+dy, playerHitBox.getWidth(), playerHitBox.getHeight());

        for (Things things : Level.getRenderList()){
            if (things instanceof SolidThings){
                if(((SolidThings) things).getHitBox().intersect(testHitBox)){
                    movePossible=false;
                    break;
                }
            }
        }

        if (movePossible){
            this.x=x+dx;
            this.y=y+dy;
        }
        playerHitBox.setPosition(x, y);
    }

    /**
     * 
     * @param x hero Y destination
     * @param y hero Y destination
     * @param Level
     * @return true if teleport to [X, Y] of Level is possible, false if not
     */
    public boolean isTeleportPossible(double x, double y, Level Level){
        for (Things things : Level.getRenderList()){
            if (things instanceof SolidThings){
                if(((SolidThings) things).getHitBox().intersect(this.getHitBox())){
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isTeleportPossible(Level Level){
        return isTeleportPossible(this.x, this.y, Level);
    }

    public boolean teleportIfPossible(double x, double y, Level level){
        if(isTeleportPossible(x, y, level) || true){
            this.x=x;
            this.y=y;
            return true;
        }

        return false;
    }

    @Override
    public void draw(Graphics g){
        int attitude=0;
        switch(orientation){
            case UP :
                attitude=2;
                break;
            case DOWN:
                attitude=0;
                break;
            case LEFT :
                attitude=1;
                break;
            case RIGHT:
                attitude = 3;
                break;
        }
        int index = (int) ((System.currentTimeMillis()/125)%10);
        index=isWalking?index:0;
        g.drawImage(image,(int)x,(int)y,(int)x+48,(int) y+52,index*96,100*attitude,(index+1)*96,100*(attitude+1),null,null);
    }
}