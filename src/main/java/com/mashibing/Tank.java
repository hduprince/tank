package com.mashibing;


import com.mashibing.facade.GameModel;
import com.mashibing.strategy.FireStrategy;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Random;

/**
 * @create: 2020-03-20 05:25
 **/
public class Tank extends GameObject implements Runnable, Movable {

    public Dir dir;
    public Group group;
    private volatile boolean living = true;
    private FireStrategy fireStrategy;
    private int speed;
    private volatile boolean moving = true;

    private Random random = new Random();


    public Tank(Point point, Dir dir, Group group) {
        this.point = point;
        this.oldPoint = new Point();
        this.dir = dir;
        this.group = group;
        this.speed = ConfigMgr.getInt("tankSpeed");
        this.width = ResourceMgr.goodTank1U.getWidth();
        this.height = ResourceMgr.goodTank1U.getHeight();
        rectangle = new Rectangle(point.x, point.y, width, height);
        if(group == Group.GOOD){
            try {
                fireStrategy = (FireStrategy) Class.forName(ConfigMgr.getString("goodFS")).newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                fireStrategy = (FireStrategy) Class.forName(ConfigMgr.getString("badFS")).newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }
            GameModel.getInstance().getObjects().add(this);
        }

    }

    public void paint(Graphics g) {

        if ( living ) {
            switch (dir) {
                case LEFT:
                    g.drawImage(group == Group.GOOD ? ResourceMgr.goodTank1L : ResourceMgr.badTank1L, this.point.x, this.point.y, null);
                    break;
                case UP:
                    g.drawImage(group == Group.GOOD ? ResourceMgr.goodTank1U : ResourceMgr.badTank1U, this.point.x, this.point.y, null);
                    break;
                case DOWN:
                    g.drawImage(group == Group.GOOD ? ResourceMgr.goodTank1D : ResourceMgr.badTank1D,  this.point.x, this.point.y, null);
                    break;
                case RIGHT:
                    g.drawImage(group == Group.GOOD ? ResourceMgr.goodTank1R : ResourceMgr.badTank1R,  this.point.x, this.point.y, null);
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
                        || this.point.x + width >= TankFrame.GAME_WIDTH && dir == Dir.RIGHT
                        || this.point.y <= 80 && dir == Dir.UP
                        || this.point.y + height >= TankFrame.GAME_HEIGHT && dir == Dir.DOWN)
                ) {
                    oldPoint.x = point.x;
                    oldPoint.y = point.y;
                    move(dir, point, speed);
                    resize();
                }
            }
            if ( group == Group.BAD ) {
                if ( random.nextInt(10) > 8 ) {
                    fire();
                }
                if ( random.nextInt(10) > 7 ) {
//                    dir = Arrays.stream(Dir.values()).skip(random.nextInt(4)).findFirst().orElse(Dir.UP);
                    dir = Dir.values()[random.nextInt(4)];
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
            new Thread(()->new Audio("audio/tank_move.wav").play()).start();
        } else {
            moving = false;
        }
    }

    public void fire() {
        fireStrategy.fire(this);
    }


    public void die() {
        this.living = false;
        int eX = point.x + width/2;
        int eY = point.y + width/2;
        GameModel.getInstance().getObjects().add(new Explode(new Point(eX, eY)));
        GameModel.getInstance().getObjects().remove(this);
    }

}
