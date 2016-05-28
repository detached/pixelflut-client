package de.w3is.pixelflut.client;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

/**
 * @author <a href="mailto:simon.weis@1und1.de">Simon Weis</a>
 * @since 5/28/16.
 */
public interface Pixelflut {
    Optional<Size> getSize() throws IOException;
    void send(Pixel pixel);

    default String toMessage(Pixel pixel) {
        return String.format("PX %d %d %02X%02X%02X",
                pixel.getX(), pixel.getY(),
                pixel.getColor().getRed(),
                pixel.getColor().getGreen(),
                pixel.getColor().getBlue());
    }
}
