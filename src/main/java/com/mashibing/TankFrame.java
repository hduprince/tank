package com.mashibing;

import com.mashibing.facade.GameModel;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @create: 2020-03-20 04:28
 **/
public class TankFrame extends Frame {


    public final static int GAME_WIDTH = ConfigMgr.getInt("gameWidth");
    public final static int GAME_HEIGHT = ConfigMgr.getInt("gameHeight");

    private static TankFrame INSTANCE = new TankFrame();


    public static TankFrame getInstance(){
        return INSTANCE;
    }


    GameModel gameModel = new GameModel();

    private TankFrame() {
        setSize(GAME_WIDTH, GAME_HEIGHT);
        setResizable(false);
        setVisible(true);
        setTitle("tank war");
        setBackground(Color.black);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        addKeyListener(new MyKeyListener());
    }

    @Override
    public void paint(Graphics g) {
        gameModel.paint(g);
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
                    gameModel.getMainTank().changeDir(keyCode, true);
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
                    gameModel.getMainTank().changeDir(keyCode, false);
                    break;
                case KeyEvent.VK_B:
                    gameModel.getMainTank().fire();
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




