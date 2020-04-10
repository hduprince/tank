package com.mashibing.facade;

import com.mashibing.*;

import java.awt.*;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @create: 2020-04-10 11:16
 **/
public class GameModel {



    private Tank mainTank = new Tank(new Point(200, 400), Dir.LEFT, Group.GOOD, this);
    public static java.util.List<Bullet> bullets = Collections.synchronizedList(new LinkedList<>());
    public static java.util.List<Tank> enemies = Collections.synchronizedList(new LinkedList<>());
    public static List<Explode> explodes = Collections.synchronizedList(new LinkedList<>());


    public GameModel() {
        new Thread(mainTank).start();
        for (int i = 0; i < ConfigMgr.getInt("enemyCount"); i++) {
            Tank tank = new Tank(new Point(200 + 50 * i, 200), Dir.DOWN, Group.BAD, this);
            enemies.add(tank);
            new Thread(tank).start();
        }
    }

    public void paint(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.yellow);
        g.drawString("子弹总数:" + bullets.size(), 30, 50);
        g.drawString("敌军总数:" + enemies.size(), 30, 70);
        g.drawString("爆炸总数:" + explodes.size(), 30, 90);
        g.setColor(c);
        mainTank.paint(g);


        for (int i = 0; i < bullets.size(); i++) {
            bullets.get(i).paint(g);
        }

        for (int i = 0; i < enemies.size(); i++) {
            enemies.get(i).paint(g);
        }

        for (int i = 0; i < explodes.size(); i++) {
            explodes.get(i).paint(g);
        }

        //碰撞检查
        for (int j = 0; j < enemies.size(); j++) {
            for (int i = 0; i < bullets.size() && j < enemies.size(); i++) {
                enemies.get(j).colliding(bullets.get(i));
            }
        }
    }

    public Tank getMainTank() {
        return mainTank;
    }

}
