package gg.ted.closest_points;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Generate random points on a 2D plane
 */
public class PointGenerator2D {

    public static List<Point2D> generatePoints(Rectangle2D boundingRect, int n) {
        List<Point2D> points = new ArrayList<Point2D>();

        //  Note: There is no need for cryptographically secure random number generation
        //  in this program;  in fact the predictability of using a fixed seed may be
        //  useful
        final long seed = 12345;
        Random r = new Random(seed);

        for(int i=0; i<n; i++) {
            double p_x = r.nextDouble(boundingRect.getMinX(), boundingRect.getMaxX());
            double p_y = r.nextDouble(boundingRect.getMinY(), boundingRect.getMaxY());
            Point2D p = new Point2D.Double(p_x, p_y);
            points.add(p);
        }

        return points;
    }
}
