/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

public class Solver {

    private class Node {
        public Board b;
        public int move = 0;
        public Node parent = null;

        public Node(Board b) {
            this.b = b;
        }

        public int priority() {
            return b.manhattan() + move;
        }
    }

    private int move = 0;

    private class BoardComparator implements Comparator<Node> {
        public int compare(Node o1, Node o2) {
            return o1.priority() - o2.priority();
        }
    }


    private Board initial;

    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial) {
        if (initial == null) {
            throw new IllegalArgumentException();
        }
        this.initial = initial;
    }

    // is the initial board solvable? (see below)
    public boolean isSolvable() {
        return moves() != -1;
    }

    // min number of moves to solve initial board; -1 if unsolvable
    public int moves() {
        solution();
        return move;
    }

    // sequence of boards in a shortest solution; null if unsolvable
    public Iterable<Board> solution() {
        move = -1;
        ArrayList<Board> used = new ArrayList<>();
        Stack<Board> sol = new Stack<>();
        MinPQ<Node> pq = new MinPQ<>(new BoardComparator());
        pq.insert(new Node(initial));
        while (pq.size() != 0) {
            Node n = pq.delMin();
            used.add(n.b);
            if (n.b.isGoal()) {
                move = n.move;
                while (n != null) {
                    sol.push(n.b);
                    n = n.parent;
                }
                break;
            }
            Iterator<Board> it = n.b.neighbors().iterator();
            while (it.hasNext()) {
                Node tmp = new Node(it.next());
                boolean found = false;
                for (int i = 0; i < used.size(); ++i) {
                    if (used.get(i).equals(tmp.b)) {
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    tmp.move = n.move + 1;
                    tmp.parent = n;
                    pq.insert(tmp);
                }
            }
        }
        if (move != -1) {
            return sol;
        }
        return null;
    }

    public static void main(String[] args) {
        int[][] t = new int[2][2];
        t[0][0] = 0;
        t[0][1] = 3;
        t[1][0] = 2;
        t[1][1] = 1;
        Board b = new Board(t);
        Solver s = new Solver(b);
        System.out.println(s.isSolvable());
        System.out.println(s.moves());
        Iterator<Board> a = s.solution().iterator();
        while (a.hasNext()) {
            System.out.println(a.next());
        }
    }
}
