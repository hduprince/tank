package com.mashibing;

import java.awt.*;

/**
 * @create: 2020-03-27 11:28
 **/
public class Bullet implements Movable {

    private Point point;
    private Dir dir;
    private boolean living = true;
    public Group group;


    private Rectangle rectangle;

    private static final int SPEED = ConfigMgr.getInt("bulletSpeed");

    public static final int WIDTH = ResourceMgr.bulletD.getWidth();
    public static final int HEIGHT = ResourceMgr.bulletD.getHeight();

    public Bullet(Point point, Dir dir, Group group) {
        this.point = point;
        this.dir = dir;
        this.group = group;
        rectangle = new Rectangle(point.x, point.y, WIDTH, HEIGHT);
    }

    public void paint(Graphics g) {

        move(dir, point, SPEED);
        if(point.getX() <= 0 || point.getX()+WIDTH >= TankFrame.GAME_WIDTH
        || point.getY() <= 80 || point.getY()+HEIGHT >= TankFrame.GAME_HEIGHT){
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

    public boolean getLiving() {
        return living;
    }

    public void setLiving(boolean living) {
        this.living = living;
    }


    public Rectangle getRectangle() {
        return rectangle;
    }

    public void setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
    }

    public void die() {
        this.living = false;
        TankFrame.bullets.remove(this);
    }

    @Override
    public void resize() {
        rectangle.setSize(WIDTH, HEIGHT);
        rectangle.setLocation(point);
    }
}


