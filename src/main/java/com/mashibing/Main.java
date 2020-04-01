package com.mashibing;


/**
 * @create: 2020-03-20 04:01
 **/
public class Main {

    public static void main(String[] args) throws InterruptedException {
        TankFrame frame = new TankFrame();

        while (true) {
            Thread.sleep(50);
            frame.repaint();
        }
    }
}
