package org.cis1200.freefall;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Platform extends GameObj {
    private final String imgFile = "files/platform.png";
    public static final int INIT_POS_X = 0;
    public static final int INIT_VEL_X = 0;
    public static final int INIT_VEL_Y = -1;
    private int placementNumber;
    private static BufferedImage img;

    public Platform(
            int courtWidth, int courtHeight, int placementNumber, int initialPY, boolean first
    ) {
        super(INIT_VEL_X, INIT_VEL_Y, INIT_POS_X, initialPY, 90, 15, courtWidth, courtHeight);
        this.placementNumber = placementNumber;
        try {
            if (img == null) {
                img = ImageIO.read(new File(imgFile));
            }
        } catch (IOException e) {
            System.out.println("Internal Error:" + e.getMessage());
        }
        if (placementNumber == 1) {
            if (first) {
                this.setPx(-25);
            } else {
                this.setPx((int) (Math.random() * (15 + 50) - 50));
            }
        } else if (placementNumber == 2) {
            if (first) {
                this.setPx(150);
            } else {
                this.setPx((int) (Math.random() * (175 - 125) + 125));
            }
        } else {
            if (first) {
                this.setPx(325);
            } else {
                this.setPx((int) (Math.random() * (350 - 290) + 290));
            }
        }
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(img, this.getPx(), this.getPy(), 90, 15, null);
    }
}
