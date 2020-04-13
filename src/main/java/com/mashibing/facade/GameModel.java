package com.mashibing.facade;

import com.mashibing.*;
import com.mashibing.cor.Collider;
import com.mashibing.cor.TankBulletCollider;
import com.mashibing.cor.TankTankCollider;

import java.awt.*;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @create: 2020-04-10 11:16
 **/
public class GameModel {


    private Tank mainTank = new Tank(new Point(200, 400), Dir.LEFT, Group.GOOD, this);

    List<GameObject> objects = Collections.synchronizedList(new LinkedList<>());

    List<Collider> colliders = new LinkedList();

//    Collider collider = new TankBulletCollider();
//    Collider collider1 = new TankTankCollider();

    public GameModel() {
        new Thread(mainTank).start();
        for (int i = 0; i < ConfigMgr.getInt("enemyCount"); i++) {
            Tank tank = new Tank(new Point(200 + 50 * i, 200), Dir.DOWN, Group.BAD, this);
            objects.add(tank);
            new Thread(tank).start();
        }

        colliders.add(new TankBulletCollider());
        colliders.add(new TankTankCollider());
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
        for (int j = 0; j < objects.size(); j++) {
            for (int i = j + 1; i < objects.size() && j < objects.size(); i++) {
                for(Collider collider : colliders) {
                    if(!collider.collide(objects.get(i), objects.get(j))){
                        break;
                    }
                }
            }
        }
    }

    public Tank getMainTank() {
        return mainTank;
    }

    public List<GameObject> getObjects() {
        return objects;
    }

}
