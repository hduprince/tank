package com.mashibing;


import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * @create: 2020-03-20 05:25
 **/
public class Tank {

    private int x;
    private int y;
    private Dir dir;

    private boolean moving = false;

    private static final int SPEED = 10;


    public Tank(int x, int y, Dir dir) {
        this.x = x;
        this.y = y;
        this.dir = dir;
    }

    public void paint(Graphics g) {
        g.fillRect(x, y, 50, 50);
    }


    public void move(int keyCode, boolean press) {
        System.out.println("move" + x);
        if(press){
            moving = true;
            switch (keyCode) {
                case KeyEvent.VK_LEFT:
                    dir = Dir.LEFT;
                    x -= SPEED;
                    break;
                case KeyEvent.VK_RIGHT:
                    dir = Dir.RIGHT;
                    x += SPEED;
                    break;
                case KeyEvent.VK_UP:
                    dir = Dir.UP;
                    y -= SPEED;
                    break;
                case KeyEvent.VK_DOWN:
                    dir = Dir.DOWN;
                    y += SPEED;
                    break;
            }
        } else {
            moving = false;
        }
    }
}
