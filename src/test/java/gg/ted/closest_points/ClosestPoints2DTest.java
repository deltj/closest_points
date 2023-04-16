package gg.ted.closest_points;

import org.junit.jupiter.api.Test;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ClosestPoints2DTest {

    @Test
    void closestPoints_Naive_twoPoints() {
        List<Point2D> points = new ArrayList<>();
        points.add(new Point2D.Double(0, 0));
        points.add(new Point2D.Double(0, 1));

        ClosestPoints2D cp2d = new NaiveClosestPoints2D();
        LineSegment2D c = cp2d.closestPoints(points);

        assertEquals(4, cp2d.getNumLengthCalls());
        assertEquals(1.0, c.length(), 0.001);
    }

    @Test
    void closestPoints_Naive_threePoints() {
        List<Point2D> points = new ArrayList<>();
        points.add(new Point2D.Double(0, 0));
        points.add(new Point2D.Double(0, 1));
        points.add(new Point2D.Double(0, 3));

        ClosestPoints2D cp2d = new NaiveClosestPoints2D();
        LineSegment2D c = cp2d.closestPoints(points);

        assertEquals(9, cp2d.getNumLengthCalls());
        assertEquals(1.0, c.length(), 0.001);
    }
}