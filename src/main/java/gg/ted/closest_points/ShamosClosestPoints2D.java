package gg.ted.closest_points;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * This is an implementation of the divide and conquer closest points algorithm
 * described in Computational Geometry by Preparata and Shamos.  It is also
 * described in revision 3 of CLRS, section 33.4.
 */
public class ShamosClosestPoints2D extends ClosestPoints2D {

    /**
     * Create a copy of the provided List of points and sort it by X value
     * @param points A set of points
     * @return Points sorted by X value
     */
    public List<Point2D> sortX(List<Point2D> points) {
        List<Point2D> sortedPoints = new ArrayList<>(points);
        sortedPoints.sort(Comparator.comparingDouble(Point2D::getX));
        return sortedPoints;
    }

    /**
     * Create a copy of the provided List of points and sort it by Y value
     * @param points A set of points
     * @return Points sorted by Y value
     */
    public List<Point2D> sortY(List<Point2D> points) {
        List<Point2D> sortedPoints = new ArrayList<>(points);
        sortedPoints.sort(Comparator.comparingDouble(Point2D::getY));
        return sortedPoints;
    }

    /**
     * Find the median X value in the point set
     * @param points A set of points
     * @return The median x value
     */
    public double medianX(List<Point2D> points) {
        final int n = points.size();
        if(n % 2 == 0) {
            return (points.get((n/2) - 1).getX() + points.get(n/2).getX()) / 2.0;
        } else {
            return points.get(n/2).getX();
        }
    }

    /**
     * Find the closest pair of points using the Shamos algorithm.  Running time is O(n log n)
     * @param P The point set to analyze
     * @return A line segment describing the two closest points in the set
     */
    @Override
    public LineSegment2D closestPoints(List<Point2D> P) {
        final int n = P.size();
        //System.out.println("n=" + n);

        //  Recursion base case
        if(n <= 3) {
            //System.out.println("recursion base case");

            //  Use the naive closest points algorithm
            NaiveClosestPoints2D ncp2d = new NaiveClosestPoints2D();
            LineSegment2D result = ncp2d.closestPoints(P);
            numLengthCalls += ncp2d.getNumLengthCalls();
            return result;
        }

        //  Sort the points by X and Y values
        List<Point2D> X = sortX(P);
        List<Point2D> Y = sortY(P);
        //TODO: This implementation sorts P in every recursive call, which seems bad.
        //  I may need to use a helper function to pass the sorted values to each
        //  subsequent call

        //  Find the median x value
        final double mx = medianX(X);
        //System.out.println("mx=" + mx);

        //  Bisect the input set using the median x value
        List<Point2D> Pl = new ArrayList<>();
        List<Point2D> Pr = new ArrayList<>();
        for(Point2D p : P) {
            if(p.getX() < mx) {
                Pl.add(p);
            } else {
                Pr.add(p);
            }
        }
        //System.out.println("|Pl|=" + Pl.size());
        //System.out.println("|Pr|=" + Pr.size());

        //  Recursively call closestPoints for Pl and Pr
        LineSegment2D Cl = closestPoints(Pl);
        LineSegment2D Cr = closestPoints(Pr);

        //  Find d, the shorter of the distances between the closest points in Pl and Pr
        final double dl = Cl.length();
        final double dr = Cr.length();
        final double d = Math.min(dl, dr);

        //  dl and dr are the lengths of the shortest line segments in Pl and Pr, respectively.
        //  If the closest pair of points in P spans Pl and Pr, then they are within
        //  distance d of the median line.

        //  Create Y' containing points within distance d of the median line
        List<Point2D> Yprime = new ArrayList<>();
        for(Point2D p : Y) {
            final double px = p.getX();
            if((mx - d) < px && px < (mx + d)) {
                Yprime.add(p);
            }
        }
        //System.out.println("|y'|=" + Yprime.size());

        //  Find the closest points in Y' using the naive method
        LineSegment2D dprime = null;
        if(Yprime.size() > 1) {
            NaiveClosestPoints2D ncp2d = new NaiveClosestPoints2D();
            dprime = ncp2d.closestPoints(Yprime);
            numLengthCalls += ncp2d.getNumLengthCalls();
        }

        //  Figure out which of the three potential solutions is the closest
        if(dprime != null && dprime.length() < dl && dprime.length() < dr) {
            //  Y' contains a pair of points closer than the closest points in Pl and Pr
            //System.out.println("closest pair spans Pl and Pr");
            return dprime;
        } else if(dl < dr) {
            //  Cl is the closest pair
            //System.out.println("closest pair is in Cl");
            return Cl;
        } else if(dr < dl) {
            //  Cr is the closest pair
            //System.out.println("closets pair is in Cr");
            return Cr;
        }

        //  If this code is reached then there is a logic error in the if statements above
        return null;
    }
}
