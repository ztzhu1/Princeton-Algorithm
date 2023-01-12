#ifndef SHORTEST_PATH_H
#define SHORTEST_PATH_H

#include <cstddef>
#include <queue>
#include <vector>
#include <utility>
#include <map>

class DirectedEdge {

public:
    DirectedEdge(int v, int w, double weight);
    int from();
    int to();
    double getWeight();

private:
    int v;
    int w;
    double weight;
};

class EdgeWeightedDigraph {

public:
    explicit EdgeWeightedDigraph(int V);
    EdgeWeightedDigraph(EdgeWeightedDigraph &G) = default;
    void addEdge(DirectedEdge e);
    std::vector<DirectedEdge *> adj(int v);
    size_t V() const;
    size_t E() const;

private:
    std::vector<std::vector<DirectedEdge>> adjList;
    std::vector<DirectedEdge> edgeList;
};

class SP {

public:
    SP(EdgeWeightedDigraph &G, int source);
    double getDistTo(int v);
    std::vector<DirectedEdge *> getPathTo(int v);
    DirectedEdge *lastEdgeTo(int v);
    void relax(DirectedEdge &e);

private:
    EdgeWeightedDigraph G;
    int source;
    std::vector<double> distTo;
    std::vector<DirectedEdge> edgeTo;
};

class DijkstraSP {

public:
    DijkstraSP() = delete;
    DijkstraSP(EdgeWeightedDigraph &G, int source);
    void relax(DirectedEdge &e);

private:
    EdgeWeightedDigraph G;
    int source;
    std::vector<double> distTo;
    std::vector<DirectedEdge> edgeTo;
    std::multimap<double, int> pq;
};

#endif // SHORTEST_PATH_H