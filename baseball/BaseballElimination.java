/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.FlowEdge;
import edu.princeton.cs.algs4.FlowNetwork;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.TreeMap;

public class BaseballElimination {
    private TreeMap<String, Integer> teams;
    private int[][] g;
    private int[] w;
    private int[] l;
    private int[] r;
    private FlowNetwork fn;
    private FlowEdge[] edgeTo;
    private boolean[] marked;
    private double value;

    public BaseballElimination(String filename) {
        if (filename == null)
            throw new IllegalArgumentException();
        In in = new In(filename);
        int numTeams = Integer.parseInt(in.readLine());
        g = new int[numTeams][numTeams];
        w = new int[numTeams];
        l = new int[numTeams];
        r = new int[numTeams];

        teams = new TreeMap<>();
        int idx = 0;
        while (in.hasNextLine()) {
            String[] s = in.readLine().split(" +");
            if (teams.containsKey(s[0]))
                throw new IllegalArgumentException();

            teams.put(s[0], idx);
            w[idx] = Integer.parseInt(s[1]);
            l[idx] = Integer.parseInt(s[2]);
            r[idx] = Integer.parseInt(s[3]);
            for (int i = 0; i < numTeams; i++) {
                g[idx][i] = Integer.parseInt(s[4 + i]);
            }
            idx++;
        }
    }

    public int numberOfTeams() {
        return teams.size();
    }

    public Iterable<String> teams() {
        return teams.keySet();
    }

    public int wins(String team) {
        if (team == null || !teams.containsKey(team))
            throw new IllegalArgumentException();

        return w[teams.get(team)];
    }

    public int losses(String team) {
        if (team == null || !teams.containsKey(team))
            throw new IllegalArgumentException();

        return l[teams.get(team)];
    }

    public int remaining(String team) {
        if (team == null || !teams.containsKey(team))
            throw new IllegalArgumentException();

        return r[teams.get(team)];
    }

    public int against(String team1, String team2) {
        if (team1 == null || !teams.containsKey(team1) || team2 == null || !teams.containsKey(
                team2) || team1.equals(team2))
            throw new IllegalArgumentException();

        return g[teams.get(team1)][teams.get(team2)];
    }

    public boolean isEliminated(String team) {
        if (team == null || !teams.containsKey(team))
            throw new IllegalArgumentException();

        int given = teams.get(team);
        FordFulkerson(given);

        for (FlowEdge e : fn.adj(0)) {
            if (e.residualCapacityTo(e.other(0)) != 0)
                return true;
        }
        return false;
    }

    public Iterable<String> certificateOfElimination(String team) {
        if (!isEliminated(team))
            return null;

        ArrayList<String> c = new ArrayList<>();
        int n = numberOfTeams();
        int given = teams.get(team);
        for (FlowEdge e : fn.adj(0)) {
            if (e.residualCapacityTo(e.other(0)) != 0) {
                // for (String k : teams.keySet()) {
                //     if (teams.get(k) == i || teams.get(k) == j) {
                //         if (!teams.containsKey(k))
                //             c.add(k);
                //     }
            }
        }
        return c;
    }

    private void FordFulkerson(int given) {
        initFN(given);
        int s = sourceIdx();
        int t = targetIdx();
        value = 0.0;
        while (hasArgumentingPath()) {
            double bottle = Double.MAX_VALUE;
            for (int v = t; v != s; v = edgeTo[v].other(v))
                bottle = Math.min(bottle, edgeTo[v].residualCapacityTo(v));
            for (int v = t; v != s; v = edgeTo[v].other(v))
                edgeTo[v].addResidualFlowTo(v, bottle);

            value += bottle;
        }
    }

    private boolean hasArgumentingPath() {
        int V = fn.V();
        int s = sourceIdx();
        int t = targetIdx();
        edgeTo = new FlowEdge[V];
        marked = new boolean[V];
        Queue<Integer> q = new Queue<>();
        q.enqueue(s);
        marked[s] = true;
        while (!q.isEmpty()) {
            int v = q.dequeue();

            for (FlowEdge e : fn.adj(v)) {
                int w = e.other(v);
                if (e.residualCapacityTo(w) > 0 && !marked[w]) {
                    edgeTo[w] = e;
                    marked[w] = true;
                    q.enqueue(w);
                }
            }
        }
        return marked[t];
    }

    private void initFN(int given) {
        int V = numberOfTeams() - 1;
        V = V * (V - 1) / 2 + V + 2;
        fn = new FlowNetwork(V);
        for (int i = 0; i < numberOfTeams() - 1; i++) {
            for (int j = i + 1; j < numberOfTeams(); j++) {
                if (i == given || j == given)
                    continue;

                int team1 = idx(i, given);
                int team2 = idx(j, given);
                FlowEdge e = new FlowEdge(sourceIdx(), pairIdx(team1, team2), (double) g[i][j]);
                fn.addEdge(e);
                e = new FlowEdge(pairIdx(team1, team2), teamIdx(team1), Double.MAX_VALUE);
                fn.addEdge(e);
                e = new FlowEdge(pairIdx(team1, team2), teamIdx(team2), Double.MAX_VALUE);
                fn.addEdge(e);
            }
        }
        for (int i = 0; i < numberOfTeams(); i++) {
            if (i == given)
                continue;

            int team1 = idx(i, given);
            FlowEdge e = new FlowEdge(teamIdx(team1), targetIdx(),
                                      (double) (w[given] + r[given] - w[i]));
            fn.addEdge(e);
        }
    }

    private int idx(int team, int given) {
        if (team == given)
            throw new IllegalArgumentException();

        if (team < given)
            return team;
        else
            return team - 1;
    }

    private int sourceIdx() {
        return 0;
    }

    private int targetIdx() {
        return fn.V() - 1;
    }

    private int pairIdx(int team1, int team2) {
        if (team1 < 0 || team2 < 0 || team1 == team2)
            throw new IllegalArgumentException();
        int tmpMin = Math.min(team1, team2);
        int tmpMax = Math.max(team1, team2);
        team1 = tmpMin;
        team2 = tmpMax;
        int n = numberOfTeams() - 1;
        return team1 * (2 * n - 1 - team1) / 2 + team2 - team1;
    }

    private int teamIdx(int team) {
        int V = numberOfTeams() - 1;
        V = V * (V - 1) / 2;
        return 1 + V + team;
    }

    public static void main(String[] args) {
        BaseballElimination division = new BaseballElimination(args[0]);
        // division.FordFulkerson(4);
        for (String team : division.teams()) {
            if (division.isEliminated(team)) {
                StdOut.print(team + " is eliminated by the subset R = { ");
                for (String t : division.certificateOfElimination(team)) {
                    StdOut.print(t + " ");
                }
                StdOut.println("}");
            }
            else {
                StdOut.println(team + " is not eliminated");
            }
        }
    }
}
