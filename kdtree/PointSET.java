/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;

import java.util.ArrayList;
import java.util.TreeSet;

public class PointSET {
    private TreeSet<Point2D> tree = new TreeSet<>();

    public PointSET() {

    }

    public boolean isEmpty() {
        return tree.isEmpty();
    }

    public int size() {
        return tree.size();
    }

    public void insert(Point2D p) {
        tree.add(p);
    }

    public boolean contains(Point2D p) {
        return tree.contains(p);
    }

    public Iterable<Point2D> range(RectHV rect) {
        return new ArrayList<Point2D>();
    }

    public void draw() {
        for (Point2D p : tree) {
            p.draw();
        }
    }

    public Point2D nearest(Point2D p) {
        return p;
    }

    public static void main(String[] args) {
        PointSET p = new PointSET();
        p.insert(new Point2D(1, 2));
        p.insert(new Point2D(2, 2));
        p.draw();
    }
}
