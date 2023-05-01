package gg.ted.closest_points;

import org.junit.jupiter.api.Test;

import java.awt.geom.Point2D;

import static org.junit.jupiter.api.Assertions.*;

class KhullerMatiasClosestPoints2DTest {

    @Test
    void gridIndex1() {
        Point2D ll = new Point2D.Double(0, 0);
        double d = 3.0;
        Point2D p = new Point2D.Double(1.0, 1.0);
        int i = KhullerMatiasClosestPoints2D.gridIndex(p, ll, d);
        assertEquals(0, i);
    }

    @Test
    void gridIndex2() {
        Point2D ll = new Point2D.Double(0, 0);
        double d = 3.0;
        Point2D p = new Point2D.Double(3.1, 1.0);
        int i = KhullerMatiasClosestPoints2D.gridIndex(p, ll, d);
        //assertEquals(1, i);
    }
}