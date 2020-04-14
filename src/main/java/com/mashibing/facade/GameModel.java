package com.mashibing.facade;

import com.mashibing.*;
import com.mashibing.cor.ColliderChain;

import java.awt.*;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * @create: 2020-04-10 11:16
 **/
public class GameModel {


    private static final GameModel INSTANCE = new GameModel();

    static {
        INSTANCE.init();
    }


    private Tank mainTank;

    List<GameObject> objects = Collections.synchronizedList(new LinkedList<>());

    ColliderChain chain = new ColliderChain();


    private GameModel() {}


    private void init(){
        mainTank = new Tank(new Point(200, 400), Dir.LEFT, Group.GOOD);
        new Thread(mainTank).start();
        for (int i = 0; i < ConfigMgr.getInt("enemyCount"); i++) {
            Tank tank = new Tank(new Point(200 + 50 * i, 200), Dir.DOWN, Group.BAD);
            new Thread(tank).start();
        }

        new Wall(new Point(100, 100), 200, 30);
        new Wall(new Point(300, 400), 50, 100);
    }

    public void paint(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.yellow);
        g.drawString("对象数量 :"+objects.size(), 30, 50);
        g.setColor(c);
        mainTank.paint(g);


        for (int i = 0; i < objects.size(); i++) {
            objects.get(i).paint(g);
        }

        //碰撞检查
        for (int i = 0; i < objects.size(); i++) {
            for (int j = i + 1; i < objects.size() && j < objects.size(); j++) {
                System.out.println(new Date() + "--i =" + i + ",j=" + j);
                chain.collide(objects.get(i), objects.get(j));
            }
        }
    }

    public Tank getMainTank() {
        return mainTank;
    }

    public List<GameObject> getObjects() {
        return objects;
    }

    public static GameModel getInstance(){
        return INSTANCE;
    }
}
