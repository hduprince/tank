package com.mashibing.cor;

import com.mashibing.Bullet;
import com.mashibing.GameObject;
import com.mashibing.Tank;

/**
 * @create: 2020-04-10 17:32
 **/
public class TankBulletCollider implements Collider {

    @Override
    public void collide(GameObject o1, GameObject o2) {
        if ( o1 instanceof Tank && o2 instanceof Bullet ) {
            Tank tank = (Tank) o1;
            Bullet bullet = (Bullet) o2;
            if ( tank.group == bullet.group ) return;

            if ( tank.getRectangle().intersects(bullet.getRectangle()) ) {
                tank.die();
                bullet.die();
            } else if ( o1 instanceof Bullet && o2 instanceof Tank ) {
                collide(o2, o1);
            }
        }
    }
}
