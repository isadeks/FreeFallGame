package org.cis1200.freefall;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Character extends GameObj {

    public static final String IMG_FILE = "files/character.png";
    public static final int INIT_POS_X = 179;
    public static final int INIT_POS_Y = 60;
    public static final int INIT_VEL_X = 0;
    // public static final int INIT_VEL_Y = 1;
    public static final int INIT_VEL_Y = 2;
    private static BufferedImage img;

    public Character(int courtWidth, int courtHeight) {
        super(INIT_VEL_X, INIT_VEL_Y, INIT_POS_X, INIT_POS_Y, 40, 40, courtWidth, courtHeight);
        try {
            if (img == null) {
                img = ImageIO.read(new File(IMG_FILE));
            }
        } catch (IOException e) {
            System.out.println("Internal Error:" + e.getMessage());
        }
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(img, this.getPx(), this.getPy(), 40, 40, null);
    }
}
