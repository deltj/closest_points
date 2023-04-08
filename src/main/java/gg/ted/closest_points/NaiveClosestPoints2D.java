package gg.ted.closest_points;

import javax.sound.sampled.Line;
import java.awt.geom.Point2D;
import java.util.List;

/**
 * This very simple implementation compares every pair of points in the set.
 *
 * Running time is O(n^2)
 */
public class NaiveClosestPoints2D extends ClosestPoints2D {
    @Override
    public LineSegment2D closestPoints(List<Point2D> points) {
        resetCounters();

        LineSegment2D closest = null;

        for(Point2D a : points) {
            for(Point2D b : points) {
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
