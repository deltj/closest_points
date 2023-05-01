package gg.ted.closest_points;

import java.awt.geom.Point2D;
import java.util.List;

/**
 * This very simple implementation compares every pair of points in the set.
 */
public class NaiveClosestPoints2D extends ClosestPoints2D {
    /**
     * Brute-force algorithm for finding the closest points.  Running time is O(n^2)
     * @param P The point set to analyze
     * @return A line segment describing the two closest points in the set
     */
    @Override
    public LineSegment2D closestPoints(List<Point2D> S) {
        resetCounters();

        LineSegment2D closest = null;

        for(Point2D a : S) {
            for(Point2D b : S) {
                LineSegment2D ab = new LineSegment2D(a, b);

                final double len_ab = ab.length();
                numLengthCalls++;

                if(len_ab == 0.0) {
                    continue;
                }

                if(closest == null) {
                    closest = ab;
                    continue;
                }

                if (len_ab < closest.length()) {
                    numLengthCalls++;
                    closest = ab;
                }
            }
        }

        return closest;
    }
}
