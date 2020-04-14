package com.mashibing.cor;

import com.mashibing.GameObject;
import com.mashibing.Tank;
import com.mashibing.Wall;

/**
 * @create: 2020-04-10 17:32
 **/
public class TankWallCollider implements Collider {

    @Override
    public boolean collide(GameObject o1, GameObject o2) {
        if ( o1 instanceof Tank && o2 instanceof Wall ) {
            Tank tank = (Tank) o1;
            Wall wall = (Wall) o2;
            if ( tank.getRectangle().intersects(wall.getRectangle()) ) {
                tank.setbacks();
            }
        } else if ( o1 instanceof Wall && o2 instanceof Tank ) {
            return collide(o2, o1);
        }
        return true;
    }
}
