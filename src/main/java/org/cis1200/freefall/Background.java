package org.cis1200.freefall;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Background {

    public static final String IMG_FILE = "files/background.png";

    private final int x = 0;
    private int y;
    final private int velocity = -1;
    private static BufferedImage img;

    public Background() {
        this.y = 1;
        try {
            if (img == null) {
                img = ImageIO.read(new File(IMG_FILE));
            }
        } catch (IOException e) {
            System.out.println("Internal Error:" + e.getMessage());
        }
    }

    public void draw(Graphics g) {
        y += velocity;
        if (y == -683) {
            y = 0;
        }
        if (y < 0) {
            g.drawImage(img, x, y + 683, 410, 683, null);
        }
        g.drawImage(img, x, y, 410, 683, null);
    }
}
