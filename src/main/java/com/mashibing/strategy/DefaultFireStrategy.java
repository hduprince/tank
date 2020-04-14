package com.mashibing.strategy;

import com.mashibing.Audio;
import com.mashibing.Bullet;
import com.mashibing.Group;
import com.mashibing.Tank;
import com.mashibing.decorator.RectDecorator;
import com.mashibing.decorator.TailDecorator;
import com.mashibing.facade.GameModel;

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
        //装饰器模式
//        GameModel.getInstance().getObjects().add(
//                new TailDecorator(new RectDecorator(new Bullet(new Point(bX, bY), tank.dir, tank.group)))
//        );
        new Bullet(new Point(bX, bY), tank.dir, tank.group);

        if ( tank.group == Group.GOOD ) new Thread(() -> new Audio("audio/tank_fire.wav").play()).start();
    }
}
