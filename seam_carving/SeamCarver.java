/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.IndexMinPQ;
import edu.princeton.cs.algs4.Picture;

import java.awt.Color;

public class SeamCarver {

    private Picture picture;

    // create a seam carver object based on the given picture
    public SeamCarver(Picture picture) {
        if (picture == null)
            throw new IllegalArgumentException();

        this.picture = new Picture(picture);
    }

    // current picture
    public Picture picture() {
        return this.picture;
    }

    // width of current picture
    public int width() {
        return picture.width();
    }

    // height of current picture
    public int height() {
        return picture.height();
    }

    // energy of pixel at column x and row y
    public double energy(int x, int y) {
        if (x < 0 || x >= width() || y < 0 || y >= height())
            throw new IllegalArgumentException();
        if (x == 0 || x == width() || y == 0 || y >= height())
            return 1000.0;

        Color rgbLeft = picture.get(x - 1, y);
        Color rgbRight = picture.get(x + 1, y);
        Color rgbUp = picture.get(x, y - 1);
        Color rgbDown = picture.get(x, y - 1);
        double dxSquare = Math.pow((rgbRight.getRed() - rgbLeft.getRed()), 2.0)
                + Math.pow((rgbRight.getGreen() - rgbLeft.getGreen()), 2.0)
                + Math.pow((rgbRight.getBlue() - rgbLeft.getBlue()), 2.0);
        double dySquare = Math.pow((rgbUp.getRed() - rgbDown.getRed()), 2.0)
                + Math.pow((rgbUp.getGreen() - rgbDown.getGreen()), 2.0)
                + Math.pow((rgbUp.getBlue() - rgbDown.getBlue()), 2.0);
        return Math.sqrt(dxSquare + dySquare);
    }

    // sequence of indices for horizontal seam
    public int[] findVerticalSeam() {
        int w = width();
        int h = height();
        double[][] distTo = new double[h][w];
        int[][] edgeTo = new int[h][w];
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (i == 0)
                    distTo[i][j] = 1000;
                else
                    distTo[i][j] = Double.MAX_VALUE;
            }
        }

        IndexMinPQ<Double> pq = new IndexMinPQ<>(h * w);
        for (int j = 0; j < w; j++)
            pq.insert(index(0, j), distTo[0][j]);
        while (!pq.isEmpty()) {
            int idx = pq.delMin();
            relax(idx / w + 1, idx % w - 1, distTo, edgeTo, pq);
            relax(idx / w + 1, idx % w, distTo, edgeTo, pq);
            relax(idx / w + 1, idx % w + 1, distTo, edgeTo, pq);
        }

        int[] path = new int[h];
        int pos = 0;
        double val = distTo[h - 1][0];
        for (int i = 1; i < w; i++) {
            if (distTo[h - 1][i] < val) {
                val = distTo[h - 1][i];
                pos = i;
            }
        }
        for (int i = h - 1; i >= 0; i--) {
            path[i] = pos;
            pos = edgeTo[i][pos];
            pos = pos % w;
        }
        return path;
    }

    // sequence of indices for horizontal seam
    public int[] findHorizontalSeam() {
        int w = width();
        int h = height();
        Picture rotated = new Picture(h, w);
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                rotated.set(j, i, this.picture.get(i, j));
            }
        }
        Picture original = this.picture;
        this.picture = rotated;
        int[] path = findVerticalSeam();
        this.picture = original;
        return path;
    }

    private void relax(int i, int j, double[][] distTo, int[][] edgeTo, IndexMinPQ<Double> pq) {
        if (i <= 0 || i >= height() || j < 0 || j >= width())
            return;

        if (distTo[i][j] > distTo[i - 1][j] + energy(i, j)) {
            distTo[i][j] = distTo[i - 1][j] + energy(i, j);
            edgeTo[i][j] = index(i - 1, j);
            if (pq.contains(index(i, j)))
                pq.decreaseKey(index(i, j), distTo[i][j]);
            else
                pq.insert(index(i, j), distTo[i][j]);
        }
        if (j > 0 && distTo[i][j] > distTo[i][j - 1] + energy(i, j)) {
            distTo[i][j] = distTo[i][j - 1] + energy(i, j);
            edgeTo[i][j] = index(i, j - 1);
            if (pq.contains(index(i, j - 1)))
                pq.decreaseKey(index(i, j - 1), distTo[i][j - 1]);
            else
                pq.insert(index(i, j - 1), distTo[i][j - 1]);
        }
        if (j < picture.width() - 1 && distTo[i][j] > distTo[i][j + 1] + energy(i, j)) {
            distTo[i][j] = distTo[i][j + 1] + energy(i, j);
            edgeTo[i][j] = index(i, j + 1);
            if (pq.contains(index(i, j + 1)))
                pq.decreaseKey(index(i, j + 1), distTo[i][j + 1]);
            else
                pq.insert(index(i, j + 1), distTo[i][j + 1]);
        }
    }

    private int index(int i, int j) {
        return i * picture.width() + j;
    }

    // remove horizontal seam from current picture
    public void removeHorizontalSeam(int[] seam) {
        int w = width();
        int h = height();
        int[] path = findHorizontalSeam();
        Picture p = new Picture(w, h - 1);
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (i < path[j])
                    p.set(i, j, picture.get(i, j));
                else if (i > path[j])
                    p.set(i - 1, j, picture.get(i, j));
            }
        }
        this.picture = p;
    }

    // remove vertical seam from current picture
    public void removeVerticalSeam(int[] seam) {
        int w = width();
        int h = height();
        int[] path = findHorizontalSeam();
        Picture p = new Picture(w, h - 1);
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (i < path[j])
                    p.set(i, j, picture.get(i, j));
                else if (i > path[j])
                    p.set(i - 1, j, picture.get(i, j));
            }
        }
        this.picture = p;
    }

    public static void main(String[] args) {
        Picture p = SCUtility.randomPicture(10, 10);
        SeamCarver s = new SeamCarver(p);
        System.out.println(s.energy(0, 0));
        System.out.println(s.energy(9, 0));
    }
}
