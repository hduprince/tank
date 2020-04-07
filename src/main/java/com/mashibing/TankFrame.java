package com.mashibing;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;


/**
 * @create: 2020-03-20 04:28
 **/
public class TankFrame extends Frame {


    public final static int GAME_WIDTH = ConfigMgr.getInt("gameWidth");
    public final static int GAME_HEIGHT = ConfigMgr.getInt("gameHeight");
    private static Tank mainTank = new Tank(new Point(200, 400), Dir.LEFT, Group.GOOD);
    public static TankFrame INSTANCE = new TankFrame();

    public static List<Bullet> bullets = Collections.synchronizedList(new LinkedList<>());
    public static List<Tank> enemies = Collections.synchronizedList(new LinkedList<>());
    public static List<Explode> explodes = Collections.synchronizedList(new LinkedList<>());

    static {
        INSTANCE.setSize(GAME_WIDTH, GAME_HEIGHT);
        INSTANCE.setResizable(false);
        INSTANCE.setVisible(true);
        INSTANCE.setTitle("tank war");
        INSTANCE.setBackground(Color.black);
        INSTANCE.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        INSTANCE.addKeyListener(new MyKeyListener());
        new Thread(mainTank).start();

        for (int i = 0; i < ConfigMgr.getInt("enemyCount"); i++) {
            Tank tank = new Tank(new Point(200 + 50 * i, 200), Dir.DOWN, Group.BAD);
            enemies.add(tank);
            new Thread(tank).start();
        }
    }

    private TankFrame() { }

    @Override
    public void paint(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.yellow);
        g.drawString("子弹总数:" + bullets.size(), 30, 50);
        g.drawString("敌军总数:" + enemies.size(), 30, 70);
        g.drawString("爆炸总数:" + explodes.size(), 30, 90);
        g.setColor(c);
        mainTank.paint(g);


        for (int i = 0; i < bullets.size(); i++) {
            bullets.get(i).paint(g);
        }

        for (int i = 0; i < enemies.size(); i++) {
            enemies.get(i).paint(g);
        }

        for (int i = 0; i < explodes.size(); i++) {
            explodes.get(i).paint(g);
        }

        //碰撞检查
        for (int j = 0; j < enemies.size(); j++) {
            for (int i = 0; i < bullets.size(); i++) {
                enemies.get(j).colliding(bullets.get(i));
            }
        }
    }


    static class MyKeyListener extends KeyAdapter {
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
                case KeyEvent.VK_B:
                    mainTank.fire();
                    break;
            }
        }

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




