package com.mashibing.decorator;

import com.mashibing.GameObject;

import java.awt.*;

/**
 * @create: 2020-04-14 16:01
 **/
public class TailDecorator extends GODecorator{

    public TailDecorator(GameObject go) {
        super(go);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Color c = g.getColor();
        g.setColor(Color.white);
        g.drawRect(go.getPoint().x-go.getWidth(), go.getPoint().y-go.getHeight(), go.getWidth(), go.getHeight());
        g.setColor(c);
    }
}