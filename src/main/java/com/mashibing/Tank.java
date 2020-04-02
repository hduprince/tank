package com.mashibing;


import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Arrays;
import java.util.Random;

/**
 * @create: 2020-03-20 05:25
 **/
public class Tank implements Runnable, Movable {

    private Point point;
    private Dir dir;
    private TankFrame tankFrame;
    private Group group;
    private volatile boolean living = true;
    private Rectangle rectangle;

    private volatile boolean moving = true;
    private static final int SPEED = 10;
    private static final int WIDTH = ResourceMgr.tankD.getWidth();
    private static final int HEIGHT = ResourceMgr.tankD.getHeight();

    private Random random = new Random();


    public Tank(Point point, Dir dir, Group group, TankFrame tankFrame) {
        this.point = point;
        this.dir = dir;
        this.tankFrame = tankFrame;
        this.group = group;
        rectangle = new Rectangle(point.x, point.y, WIDTH, HEIGHT);
    }

    public void paint(Graphics g) {

        if ( living ) {
            switch (dir) {
                case LEFT:
                    g.drawImage(ResourceMgr.tankL, this.point.x, this.point.y, null);
                    break;
                case UP:
                    g.drawImage(ResourceMgr.tankU, this.point.x, this.point.y, null);
                    break;
                case DOWN:
                    g.drawImage(ResourceMgr.tankD, this.point.x, this.point.y, null);
                    break;
                case RIGHT:
                    g.drawImage(ResourceMgr.tankR, this.point.x, this.point.y, null);
                    break;
            }
        }

    }

    public void run() {
        while (living) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


            if ( moving ) {
                if ( !(this.point.x <= 0 && dir == Dir.LEFT
                        || this.point.x + WIDTH >= TankFrame.GAME_WIDTH && dir == Dir.RIGHT
                        || this.point.y <= 80 && dir == Dir.UP
                        || this.point.y + HEIGHT >= TankFrame.GAME_HEIGHT && dir == Dir.DOWN)
                ) {
                    move(dir, point, SPEED);
                }
            }
            if ( group == Group.BAD ) {
                if ( random.nextInt(10) > 8 ) {
                    fire();
                }
                if ( random.nextInt(10) > 5 ) {
                    dir = Arrays.stream(Dir.values()).skip(random.nextInt(4)).findFirst().orElse(Dir.UP);
                }
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
                x = this.point.x + WIDTH / 2 - Bullet.WIDTH / 2;
                y = this.point.y;
                break;
            case DOWN:
                x = this.point.x + WIDTH / 2 - Bullet.WIDTH / 2;
                y = this.point.y + HEIGHT;
                break;
            case LEFT:
                x = this.point.x;
                y = this.point.y + HEIGHT / 2 - Bullet.HEIGHT / 2;
                break;
            case RIGHT:
                x = this.point.x + WIDTH;
                y = this.point.y + HEIGHT / 2 - Bullet.HEIGHT / 2;
                break;
        }
        Bullet bullet = new Bullet(new Point(x, y), this.dir, this.group, this.tankFrame);
        this.tankFrame.getBullets().add(bullet);
        System.out.println("fire tankFrame.getBullets().size=" + tankFrame.getBullets().size() );

    }

    @Override
    public void resize() {
        rectangle.setSize(WIDTH, HEIGHT);
        rectangle.setLocation(point);
    }

    public void colliding(Bullet bullet) {
        if ( this.group == bullet.group ) return;

        if ( rectangle.intersects(bullet.getRectangle()) ) {
            this.die();
            bullet.die();
        }
    }

    private void die() {
        this.living = false;
        tankFrame.getEnemies().remove(this);
    }


    public Rectangle getRectangle() {
        return rectangle;
    }

    public void setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
    }
}
