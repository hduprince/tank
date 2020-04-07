package com.mashibing;

import java.awt.*;

/**
 * @create: 2020-04-03 13:34
 **/
public class Explode {

    private Point point;
    public static final int WIDTH = ResourceMgr.explodeImg[0].getWidth();
    public static final int HEIGHT =  ResourceMgr.explodeImg[0].getHeight();

    private int step = 0;


    public Explode(Point point) {
        this.point = point;
        new Thread(()->new Audio("audio/explode.wav").play()).start();
    }

    public void paint(Graphics g) {
        g.drawImage(ResourceMgr.explodeImg[step++], point.x, point.y, null);

        if(step >= ResourceMgr.explodeImg.length)
            TankFrame.INSTANCE.explodes.remove(this);
    }

}
