package com.mashibing;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @create: 2020-03-20 04:01
 **/
public class Main {

    public static void main(String[] args) throws InterruptedException {
        MyFrame frame = new MyFrame();

        while (true) {
            Thread.sleep(50);
            frame.repaint();
        }
    }
}
