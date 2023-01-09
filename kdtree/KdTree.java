/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdDraw;

import java.util.ArrayList;
import java.util.Iterator;

public class KdTree {
    private Node root = null;

    private class Node {
        private Point2D key = null;
        private Node parent = null;
        private Node left = null;
        private Node right = null;
        private int height = 0;
        private int size = 1;
    }

    public KdTree() {
        point();
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public int size() {
        return size(root);
    }

    private int size(Node node) {
        if (node == null) {
            return 0;
        }
        // return 1 + size(node.left) + size(node.right);
        return node.size;
    }

    public void insert(Point2D p) {
        if (p == null) {
            throw new IllegalArgumentException();
        }
        root = insert(root, null, p);
    }

    private Node insert(Node node, Node parent, Point2D key) {
        if (node == null) {
            node = new Node();
            node.key = key;
            node.parent = parent;
            node.size = 1;
            if (parent == null) {
                node.height = 0;
            }
            else {
                node.height = parent.height + 1;
                while (parent != null) {
                    parent.size++;
                    parent = parent.parent;
                }
            }
            return node;
        }
        if (node.key.equals(key)) {
            return node;
        }
        if (node.height % 2 == 0) {
            if (key.x() < node.key.x()) {
                node.left = insert(node.left, node, key);
            }
            else {
                node.right = insert(node.right, node, key);
            }
        }
        else {
            if (key.y() < node.key.y()) {
                node.left = insert(node.left, node, key);
            }
            else {
                node.right = insert(node.right, node, key);
            }
        }
        return node;
    }

    public boolean contains(Point2D p) {
        if (p == null) {
            throw new IllegalArgumentException();
        }
        Node node = root;
        while (node != null) {
            if (node.key.equals(p)) {
                return true;
            }
            if (node.height % 2 == 0) {
                if (p.x() < node.key.x()) {
                    node = node.left;
                }
                else {
                    node = node.right;
                }
            }
            else {
                if (p.y() < node.key.y()) {
                    node = node.left;
                }
                else {
                    node = node.right;
                }
            }
        }
        return false;
    }

    public Point2D nearest(Point2D query) {
        if (query == null) {
            throw new IllegalArgumentException();
        }
        Point2D best = nearest(root, query);
        return best;
    }

    private Point2D nearest(Node n, Point2D query) {
        if (n == null) {
            return null;
        }
        Point2D best = n.key;
        Point2D tmp;
        if (n.height % 2 == 0) {
            if (query.x() < n.key.x()) {
                tmp = nearest(n.left, query);
                if (tmp != null && tmp.distanceSquaredTo(query) < best.distanceSquaredTo(query)) {
                    best = tmp;
                }
                if (Math.pow(n.key.x() - query.x(), 2) < best.distanceSquaredTo(query)) {
                    tmp = nearest(n.right, query);
                    if (tmp != null && tmp.distanceSquaredTo(query) < best.distanceSquaredTo(
                            query)) {
                        best = tmp;
                    }
                }
            }
            else {
                tmp = nearest(n.right, query);
                if (tmp != null && tmp.distanceSquaredTo(query) < best.distanceSquaredTo(query)) {
                    best = tmp;
                }
                if (Math.pow(query.x() - n.key.x(), 2) < best.distanceSquaredTo(query)) {
                    tmp = nearest(n.left, query);
                    if (tmp != null && tmp.distanceSquaredTo(query) < best.distanceSquaredTo(
                            query)) {
                        best = tmp;
                    }
                }
            }
        }
        else {
            if (query.y() < n.key.y()) {
                tmp = nearest(n.left, query);
                if (tmp != null && tmp.distanceSquaredTo(query) < best.distanceSquaredTo(query)) {
                    best = tmp;
                }
                if (Math.pow(n.key.y() - query.y(), 2) < best.distanceSquaredTo(query)) {
                    tmp = nearest(n.right, query);
                    if (tmp != null && tmp.distanceSquaredTo(query) < best.distanceSquaredTo(
                            query)) {
                        best = tmp;
                    }
                }
            }
            else {
                tmp = nearest(n.right, query);
                if (tmp != null && tmp.distanceSquaredTo(query) < best.distanceSquaredTo(query)) {
                    best = tmp;
                }
                if (Math.pow(query.y() - n.key.y(), 2) < best.distanceSquaredTo(query)) {
                    tmp = nearest(n.left, query);
                    if (tmp != null && tmp.distanceSquaredTo(query) < best.distanceSquaredTo(
                            query)) {
                        best = tmp;
                    }
                }
            }
        }
        return best;
    }

    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null) {
            throw new IllegalArgumentException();
        }
        ArrayList<Point2D> contained = new ArrayList<>();
        range(rect, root, contained);
        return contained;
    }

    private void range(RectHV rect, Node node, ArrayList<Point2D> contained) {
        if (node == null) {
            return;
        }
        if (rect.contains(node.key)) {
            contained.add(node.key);
        }
        if (node.height % 2 == 0) {
            if (node.key.x() >= rect.xmin()) {
                range(rect, node.left, contained);
            }
            if (node.key.x() <= rect.xmax()) {
                range(rect, node.right, contained);
            }
        }
        else {
            if (node.key.y() >= rect.ymin()) {
                range(rect, node.left, contained);
            }
            if (node.key.y() <= rect.ymax()) {
                range(rect, node.right, contained);
            }
        }
    }

    public void draw() {
        Stack<Node> stack = new Stack<>();
        Node node = root;
        while (node != null || !stack.isEmpty()) {
            while (node != null) {
                drawOne(node);
                stack.push(node);
                node = node.left;
            }
            if (!stack.isEmpty()) {
                node = stack.pop().right;
            }
        }
    }

    private void drawOne(Node node) {
        if (node == null) {
            return;
        }
        Node parent = node.parent;
        if (parent == null) {
            redLine();
            StdDraw.line(node.key.x(), 0, node.key.x(), 1);
        }
        else {
            if (node.height % 2 == 0) {
                redLine();
                if (node.key.y() > parent.key.y()) {
                    StdDraw.line(node.key.x(), parent.key.y(), node.key.x(), 1);
                }
                else {
                    StdDraw.line(node.key.x(), 0, node.key.x(), parent.key.y());
                }
            }
            else {
                blueLine();
                if (node.key.x() > parent.key.x()) {
                    StdDraw.line(parent.key.x(), node.key.y(), 1, node.key.y());
                }
                else {
                    StdDraw.line(0, node.key.y(), parent.key.x(), node.key.y());
                }
            }
        }
        point();
        node.key.draw();
    }

    private void redLine() {
        StdDraw.setPenRadius(0.005);
        StdDraw.setPenColor(StdDraw.RED);
    }

    private void blueLine() {
        StdDraw.setPenRadius(0.005);
        StdDraw.setPenColor(StdDraw.BLUE);
    }

    private void point() {
        StdDraw.setPenRadius(0.025);
        StdDraw.setPenColor(StdDraw.BLACK);
    }

    public static void main(String[] args) {
        KdTree kt = new KdTree();
        kt.insert(new Point2D(0.7, 0.2));
        kt.insert(new Point2D(0.5, 0.4));
        kt.insert(new Point2D(0.9, 0.6));
        kt.insert(new Point2D(0.2, 0.3));
        kt.insert(new Point2D(0.4, 0.7));
        kt.insert(new Point2D(0.3, 0.7));
        kt.draw();
        RectHV r = new RectHV(0, 0, 1, 0.5);
        Iterator<Point2D> it = kt.range(r).iterator();
        while (it.hasNext()) {
            Point2D p = it.next();
            System.out.println(p.toString());
        }
        System.out.println(kt.nearest(new Point2D(0.2, 0.31)));
    }
}
