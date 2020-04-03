package com.mashibing;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @create: 2020-04-01 14:52
 **/
public class ResourceMgr {

    public static BufferedImage goodTank1U, goodTank1D, goodTank1L, goodTank1R;
    public static BufferedImage goodTank2U, goodTank2D, goodTank2L, goodTank2R;
    public static BufferedImage badTank1U, badTank1D, badTank1L, badTank1R;
    public static BufferedImage badTank2U, badTank2D, badTank2L, badTank2R;
    public static BufferedImage bulletU, bulletD, bulletL, bulletR;

    public static BufferedImage[] explodeImg = new BufferedImage[16];

    private ResourceMgr(){}


    static {
        try {
            goodTank1U = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("image/GoodTank1.png"));
            goodTank1D = ImageUtil.rotateImage(goodTank1U, 180);
            goodTank1L = ImageUtil.rotateImage(goodTank1U, -90);
            goodTank1R = ImageUtil.rotateImage(goodTank1U, 90);

            goodTank2U = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("image/GoodTank2.png"));
            goodTank2D = ImageUtil.rotateImage(goodTank2U, 180);
            goodTank2L = ImageUtil.rotateImage(goodTank2U, -90);
            goodTank2R = ImageUtil.rotateImage(goodTank2U, 90);


            badTank1U = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("image/BadTank1.png"));
            badTank1D = ImageUtil.rotateImage(badTank1U, 180);
            badTank1L = ImageUtil.rotateImage(badTank1U, -90);
            badTank1R =ImageUtil.rotateImage(badTank1U, 90);

            badTank2U = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("image/badTank2.png"));
            badTank2D = ImageUtil.rotateImage(badTank2U, 180);
            badTank2L = ImageUtil.rotateImage(badTank2U, -90);
            badTank2R =ImageUtil.rotateImage(badTank2U, 90);


            bulletU = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("image/bulletU.gif"));
            bulletL = ImageUtil.rotateImage(bulletU, -90);
            bulletR = ImageUtil.rotateImage(bulletU, 90);
            bulletD = ImageUtil.rotateImage(bulletU, 180);


            for(int i=1; i<=explodeImg.length; i++){
                explodeImg[i-1] = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("image/e"+i+".gif"));
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
