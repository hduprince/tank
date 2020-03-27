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

    Tank mainTank = new Tank(200, 200, Dir.LEFT);

    public MyFrame() {
        setSize(800, 600);
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
    }

    @Override
    public void paint(Graphics g) {
        mainTank.paint(g);
    }


    class MyKeyListener extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            int keyCode = e.getKeyCode();
            System.out.println("press key:" +keyCode);
            mainTank.move(keyCode, true);
        }


        @Override
        public void keyReleased(KeyEvent e) {
            int keyCode = e.getKeyCode();
            System.out.println("release key:" +keyCode);
            mainTank.move(keyCode, false);
        }

    }

}
