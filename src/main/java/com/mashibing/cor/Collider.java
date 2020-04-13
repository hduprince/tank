package com.mashibing.cor;

import com.mashibing.GameObject;

/**
 * @program: tank
 * @author: hduprince
 * @create: 2020-04-10 17:29
 **/
public interface Collider {
    boolean collide(GameObject o1, GameObject o2);
}
