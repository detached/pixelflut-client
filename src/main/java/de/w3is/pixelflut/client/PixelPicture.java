package de.w3is.pixelflut.client;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * @author <a href="mailto:simon.weis@1und1.de">Simon Weis</a>
 * @since 5/27/16.
 */
public class PixelPicture {

    BufferedImage bufferedImage;

    public PixelPicture(String path) throws IOException {
        bufferedImage = ImageIO.read(new File(path));
    }

    public void scaleTo(Size size) {
        BufferedImage scaledImage = new BufferedImage(size.getX(), size.getY(), bufferedImage.getType());
        Graphics2D graphics = scaledImage.createGraphics();
        graphics.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);

        graphics.drawImage(bufferedImage, 0, 0, size.getX(), size.getY(), 0, 0, bufferedImage.getWidth(),
                bufferedImage.getHeight(), null);
        graphics.dispose();
        bufferedImage = scaledImage;

        System.out.println("Scaled picture has " + bufferedImage.getWidth() + " " + bufferedImage.getHeight());
    }

    public void printTo(Pixelflut pixelflutTCP, int offX, int offY) {

        java.util.List<Pixel> pixels = new ArrayList<>();

        for(int x = 0; x < bufferedImage.getWidth(); x++) {
            for (int y = 0; y < bufferedImage.getHeight(); y++) {
                Color color = new Color(bufferedImage.getRGB(x, y));
                pixels.add(new Pixel(x + offX, y + offY, color));
            }
        }

        while (true) {
            pixels.stream().parallel().forEach(pixelflutTCP::draw);
        }
    }
}
