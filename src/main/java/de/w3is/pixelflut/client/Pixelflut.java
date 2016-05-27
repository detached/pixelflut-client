package de.w3is.pixelflut.client;

import java.io.IOException;

/**
 * @author <a href="mailto:simon.weis@1und1.de">Simon Weis</a>
 * @since 5/28/16.
 */
public interface Pixelflut {
    Size getSize() throws IOException;
    void draw(Pixel pixel);
}
