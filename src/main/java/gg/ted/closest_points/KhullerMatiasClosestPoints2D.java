package gg.ted.closest_points;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

/**
 * This is an implementation of the randomized closest points algorithm
 * by Khuller and Matias (1995).  Expected (average) running time is O(n).
 */
public class KhullerMatiasClosestPoints2D extends ClosestPoints2D {
    protected HashMap<Integer, ArrayList<Point2D>> grid = new HashMap<>();

    public static int gridIndex(Point2D p, Point2D ll, double gridSize) {
        final Double xDelta = p.getX() - ll.getX();
        final Double yDelta = p.getY() - ll.getY();
        final int xOffset = (int)Math.floor(xDelta / gridSize);
        final int yOffset = (int)Math.floor(yDelta / gridSize);
        //System.out.println("xOffset = " + xOffset);
        //System.out.println("yOffset = " + yOffset);

        return (int)(Math.floor(p.getX() / gridSize) * Math.floor(p.getY() / gridSize));
    }

    /**
     * Find the closest pair of points using the Khuller and Matias algorithm
     * @param S The point set to analyze
     * @return A line segment describing the two closest points in the set
     */
    @Override
    public LineSegment2D closestPoints(List<Point2D> S) {
        final int n = S.size();

        //  Find lower left and upper right points of a rectangle bounding the points in S
        double minX = Double.MAX_VALUE;
        double maxX = Double.MIN_VALUE;
        double minY = Double.MAX_VALUE;
        double maxY = Double.MIN_VALUE;
        for(Point2D p : S) {
            double x = p.getX();
            double y = p.getY();

            if(x < minX) {
                minX = x;
            }

            if(x > maxX) {
                maxX = x;
            }

            if(y < minY) {
                minY = y;
            }

            if(y > maxY) {
                maxY = y;
            }
        }

        Point2D gridll = new Point2D.Double(minX, minY);
        Point2D gridur = new Point2D.Double(maxX, maxY);

        //  Pick a random point in S
        final long seed = System.currentTimeMillis();
        Random r = new Random(seed);
        final int i = r.nextInt(n);
        Point2D p = S.get(i);

        //  Find the distance d between p and the closest point to it
        //  This step takes O(n)
        double d = Double.MAX_VALUE;
        for(Point2D q : S) {
            LineSegment2D pq = new LineSegment2D(p, q);
            if(pq.length() > 0 && pq.length() < d) {
                d = pq.length();
            }
        }

        //  Distance d is now the approximation of the closest points

        //  Construct the grid with size d


        return null;
    }
}
