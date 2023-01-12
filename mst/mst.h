#ifndef MST_H
#define MST_H

#include <memory>
#include <optional>
#include <utility>
#include <vector>

class Edge {

public:
    Edge() = delete;
    Edge(int v, int w, double weight);
    int either() const;
    int other(int v) const;
    int compareTo(Edge &that) const;
    double getWeight() const;

private:
    int v;
    int w;
    double weight;
};

typedef std::vector<Edge>::iterator edgeIt;
typedef typename std::pair<edgeIt, edgeIt> edgeItPair;

class EdgeWeightedGraph {

private:
    std::vector<std::vector<Edge>> adjList;
    std::vector<Edge> edgeList;

public:
    EdgeWeightedGraph() = delete;
    explicit EdgeWeightedGraph(int V);
    void addEdge(const Edge &e);
    edgeItPair adj(int v);
    edgeItPair edges();
    int V();

};

class KruskalMST {

public:
    explicit KruskalMST(const EdgeWeightedGraph &G);
    std::vector<Edge *> edges();

private:
    std::vector<Edge *> mst;
    EdgeWeightedGraph G;
};

#endif // MST_H