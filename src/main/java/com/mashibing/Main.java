package com.mashibing;


/**
 * @create: 2020-03-20 04:01
 **/
public class Main {

    public static void main(String[] args) throws InterruptedException {
        while (true) {
            Thread.sleep(25);
            TankFrame.INSTANCE.repaint();
        }
    }
}
