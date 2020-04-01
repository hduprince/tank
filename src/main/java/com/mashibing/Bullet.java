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

    public static final int WIGHT = 10;
    public static final int HEIGHT = 10;

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

        Color c = g.getColor();
        g.setColor(Color.red);
        g.fillOval(point.getX(), point.getY(), WIGHT, HEIGHT);
        g.setColor(c);
        move(dir, point, SPEED);
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }
}


