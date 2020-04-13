package com.mashibing;

import com.mashibing.facade.GameModel;

import java.awt.*;

/**
 * @create: 2020-04-10 15:05
 **/
public abstract class GameObject {

    protected Point oldPoint;
    protected Point point;
    protected Rectangle rectangle;
    protected int width;
    protected int height;
    protected GameModel gameModel;

    public abstract void paint(Graphics g);

    public void resize() {
        rectangle.setSize(width, height);
        rectangle.setLocation(point);
    }


    public Point getPoint() {
        return point;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public GameModel getGameModel() {
        return gameModel;
    }

    public void setbacks(){
        point.x = oldPoint.x;
        point.y = oldPoint.y;
        resize();
    }


}
