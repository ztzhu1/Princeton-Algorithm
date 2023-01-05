/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class FastCollinearPoints {

    private Point[] points;

    public FastCollinearPoints(Point[] points) {
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
        for (int i = 0; i < points.length; ++i) {
            Comparator<Point> comp = points[i].slopeOrder();
            ArrayList<Point> p = new ArrayList<>();
            for (int j = 0; j < points.length; ++j) {
                if (i != j) {
                    p.add(points[j]);
                }
            }
            Collections.sort(p, comp);
            for (int k = 0; k < p.size(); ) {
                boolean plused = false;
                int a = k;
                while (k < p.size() && comp.compare(p.get(a), p.get(k)) == 0) {
                    ++k;
                    plused = true;
                }
                if (k - a >= 3) {
                    Point[] tmp = new Point[k - a + 1];
                    tmp[tmp.length - 1] = points[i];
                    for (int ii = 0; ii < k - a; ++ii) {
                        tmp[ii] = p.get(a + ii);
                    }
                    Arrays.sort(tmp);
                    boolean exist = false;
                    LineSegment ls = new LineSegment(tmp[0], tmp[tmp.length - 1]);
                    for (int m = 0; m < segs.size(); ++m) {
                        if (segs.get(m).toString().equals(ls.toString())) {
                            exist = true;
                            break;
                        }
                    }
                    if (!exist) {
                        segs.add(ls);
                    }
                }
                if (!plused) {
                    ++k;
                }
            }
        }
        LineSegment[] ret = new LineSegment[segs.size()];
        for (int i = 0; i < segs.size(); ++i) {
            ret[i] = segs.get(i);
        }
        return ret;
    }

    public static void main(String[] args) {
        Point a1 = new Point(0, 0);
        Point a2 = new Point(0, 2);
        Point a3 = new Point(0, 1);
        Point a4 = new Point(0, 3);
        Point a5 = new Point(1, 0);
        Point a6 = new Point(2, 0);
        Point a7 = new Point(3, 0);
        Point a8 = new Point(4, 0);
        Point[] points = { a1, a2, a3, a4, a5, a6, a7, a8 };
        FastCollinearPoints b = new FastCollinearPoints(points);
        LineSegment[] ls = b.segments();
        for (int i = 0; i < ls.length; ++i) {
            System.out.println(ls[i].toString());
        }
        ls = b.segments();
        for (int i = 0; i < ls.length; ++i) {
            System.out.println(ls[i].toString());
        }
    }
}
