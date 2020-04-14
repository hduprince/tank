package com.mashibing;

import com.mashibing.facade.GameModel;

import java.awt.*;

/**
 * @create: 2020-04-14 15:00
 **/
public class Wall extends GameObject {


    public Wall(Point point, int width, int heigth) {
        this.point = point;
        this.width = width;
        this.height = heigth;
        rectangle = new Rectangle(point.x, point.y, width, height);
        GameModel.getInstance().getObjects().add(this);
    }

    @Override
    public void paint(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.yellow);
        g.fillRect(point.x, point.y, width, height);
        g.setColor(c);
    }
}
