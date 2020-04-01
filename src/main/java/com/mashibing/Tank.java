package com.mashibing;


import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * @create: 2020-03-20 05:25
 **/
public class Tank implements Runnable, Movable {

    private Point point;
    private Dir dir;
    private TankFrame tankFrame;

    private volatile boolean moving = false;
    private static final int SPEED = 10;

    private static final int WIDTH = 50;
    private static final int HEIGHT = 50;


    public Tank(Point point, Dir dir, TankFrame tankFrame) {
        this.point = point;
        this.dir = dir;
        this.tankFrame = tankFrame;
    }

    public void paint(Graphics g) {
        g.fillRect(point.getX(), point.getY(), WIDTH, HEIGHT);
    }

    public void run() {
        while (true) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if ( moving ) {
                move(dir, point, SPEED);
            }
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


    public void fire() {
        int x = 0, y = 0;
        //计算子弹位置
        switch (dir) {
            case UP:
                x = this.point.getX() + WIDTH / 2 - Bullet.WIGHT / 2;
                y = this.point.getY();
                break;
            case DOWN:
                x = this.point.getX() + WIDTH / 2 - Bullet.WIGHT / 2;
                y = this.point.getY() + HEIGHT;
                break;
            case LEFT:
                x = this.point.getX();
                y = this.point.getY() + HEIGHT / 2 - Bullet.HEIGHT / 2;
                break;
            case RIGHT:
                x = this.point.getX() + WIDTH;
                y = this.point.getY() + HEIGHT / 2 - Bullet.HEIGHT / 2;
                break;
        }
        Bullet bullet = new Bullet(new Point(x, y), this.dir, this.tankFrame);
        this.tankFrame.getBullets().add(bullet);
    }

}
