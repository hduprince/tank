package com.mashibing;

import com.mashibing.facade.GameModel;

import java.awt.*;

/**
 * @create: 2020-03-27 11:28
 **/
public class Bullet extends GameObject implements Movable {


    private Dir dir;
    private boolean living = true;
    public Group group;
    private int speed;

    public Bullet(Point center, Dir dir, Group group, GameModel gameModel) {
        this.dir = dir;
        this.group = group;
        this.gameModel = gameModel;
        this.width = ResourceMgr.bulletD.getWidth();
        this.height = ResourceMgr.bulletD.getHeight();
        this.speed = ConfigMgr.getInt("bulletSpeed");
        this.point = new Point(center.x-width/2, center.y-height/2);
        rectangle = new Rectangle(point.x, point.y, width, height);
    }

    public void paint(Graphics g) {

        move(dir, point, speed);
        resize();
        if(point.getX() <= 0 || point.getX()+width >= TankFrame.GAME_WIDTH
        || point.getY() <= 80 || point.getY()+height >= TankFrame.GAME_HEIGHT){
            this.die();
        }
        switch (dir) {
            case LEFT:
                g.drawImage(ResourceMgr.bulletL, this.point.x, this.point.y, null);
                break;
            case UP:
                g.drawImage(ResourceMgr.bulletU, this.point.x, this.point.y, null);
                break;
            case DOWN:
                g.drawImage(ResourceMgr.bulletD, this.point.x, this.point.y, null);
                break;
            case RIGHT:
                g.drawImage(ResourceMgr.bulletR, this.point.x, this.point.y, null);
                break;
        }
    }

    public void die() {
        this.living = false;
        gameModel.getObjects().remove(this);
    }

}


