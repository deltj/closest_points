package gg.ted.closest_points;

import java.awt.geom.Point2D;
import java.util.List;

/**
 * Subclasses provide a method to find the closest pair in a set of points.
 */
public abstract class ClosestPoints2D {
    protected int numLengthCalls = 0;

    /**
     * Find the closest pair of points in the point set
     * @param S The point set to analyze
     * @return The closest pair as a line segment
     */
    public abstract LineSegment2D closestPoints(List<Point2D> S);

    /**
     * Reset performance counters
     */
    public void resetCounters() {
        numLengthCalls = 0;
    }

    /**
     * Return the number of times the LineSegment2D.length() method was called to determine the closest pair.
     * @return The number of times LineSegment2D.length() was called
     */
    public int getNumLengthCalls() { return numLengthCalls; }
}
