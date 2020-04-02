package com.mashibing;

import java.awt.*;

/**
 * @program: tank
 * @author: hduprince
 * @create: 2020-03-30 15:45
 **/
public interface Movable {

    default void move(Dir dir, Point point, Integer speed){
        switch (dir) {
            case LEFT:
                point.x  = point.x - speed;
                break;
            case RIGHT:
                point.x  = point.x + speed;
                break;
            case UP:
                point.y  = point.y - speed;
                break;
            case DOWN:
                point.y  = point.y + speed;
                break;
        }
        resize();

    }

    void resize();
}
