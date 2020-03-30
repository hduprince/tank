package com.mashibing;

/**
 * @program: tank
 * @author: hduprince
 * @create: 2020-03-30 15:45
 **/
public interface Moving {

    default void move(Dir dir, Point point, Integer speed){
        switch (dir) {
            case LEFT:
                point.setX(point.getX() - speed);
                break;
            case RIGHT:
                point.setX(point.getX() + speed);
                break;
            case UP:
                point.setY(point.getY() - speed);
                break;
            case DOWN:
                point.setY(point.getY() + speed);
                break;
        }
    }
}
