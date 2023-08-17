package org.cis1200.freefall;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class BrokenPlatform extends Platform {
    private BufferedImage img;

    private int timeLeft = 8;
    private boolean visible = true;

    public BrokenPlatform(
            int courtWidth, int courtHeight, int placementNumber, int py, boolean first
    ) {
        super(courtWidth, courtHeight, placementNumber, py, first);
        try {
            if (img == null) {
                img = ImageIO.read(new File("files/brokenplatform.png"));
            }
        } catch (IOException e) {
            System.out.println("Internal Error:" + e.getMessage());
        }
    }

    public void touched() {
        timeLeft--;
        if (timeLeft == 0) {
            visible = false;
        }
    }

    public boolean isBroken() {
        return !visible;
    }

    @Override
    public void draw(Graphics g) {
        if (visible) {
            g.drawImage(img, this.getPx(), this.getPy(), 90, 15, null);
        }
    }
}
