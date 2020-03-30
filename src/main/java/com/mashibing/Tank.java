package com.mashibing;


import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * @create: 2020-03-20 05:25
 **/
public class Tank implements Runnable, Moving{

    private Point point;
    private Dir dir;

    private boolean moving = false;

    private static final int SPEED = 10;


    public Tank(Point point, Dir dir) {
        this.point = point;
        this.dir = dir;
    }

    public void paint(Graphics g) {
        g.fillRect(point.getX(), point.getY(), 50, 50);
    }

    public void run() {
        while (true) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            move(dir, point, SPEED);
        }
    }


    public void changeDir(int keyCode, boolean press) {
        if ( press ) {
            moving = true;
            switch (keyCode) {
                case KeyEvent.VK_LEFT:
                    dir = Dir.LEFT;
                    break;
                case KeyEvent.VK_RIGHT:
                    dir = Dir.RIGHT;
                    break;
                case KeyEvent.VK_UP:
                    dir = Dir.UP;
                    break;
                case KeyEvent.VK_DOWN:
                    dir = Dir.DOWN;
                    break;
            }
        } else {
            moving = false;
        }
    }


}
