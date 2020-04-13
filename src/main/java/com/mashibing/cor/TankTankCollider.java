package com.mashibing.cor;

import com.mashibing.GameObject;
import com.mashibing.Tank;

/**
 * @create: 2020-04-10 17:32
 **/
public class TankTankCollider implements Collider {

    @Override
    public boolean collide(GameObject o1, GameObject o2) {
        if ( o1 instanceof Tank && o2 instanceof Tank ) {
            Tank tank = (Tank) o1;
            Tank tank1 = (Tank) o2;

            if ( tank.getRectangle().intersects(tank1.getRectangle()) ) {
                tank.setbacks();
                tank1.setbacks();
            }
        }
        return true;
    }
}
