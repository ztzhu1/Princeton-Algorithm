/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeSet;

public class PointSET {
    private TreeSet<Point2D> tree = new TreeSet<>();

    public PointSET() {
        StdDraw.setPenRadius(0.02);
    }

    public boolean isEmpty() {
        return tree.isEmpty();
    }

    public int size() {
        return tree.size();
    }

    public void insert(Point2D p) {
        if (p == null) {
            throw new IllegalArgumentException();
        }
        tree.add(p);
    }

    public boolean contains(Point2D p) {
        if (p == null) {
            throw new IllegalArgumentException();
        }
        return tree.contains(p);
    }

    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null) {
            throw new IllegalArgumentException();
        }
        ArrayList<Point2D> ret = new ArrayList<>();
        Iterator<Point2D> it = tree.iterator();
        while (it.hasNext()) {
            Point2D p = it.next();
            if (p.x() >= rect.xmin()
                    && p.x() <= rect.xmax()
                    && p.y() >= rect.ymin()
                    && p.y() <= rect.ymax()) {
                ret.add(p);
            }
        }
        return ret;
    }

    public void draw() {
        for (Point2D p : tree) {
            p.draw();
        }
    }

    public Point2D nearest(Point2D p) {
        if (p == null) {
            throw new IllegalArgumentException();
        }
        if (isEmpty()) {
            return null;
        }
        Point2D ret = tree.first();
        double dist = p.distanceSquaredTo(ret);
        Iterator<Point2D> it = tree.iterator();
        while (it.hasNext()) {
            Point2D curr = it.next();
            if (p.distanceSquaredTo(curr) < p.distanceSquaredTo(ret)) {
                dist = p.distanceSquaredTo(curr);
                ret = curr;
            }
        }
        return ret;
    }

    public static void main(String[] args) {
        PointSET p = new PointSET();
        p.insert(new Point2D(0.1, 0.2));
        p.insert(new Point2D(0.2, 0.2));
        Point2D pp = new Point2D(0, 0);
        p.draw();
        System.out.println(p.nearest(pp).x());
    }
}
