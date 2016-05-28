package de.w3is.pixelflut.client;

import java.util.stream.Stream;

/**
 * @author <a href="mailto:simon.weis@1und1.de">Simon Weis</a>
 * @since 5/27/16.
 */
public class PixelflutClient {

    public static void main(String[] args) throws Exception {

        if (args.length != 5) {
            System.out.println("Pixelflut-client $host $port $image $offX $offY");
            return;
        }

        String server = args[0];
        int port = Integer.parseInt(args[1]);

        PixelPicture pixelPicture = new PixelPicture(args[2]);

        int offX = Integer.parseInt(args[3]);
        int offY = Integer.parseInt(args[4]);

        //PixelflutTCP pixelflutTCP = new PixelflutTCP(server, port);
        PixelflutUDP pixelflutUDP = new PixelflutUDP(server, port);

        //pixelPicture.scaleTo(pixelflutUDP.getSize());

        pixelPicture.printTo(pixelflutUDP, offX, offY);
    }
}
