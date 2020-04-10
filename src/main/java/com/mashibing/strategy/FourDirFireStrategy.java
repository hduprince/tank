package com.mashibing.strategy;

import com.mashibing.*;

import java.awt.*;

/**
 * @create: 2020-04-07 16:10
 **/
public class FourDirFireStrategy implements FireStrategy {

    @Override
    public void fire(Tank tank) {
        for (Dir dir : Dir.values()) {
            int bX = tank.getPoint().x + tank.getWidth() / 2;
            int bY = tank.getPoint().y + tank.getHeight() / 2;
            Bullet bullet = new Bullet(new Point(bX, bY), dir, tank.group, tank.getGameModel());
            tank.getGameModel().getObjects().add(bullet);
        }

        if ( tank.group == Group.GOOD ) new Thread(() -> new Audio("audio/tank_fire.wav").play()).start();
    }
}
