package gg.ted.closest_points;

import java.awt.geom.Point2D;

/**
 * A two-dimensional closed line segment
 */
public class LineSegment2D {
    private Point2D a;
    private Point2D b;

    public LineSegment2D(Point2D a, Point2D b) {
        this.a = a;
        this.b = b;
    }

    public Point2D getA() { return a; }
    public Point2D getB() { return b; }

    public void setA(Point2D a) { this.a = a; }
    public void setB(Point2D b) { this.b = b; }

    /**
     * Find the length of the line segment as the Euclidian distance between the endpoints
     * @return The length of the line segment
     */
    public double length() {
        final double a_x = a.getX();
        final double a_y = a.getY();
        final double b_x = b.getX();
        final double b_y = b.getY();
        return Math.sqrt( (a_x - b_x) * (a_x - b_x) + (a_y - b_y) * (a_y - b_y) );
    }
}
