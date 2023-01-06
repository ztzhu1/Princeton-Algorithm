/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import java.util.ArrayList;
import java.util.Iterator;

public class Board {


    private int[][] tiles;

    // create a board from an n-by-n array of tiles,
    // where tiles[row][col] = tile at (row, col)
    public Board(int[][] tiles) {
        this.tiles = new int[tiles.length][tiles.length];
        for (int i = 0; i < tiles.length; ++i) {
            this.tiles[i] = tiles[i].clone();
        }
    }

    // string representation of this board
    public String toString() {
        String ret = Integer.toString(tiles.length) + "\n";
        for (int i = 0; i < tiles.length; ++i) {
            for (int j = 0; j < tiles.length; ++j) {
                ret += Integer.toString(tiles[i][j]);
                if (j != tiles.length - 1) {
                    ret += " ";
                }
            }
            if (i != tiles.length - 1) {
                ret += "\n";
            }
        }
        return ret;
    }

    // board dimension n
    public int dimension() {
        return tiles.length;
    }

    // number of tiles out of place
    public int hamming() {
        int n = dimension();
        int s = 0;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (tiles[i][j] != 0 && tiles[i][j] != i * n + j + 1) {
                    ++s;
                }
            }
        }
        return s;
    }

    // sum of Manhattan distances between tiles and goal
    public int manhattan() {
        int s = 0;
        int n = dimension();
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (tiles[i][j] != 0 && tiles[i][j] != i * n + j + 1) {
                    int row = Math.floorDiv(tiles[i][j] - 1, n);
                    int col = Math.floorMod(tiles[i][j] - 1, n);
                    s = s + Math.abs(row - i) + Math.abs(col - j);
                }
            }
        }
        return s;
    }

    // is this board the goal board?
    public boolean isGoal() {
        return hamming() == 0;
    }

    // does this board equal y?
    public boolean equals(Object y) {
        if (this == y) {
            return true;
        }
        if (y == null) {
            return false;
        }
        if (this.getClass() != y.getClass()) {
            return false;
        }
        Board that = (Board) y;
        int n = dimension();
        if (n != that.dimension()) {
            return false;
        }
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (tiles[i][j] != that.tiles[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    // all neighboring boards
    public Iterable<Board> neighbors() {
        ArrayList<Board> nei = new ArrayList<>();
        int[][] tiles = new int[this.tiles.length][this.tiles.length];
        for (int i = 0; i < this.tiles.length; ++i) {
            tiles[i] = this.tiles[i].clone();
        }
        int n = tiles.length;
        int rowZero = 0;
        int colZero = 0;
        boolean found = false;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (tiles[i][j] == 0) {
                    found = true;
                    rowZero = i;
                    colZero = j;
                    break;
                }
            }
            if (found) {
                break;
            }
        }

        if (rowZero != 0) {
            tiles[rowZero][colZero] = tiles[rowZero - 1][colZero];
            tiles[rowZero - 1][colZero] = 0;
            nei.add(new Board(tiles));
            tiles[rowZero - 1][colZero] = tiles[rowZero][colZero];
            tiles[rowZero][colZero] = 0;
        }
        if (rowZero != tiles.length - 1) {
            tiles[rowZero][colZero] = tiles[rowZero + 1][colZero];
            tiles[rowZero + 1][colZero] = 0;
            nei.add(new Board(tiles));
            tiles[rowZero + 1][colZero] = tiles[rowZero][colZero];
            tiles[rowZero][colZero] = 0;
        }
        if (colZero != 0) {
            tiles[rowZero][colZero] = tiles[rowZero][colZero - 1];
            tiles[rowZero][colZero - 1] = 0;
            nei.add(new Board(tiles));
            tiles[rowZero][colZero - 1] = tiles[rowZero][colZero];
            tiles[rowZero][colZero] = 0;
        }
        if (colZero != tiles.length - 1) {
            tiles[rowZero][colZero] = tiles[rowZero][colZero + 1];
            tiles[rowZero][colZero + 1] = 0;
            nei.add(new Board(tiles));
            tiles[rowZero][colZero + 1] = tiles[rowZero][colZero];
            tiles[rowZero][colZero] = 0;
        }
        return nei;
    }

    // a board that is obtained by exchanging any pair of tiles
    public Board twin() {
        int[][] tiles = new int[this.tiles.length][this.tiles.length];
        for (int i = 0; i < tiles.length; ++i) {
            tiles[i] = this.tiles[i].clone();
        }
        boolean found = false;
        int i = 0;
        int j = 0;
        for (i = 0; i < tiles.length; ++i) {
            for (j = 0; j < tiles.length; ++j) {
                if (tiles[i][j] == 0) {
                    found = true;
                    break;
                }
            }
            if (found) {
                break;
            }
        }
        if (tiles[0][0] != 0 && tiles[0][1] != 0) {
            int tmp = tiles[0][0];
            tiles[0][0] = tiles[0][1];
            tiles[0][1] = tmp;
        }
        else {
            int tmp = tiles[1][0];
            tiles[1][0] = tiles[1][1];
            tiles[1][1] = tmp;
        }
        return new Board(tiles);
    }

    public static void main(String[] args) {
        int[][] t = new int[2][2];
        t[0][0] = 0;
        t[0][1] = 3;
        t[1][0] = 2;
        t[1][1] = 1;
        Board b = new Board(t);
        System.out.println(b);
        System.out.println(b.isGoal());
        System.out.println(b.hamming());
        System.out.println(b.manhattan());
        System.out.println(b.twin());

        Iterator<Board> it = b.neighbors().iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }
}
