package de.w3is.pixelflut.client;

/**
 * @author <a href="mailto:simon.weis@1und1.de">Simon Weis</a>
 * @since 5/27/16.
 */
public class PixelflutClient {

    public static void main(String[] args) throws Exception {
        String server = "94.45.232.57";
        int port = 23421;

        PixelPicture pixelPicture = new PixelPicture("/home/simon/Pictures/flower.jpg");

        //PixelflutTCP pixelflutTCP = new PixelflutTCP(server, port);
        PixelflutUDP pixelflutUDP = new PixelflutUDP(server, port);

        //pixelPicture.scaleTo(pixelflutUDP.getSize());

        pixelPicture.printTo(pixelflutUDP, 0, 0);
    }
}
