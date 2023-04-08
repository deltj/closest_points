package gg.ted.closest_points;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.cli.*;

public class Main {

    public static void main(String[] args) {
        Options options = new Options();

        Option optGen = new Option("g", "generate", true, "generate N pairs of random points");
        Option optX = new Option("x", "min_x", true, "minimum x value");
        Option optY = new Option("y", "min_y", true, "minimum y value");
        Option optW = new Option("X", "max_x", true, "maximum x value");
        Option optH = new Option("Y", "max_y", true, "maximum y value");
        Option optRead = new Option("r", "read", true, "read points from a file");

        options.addOption(optGen);
        options.addOption(optX);
        options.addOption(optY);
        options.addOption(optW);
        options.addOption(optH);
        options.addOption(optRead);

        CommandLineParser parser = new DefaultParser();
        HelpFormatter helpFormatter = new HelpFormatter();

        try {
            CommandLine cmd = parser.parse(options, args);

            if(cmd.hasOption('g')) {
                //  The user wants to generate random points

                String strN = cmd.getOptionValue('g');
                int n = Integer.parseInt(strN);
                System.out.println("Generating " + n + " points");

                //  Bounding rectangle for point generation
                double origin_x = 0.0;
                double origin_y = 0.0;
                double width = 100.0;
                double height = 100.0;

                if (cmd.hasOption('x')) {
                    String strX = cmd.getOptionValue('x');
                    origin_x = Double.parseDouble(strX);
                }

                if (cmd.hasOption('y')) {
                    String strY = cmd.getOptionValue('y');
                    origin_y = Double.parseDouble(strY);
                }

                if (cmd.hasOption('w')) {
                    String strW = cmd.getOptionValue('w');
                    width = Double.parseDouble(strW);
                }

                if (cmd.hasOption('h')) {
                    String strH = cmd.getOptionValue('h');
                    height = Double.parseDouble(strH);
                }

                //  Generate the points
                Rectangle2D rect = new Rectangle2D.Double(origin_x, origin_y, width, height);
                List<Point2D> points = PointGenerator2D.generatePoints(rect, n);

                //  Print them out to standard output
                for (Point2D p : points) {
                    System.out.println(p.getX() + "," + p.getY());
                }
            }

            if(cmd.hasOption('r')) {
                String fileName = cmd.getOptionValue('r');

                List<Point2D> points = new ArrayList<Point2D>();
                BufferedReader reader = new BufferedReader(new FileReader(fileName));
                for(String line; (line = reader.readLine()) != null; ) {
                    String[] tokens = line.split(",");
                    if(tokens.length == 2) {
                        double x = Double.parseDouble(tokens[0]);
                        double y = Double.parseDouble(tokens[1]);
                        points.add(new Point2D.Double(x, y));
                    }
                }
                System.out.println("Read " + points.size() + " points");

                ClosestPoints2D cp2d = new NaiveClosestPoints2D();
                LineSegment2D c = cp2d.closestPoints(points);

                System.out.println("The closest points are: " + c.getA().toString() + " " + c.getB().toString());
                System.out.println("with a distance of: " + c.length());
                System.out.println("Length calls: " + cp2d.getNumLengthCalls());
            }
        } catch (ParseException e) {
            System.out.println(e.getMessage());
            helpFormatter.printHelp("closest_points", options);
            System.exit(1);
        } catch (NumberFormatException e) {
            helpFormatter.printHelp("closest_points", options);
            System.exit(1);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }
}