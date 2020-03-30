package com.mashibing;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @create: 2020-03-20 04:28
 **/
public class MyFrame extends Frame {

    Tank mainTank = new Tank(new Point(200,200), Dir.LEFT);
    Bullet bullet = new Bullet(new Point(200,200), Dir.LEFT);

    private final static int GAME_WIDTH = 800;
    private final static int GAME_HEIGHT = 600;


    public MyFrame() {
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
        mainTank.paint(g);
        bullet.paint(g);
    }


    class MyKeyListener extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            int keyCode = e.getKeyCode();
            mainTank.changeDir(keyCode, true);
        }


        @Override
        public void keyReleased(KeyEvent e) {
//            int keyCode = e.getKeyCode();
//            mainTank.move(keyCode, false);
        }

    }



    //解决屏幕闪烁问题
    Image offScreenImage = null;
    @Override
    public void update(Graphics g) {
        if(offScreenImage == null){
            offScreenImage = this.createImage(GAME_WIDTH, GAME_HEIGHT);
        }
        Graphics gOffScreen = offScreenImage.getGraphics();
        Color c = gOffScreen.getColor();
        gOffScreen.setColor(Color.black);
        gOffScreen.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
        gOffScreen.setColor(c);
        paint(gOffScreen);
        g.drawImage(offScreenImage, GAME_WIDTH, GAME_HEIGHT, null);

    }

}
