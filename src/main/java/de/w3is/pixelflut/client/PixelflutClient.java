package de.w3is.pixelflut.client;

import org.apache.commons.cli.*;

/**
 * @author <a href="mailto:simon.weis@1und1.de">Simon Weis</a>
 * @since 5/27/16.
 */
public class PixelflutClient {

    public static void main(String[] args) throws Exception {

        Options options = getOptions();

        CommandLine cli;

        try {
            cli = parseArguments(args, options);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
            printHelp(options);
            return;
        }

        String server = cli.getOptionValue("host");
        int port = Integer.parseInt(cli.getOptionValue("port"));

        Pixelflut pixelflut;

        if (cli.hasOption("udp")) {
            pixelflut = new PixelflutUDP(server, port);
        } else {
            pixelflut = new PixelflutTCP(server, port);
        }

        PixelPicture pixelPicture = new PixelPicture(cli.getOptionValue("file"));

        int offX = Integer.parseInt(cli.getOptionValue("ox", "0"));
        int offY = Integer.parseInt(cli.getOptionValue("oy", "0"));

        pixelPicture.printTo(pixelflut, offX, offY);
    }

    private static void printHelp(Options options) {
        HelpFormatter helpFormatter = new HelpFormatter();
        helpFormatter.printHelp("pixelflut-client", options, true);
    }

    private static CommandLine parseArguments(String[] args, Options options) throws ParseException {
        CommandLineParser commandLineParser = new DefaultParser();
        return commandLineParser.parse(options, args);
    }

    private static Options getOptions() {
        Options options = new Options();

        options.addOption(Option.builder("h").longOpt("host").type(String.class)
                .hasArg().required().desc("Hostname or IP of the Pixelflut server").build());

        options.addOption(Option.builder("p").longOpt("port").type(Integer.class)
                .hasArg().required().desc("Port to send packages to").build());

        options.addOption(Option.builder("f").longOpt("file").type(String.class)
                .hasArg().required().desc("The file that should be drawn").build());

        options.addOption(Option.builder("ox").longOpt("offset-x").type(Integer.class)
                .hasArg().desc("The offset of the x axis").build());

        options.addOption(Option.builder("oy").longOpt("offset-y").type(Integer.class)
                .hasArg().desc("The offset of the y axis").build());

        options.addOption(Option.builder("u").longOpt("udp").desc("Use udp protocol").build());
        return options;
    }
}
