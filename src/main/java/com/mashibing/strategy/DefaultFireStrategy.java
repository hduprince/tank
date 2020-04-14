package com.mashibing.strategy;

import com.mashibing.Audio;
import com.mashibing.Bullet;
import com.mashibing.Group;
import com.mashibing.Tank;

import java.awt.*;

/**
 * @create: 2020-04-07 16:06
 **/
public class DefaultFireStrategy implements FireStrategy {
    @Override
    public void fire(Tank tank) {
        //计算子弹位置   从中心位置打出
        int bX = tank.getPoint().x + tank.getWidth() / 2 ;
        int bY = tank.getPoint().y + tank.getHeight() / 2;
        new Bullet(new Point(bX, bY), tank.dir, tank.group);

        if ( tank.group == Group.GOOD ) new Thread(() -> new Audio("audio/tank_fire.wav").play()).start();
    }
}
