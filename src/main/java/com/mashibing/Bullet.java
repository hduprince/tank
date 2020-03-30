package com.mashibing;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * @create: 2020-03-27 11:28
 **/
public class Bullet implements Moving{

    private Point point;
    private Dir dir;

    private static final int SPEED = 1;

    private static final int WIGHT = 10;
    private static final int HEIGHT = 10;

    public Bullet(Point point, Dir dir) {
        this.point = point;
        this.dir = dir;
    }

    public void paint(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.red);
        g.fillOval(point.getX(), point.getY(), WIGHT, HEIGHT);
        g.setColor(c);
        move(dir, point, SPEED);
    }


}


