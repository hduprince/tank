package com.mashibing.decorator;

import com.mashibing.GameObject;

import java.awt.*;

/**
 * @create: 2020-04-14 15:59
 **/
public class RectDecorator extends GODecorator{


    public RectDecorator(GameObject go) {
        super(go);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Color c = g.getColor();
        g.setColor(Color.white);
        g.drawRect(go.getPoint().x, go.getPoint().y, go.getWidth(), go.getHeight());
        g.setColor(c);
    }
}
