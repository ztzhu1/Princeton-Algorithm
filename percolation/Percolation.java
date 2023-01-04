import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int n;
    private boolean[][] grid;
    private final int root;
    private final int leaf;
    private WeightedQuickUnionUF gridUF;
    private int numOpen = 0;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n < 1) {
            throw new IllegalArgumentException();
        }
        this.n = n;
        this.root = n * n;
        this.leaf = n * n + 1;
        this.gridUF = new WeightedQuickUnionUF(n * n + 2);
        this.grid = new boolean[n][n];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                grid[i][j] = false;
            }
        }
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (row < 1 || row > this.n || col < 1 || col > this.n) {
            throw new IllegalArgumentException();
        }

        if (!isOpen(row, col)) {
            if (row == 1) {
                gridUF.union(root, map(row, col));
            }
            this.grid[row - 1][col - 1] = true;
            numOpen++;
        } else {
            return;
        }

        if (row < n && isOpen(row + 1, col)) {
            gridUF.union(map(row, col), map(row + 1, col));
        }
        if (col < n && isOpen(row, col + 1)) {
            gridUF.union(map(row, col), map(row, col + 1));
        }
        if (row > 1 && isOpen(row - 1, col)) {
            gridUF.union(map(row, col), map(row - 1, col));
        }
        if (col > 1 && isOpen(row, col - 1)) {
            gridUF.union(map(row, col), map(row, col - 1));
        }

        for (int i = 1; i <= n; i++) {
            if (isFull(n, i)) {
                gridUF.union(leaf, map(n, i));
            }
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (row < 1 || row > this.n || col < 1 || col > this.n) {
            throw new IllegalArgumentException();
        }
        return this.grid[row - 1][col - 1];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if (row < 1 || row > this.n || col < 1 || col > this.n) {
            throw new IllegalArgumentException();
        }
        if (!isOpen(row, col)) {
            return false;
        }
        if (gridUF.find(map(row, col)) == gridUF.find(root)) {
            return true;
        }
        return false;
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return numOpen;
    }

    // does the system percolate?
    public boolean percolates() {
        return gridUF.find(root) == gridUF.find(leaf);
    }

    private int map(int row, int col) {
        return (row - 1) * this.n + (col - 1);
    }

    public static void main(String[] args) {
        int n = 3;
        Percolation p = new Percolation(n);
        p.open(1, 3);
        p.open(2, 3);
        p.open(3, 3);
        p.open(3, 1);
        p.open(2, 1);
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                System.out.printf("%b ", p.isFull(i, j));
            }
            System.out.printf("\n");
        }
        System.out.println(p.percolates());
    }
}
