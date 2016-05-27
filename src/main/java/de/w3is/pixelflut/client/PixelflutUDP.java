package de.w3is.pixelflut.client;

import java.io.IOException;
import java.net.*;

/**
 * @author <a href="mailto:simon.weis@1und1.de">Simon Weis</a>
 * @since 5/28/16.
 */
public class PixelflutUDP implements Pixelflut{

    private InetAddress address;
    private int port;
    private DatagramSocket datagramSocket;

    public PixelflutUDP(String server, int port) throws UnknownHostException, SocketException {
        address = InetAddress.getByName(server);
        this.port = port;
        datagramSocket = new DatagramSocket();
    }

    @Override
    public Size getSize() throws IOException {
        return new Size(311, 207);
    }

    @Override
    public void draw(Pixel pixel) {
        byte[] bytes = String.format("PX %d %d %02X%02X%02X", pixel.getX(), pixel.getY(), pixel.getColor().getRed(), pixel.getColor().getGreen(), pixel.getColor().getBlue()).getBytes();
        DatagramPacket sendPacket = new DatagramPacket(bytes, bytes.length, address, port);
        try {
            datagramSocket.send(sendPacket);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
