package com.mashibing.decorator;

import com.mashibing.GameObject;

import java.awt.*;

/**
 * @create: 2020-04-14 15:57
 **/
public abstract class GODecorator extends GameObject {

    protected GameObject go;

    public GODecorator(GameObject go) {
        this.go = go;
    }

    @Override
    public void paint(Graphics g){
        this.point = go.getPoint();
        this.width = go.getWidth();
        this.height = go.getHeight();
        go.paint(g);
    };
}
