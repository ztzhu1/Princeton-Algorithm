/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class BruteCollinearPoints {
    private Point[] points;

    public BruteCollinearPoints(Point[] points) {
        if (points == null) {
            throw new IllegalArgumentException();
        }
        for (int i = 0; i < points.length; i++) {
            if (points[i] == null) {
                throw new IllegalArgumentException();
            }
        }
        for (int i = 0; i < points.length - 1; ++i) {
            for (int j = i + 1; j < points.length; ++j) {
                if (points[i].compareTo(points[j]) == 0) {
                    throw new IllegalArgumentException();
                }
            }
        }
        this.points = points.clone();
    }

    public int numberOfSegments() {
        return segments().length;
    }

    public LineSegment[] segments() {
        ArrayList<LineSegment> segs = new ArrayList<>();
        for (int a = 0; a < points.length - 3; ++a) {
            Comparator<Point> comp = points[a].slopeOrder();
            for (int b = a + 1; b < points.length - 2; ++b) {
                for (int c = b + 1; c < points.length - 1; ++c) {
                    for (int d = c + 1; d < points.length; ++d) {
                        if (comp.compare(points[b], points[c]) == 0
                                && comp.compare(points[b], points[d]) == 0
                                && comp.compare(points[c], points[d]) == 0) {
                            Point[] p = new Point[4];
                            p[0] = points[a];
                            p[1] = points[b];
                            p[2] = points[c];
                            p[3] = points[d];
                            Arrays.sort(p);
                            segs.add(new LineSegment(p[0], p[3]));
                        }
                    }
                }
            }
        }
        LineSegment[] ret = new LineSegment[segs.size()];
        for (int i = 0; i < ret.length; ++i) {
            ret[i] = segs.remove(0);
        }
        return ret;
    }

    public static void main(String[] args) {
        Point a1 = new Point(10000, 0);
        Point a2 = new Point(0, 10000);
        Point a3 = new Point(3000, 7000);
        Point a4 = new Point(7000, 3000);
        Point a5 = new Point(20000, 21000);
        Point a6 = new Point(3000, 4000);
        Point a7 = new Point(14000, 15000);
        Point a8 = new Point(6000, 7000);
        Point[] points = { a1, a2, a3, a4, a5, a6, a7, a8 };
        BruteCollinearPoints b = new BruteCollinearPoints(points);
        System.out.println(b.numberOfSegments());
        System.out.println(b.numberOfSegments());
        LineSegment[] ls = b.segments();
        for (int i = 0; i < ls.length; ++i) {
            System.out.println(ls[i].toString());
        }
        ls[0] = new LineSegment(a7, a8);
        ls = b.segments();
        for (int i = 0; i < ls.length; ++i) {
            System.out.println(ls[i].toString());
        }
    }
}
