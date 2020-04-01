package com.mashibing;

import java.awt.*;

/**
 * @create: 2020-03-27 11:28
 **/
public class Bullet implements Movable {

    private Point point;
    private Dir dir;
    private boolean isAlive;
    private TankFrame tankFrame;

    private static final int SPEED = 10;

    public static final int WIGHT = ResourceMgr.bulletD.getWidth();
    public static final int HEIGHT = ResourceMgr.bulletD.getHeight();

    public Bullet(Point point, Dir dir, TankFrame tankFrame) {
        this.point = point;
        this.dir = dir;
        this.tankFrame = tankFrame;
    }

    public void paint(Graphics g) {

        if(point.getX() <= this.tankFrame.getX() || point.getX() >= this.tankFrame.getX()+this.tankFrame.getWidth()
        || point.getY() <= this.tankFrame.getY() || point.getY() >= this.tankFrame.getY()+this.tankFrame.getHeight()){
            tankFrame.getBullets().remove(this);
            return;
        }

        switch (dir) {
            case LEFT:
                g.drawImage(ResourceMgr.bulletL, this.point.getX(), this.point.getY(), null);
                break;
            case UP:
                g.drawImage(ResourceMgr.bulletU, this.point.getX(), this.point.getY(), null);
                break;
            case DOWN:
                g.drawImage(ResourceMgr.bulletD, this.point.getX(), this.point.getY(), null);
                break;
            case RIGHT:
                g.drawImage(ResourceMgr.bulletR, this.point.getX(), this.point.getY(), null);
                break;
        }



        move(dir, point, SPEED);
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }
}


