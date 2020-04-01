package com.mashibing;

import java.util.LinkedList;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;


/**
 * @create: 2020-03-20 04:28
 **/
public class TankFrame extends Frame {

    private Tank mainTank = new Tank(new Point(200, 200), Dir.LEFT, this);

    private java.util.List<Bullet> bullets = new LinkedList<Bullet>();

    private final static int GAME_WIDTH = 800;
    private final static int GAME_HEIGHT = 600;


    public TankFrame() {
        setSize(GAME_WIDTH, GAME_HEIGHT);
        setResizable(false);
        setVisible(true);
        setTitle("tank war");
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        addKeyListener(new MyKeyListener());
        new Thread(mainTank).start();
    }


    @Override
    public void paint(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.blue);
        g.drawString("子弹总数:"+bullets.size(), 30, 50);
        g.setColor(c);
        mainTank.paint(g);
        bullets.stream().forEach(bullet -> bullet.paint(g));
    }


    class MyKeyListener extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            int keyCode = e.getKeyCode();
            switch (keyCode) {
                case KeyEvent.VK_LEFT:
                case KeyEvent.VK_RIGHT:
                case KeyEvent.VK_UP:
                case KeyEvent.VK_DOWN:
                    mainTank.changeDir(keyCode, true);
                    break;
            }

        }


        @Override
        public void keyReleased(KeyEvent e) {
            int keyCode = e.getKeyCode();
            switch (keyCode) {
                case KeyEvent.VK_LEFT:
                case KeyEvent.VK_RIGHT:
                case KeyEvent.VK_UP:
                case KeyEvent.VK_DOWN:
                    mainTank.changeDir(keyCode, false);
                    break;
                case KeyEvent.VK_CONTROL:
                    mainTank.fire();
                    break;
            }
        }

    }


    public List<Bullet> getBullets() {
        return bullets;
    }

    public void setBullets(List<Bullet> bullets) {
        this.bullets = bullets;
    }

    //解决屏幕闪烁问题
//    Image offScreenImage = null;
//    @Override
//    public void update(Graphics g) {
//        if(offScreenImage == null){
//            offScreenImage = this.createImage(GAME_WIDTH, GAME_HEIGHT);
//        }
//        Graphics gOffScreen = offScreenImage.getGraphics();
//        Color c = gOffScreen.getColor();
//        gOffScreen.setColor(Color.black);
//        gOffScreen.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
//        gOffScreen.setColor(c);
//        paint(gOffScreen);
//        g.drawImage(offScreenImage, GAME_WIDTH, GAME_HEIGHT, null);
//
//    }
}




