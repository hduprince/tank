package com.mashibing.cor;

import com.mashibing.Bullet;
import com.mashibing.GameObject;
import com.mashibing.Tank;
import com.mashibing.Wall;

/**
 * @create: 2020-04-10 17:32
 **/
public class BulletWallCollider implements Collider {

    @Override
    public boolean collide(GameObject o1, GameObject o2) {
        if ( o1 instanceof Bullet && o2 instanceof Wall ) {
            Bullet bullet = (Bullet) o1;
            Wall wall = (Wall) o2;
            if ( bullet.getRectangle().intersects(wall.getRectangle()) ) {
                bullet.die();
            }
        } else if ( o1 instanceof Wall && o2 instanceof Bullet ) {
            return collide(o2, o1);
        }
        return true;
    }
}
