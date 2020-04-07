package com.mashibing.strategy;

import com.mashibing.*;

import java.awt.*;

/**
 * @create: 2020-04-07 16:06
 **/
public class DefaultFireStrategy implements FireStrategy {
    @Override
    public void fire(Tank tank) {
        int x = 0, y = 0;
        //计算子弹位置
        switch (tank.dir) {
            case UP:
                x = tank.point.x + Tank.WIDTH / 2 - Bullet.WIDTH / 2;
                y = tank.point.y;
                break;
            case DOWN:
                x = tank.point.x + Tank.WIDTH / 2 - Bullet.WIDTH / 2;
                y = tank.point.y + Tank.HEIGHT;
                break;
            case LEFT:
                x = tank.point.x;
                y = tank.point.y + Tank.HEIGHT / 2 - Bullet.HEIGHT / 2;
                break;
            case RIGHT:
                x = tank.point.x + Tank.WIDTH;
                y = tank.point.y + Tank.HEIGHT / 2 - Bullet.HEIGHT / 2;
                break;
        }
        Bullet bullet = new Bullet(new Point(x, y), tank.dir, tank.group);
        TankFrame.bullets.add(bullet);

        if(tank.group == Group.GOOD) new Thread(()->new Audio("audio/tank_fire.wav").play()).start();
    }
}
