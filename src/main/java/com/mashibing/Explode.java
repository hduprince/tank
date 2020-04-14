package com.mashibing;

import com.mashibing.facade.GameModel;

import java.awt.*;

/**
 * @create: 2020-04-03 13:34
 **/
public class Explode extends GameObject{

    private int step = 0;


    public Explode(Point center) {
        width = ResourceMgr.explodeImg[0].getWidth();
        height = ResourceMgr.explodeImg[0].getHeight();
        this.point = new Point(center.x-width/2, center.y-height/2);
        GameModel.getInstance().getObjects().add(this);
        new Thread(()->new Audio("audio/explode.wav").play()).start();
    }

    public void paint(Graphics g) {
        if(step >= ResourceMgr.explodeImg.length) {
            GameModel.getInstance().getObjects().remove(this);
            return;
        }

        g.drawImage(ResourceMgr.explodeImg[step++], point.x, point.y, null);
    }

}
