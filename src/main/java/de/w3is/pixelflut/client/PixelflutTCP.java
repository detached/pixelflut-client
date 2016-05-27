package de.w3is.pixelflut.client;

import java.awt.*;
import java.io.*;
import java.net.Socket;

/**
 * @author <a href="mailto:simon.weis@1und1.de">Simon Weis</a>
 * @since 5/27/16.
 */
public class PixelflutTCP implements Pixelflut
{
    private Socket socket;
    private PrintWriter printWriter;
    private BufferedReader bufferedReader;

    public PixelflutTCP(String host, int port) throws IOException {
        socket = new Socket(host, port);
        printWriter = new PrintWriter(socket.getOutputStream(), true);
        bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    @Override
    public Size getSize() throws IOException {
        System.out.println("=> SIZE");
        printWriter.println("SIZE");
        String response = bufferedReader.readLine();
        System.out.println("<= " + response);

        if (response != null && response.startsWith("SIZE")) {
            String[] parts = response.split("\\s+");

            if (parts.length != 3) {
                throw new IllegalStateException("Invalid SIZE: " + response);
            }

            return new Size(Integer.parseInt(parts[1]), Integer.parseInt(parts[2]));

        } else {
            throw new IllegalStateException("Expected SIZE but server said: " + response);
        }
    }

    @Override
    public void draw(Pixel pixel) {
        String msg = String.format("PX %d %d %02X%02X%02X", pixel.getX(), pixel.getY(), pixel.getColor().getRed(), pixel.getColor().getGreen(), pixel.getColor().getBlue());
        printWriter.println(msg);
    }
}
