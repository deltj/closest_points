package gg.ted.closest_points;

import org.junit.jupiter.api.Test;

import java.awt.geom.Point2D;

import static org.junit.jupiter.api.Assertions.*;

class LineSegment2DTest {

    @Test
    void testLength() {
        Point2D a = new Point2D.Double(0, 0);
        Point2D b = new Point2D.Double(1, 1);
        LineSegment2D ab = new LineSegment2D(a, b);
        assertEquals(1.414214, ab.length(), 0.001);
    }
}