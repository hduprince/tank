package com.mashibing;

import com.mashibing.facade.GameModel;

import java.awt.*;

/**
 * @create: 2020-04-03 13:34
 **/
public class Explode extends GameObject{

    private int step = 0;


    public Explode(Point center, GameModel gameModel) {
        width = ResourceMgr.explodeImg[0].getWidth();
        height = ResourceMgr.explodeImg[0].getHeight();
        this.gameModel = gameModel;
        this.point = new Point(center.x-width/2, center.y-height/2);
        new Thread(()->new Audio("audio/explode.wav").play()).start();
    }

    public void paint(Graphics g) {
        g.drawImage(ResourceMgr.explodeImg[step++], point.x, point.y, null);

        if(step >= ResourceMgr.explodeImg.length)
            gameModel.getObjects().remove(this);
    }

}
