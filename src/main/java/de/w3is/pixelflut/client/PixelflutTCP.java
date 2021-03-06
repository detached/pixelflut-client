package de.w3is.pixelflut.client;

import java.io.*;
import java.net.Socket;
import java.util.Optional;

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
    public Optional<Size> getSize() throws IOException {
        printWriter.println("SIZE");
        String response = bufferedReader.readLine();

        if (response != null && response.startsWith("SIZE")) {
            String[] parts = response.split("\\s+");

            if (parts.length != 3) {
                throw new IllegalStateException("Invalid SIZE: " + response);
            }

            return Optional.of(new Size(Integer.parseInt(parts[1]), Integer.parseInt(parts[2])));

        } else {
            throw new IllegalStateException("Expected SIZE but server said: " + response);
        }
    }

    @Override
    public void send(Pixel pixel) {
        printWriter.println(toMessage(pixel));
    }
}
