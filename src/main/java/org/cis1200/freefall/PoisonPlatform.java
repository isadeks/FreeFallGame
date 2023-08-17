package org.cis1200.freefall;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class PoisonPlatform extends Platform {
    private BufferedImage img;

    public PoisonPlatform(
            int courtWidth, int courtHeight, int placementNumber, int py, boolean first
    ) {
        super(courtWidth, courtHeight, placementNumber, py, first);
        try {
            if (img == null) {
                img = ImageIO.read(new File("files/poisonplatform.png"));
            }
        } catch (IOException e) {
            System.out.println("Internal Error:" + e.getMessage());
        }
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(img, this.getPx(), this.getPy(), 90, 15, null);
    }
}
