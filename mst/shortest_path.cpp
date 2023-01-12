#include "shortest_path.h"

#include <algorithm>
#include <cassert>
#include <limits>

using namespace std;

DirectedEdge::DirectedEdge(int v, int w, double weight)
    : v(v)
    , w(w)
    , weight(weight) {}

int DirectedEdge::from() {
    return v;
}

int DirectedEdge::to() {
    return w;
}

double DirectedEdge::getWeight() {
    return weight;
}

EdgeWeightedDigraph::EdgeWeightedDigraph(int V)
    : adjList(V) {}

void EdgeWeightedDigraph::addEdge(DirectedEdge e) {
    int v = e.from();
    adjList[v].push_back(e);
    edgeList.push_back(e);
}

vector<DirectedEdge *> EdgeWeightedDigraph::adj(int v) {
    vector<DirectedEdge *> l;
    for (auto &i : adjList[v]) {
        l.push_back(&i);
    }
    return l;
}

size_t EdgeWeightedDigraph::V() const {
    return adjList.size();
}

size_t EdgeWeightedDigraph::E() const {
    return edgeList.size();
}

SP::SP(EdgeWeightedDigraph &G, int source)
    : G(G)
    , source(source)
    , distTo(G.V())
    , edgeTo(G.V(), DirectedEdge{0, 0, 0.0}) {

    for (auto &i : distTo) {
        i = numeric_limits<int>::max();
    }
    distTo[source] = 0;
}

double SP::getDistTo(int v) {
    return distTo[v];
}

vector<DirectedEdge *> SP::getPathTo(int v) {
    assert(v != source);
    vector<DirectedEdge *> ret;
    for (auto e = lastEdgeTo(v); v != source; e = lastEdgeTo(v)) {
        ret.push_back(e);
        v = e->from();
    }

    reverse(ret.begin(), ret.end());
    return ret;
}

DirectedEdge *SP::lastEdgeTo(int v) {
    return &edgeTo[v];
}

/**
 * DP
 */
void SP::relax(DirectedEdge &e) {
    int v = e.from();
    int w = e.to();
    if (distTo[w] > distTo[v] + e.getWeight()) {
        distTo[w] = distTo[v] + e.getWeight();
        edgeTo[w] = e;
    }
}

DijkstraSP::DijkstraSP(EdgeWeightedDigraph &G, int source)
    : G(G)
    , source(source)
    , distTo(G.V())
    , edgeTo(G.V(), DirectedEdge{0, 0, 0.0}) {

    for (auto &i : distTo) {
        i = numeric_limits<int>::max();
    }
    distTo[source] = 0;

    pq.insert(pair<double, int>(0.0, 0));
    while (!pq.empty()) {
        auto it = pq.begin();
        int v = it->second;
        pq.erase(it);
        for (auto e : this->G.adj(v)) {
            relax(*e);
        }
    }
}

void DijkstraSP::relax(DirectedEdge &e) {
    int v = e.from();
    int w = e.to();
    if (distTo[w] > distTo[v] + e.getWeight()) {
        distTo[w] = distTo[v] + e.getWeight();
        edgeTo[w] = e;
        for (auto it = pq.begin(); it != pq.end(); it++) {
            if (it->second == w) {
                pq.erase(it);
                break;
            }
        }
        pq.insert(pair<double, int>(distTo[w], w));
    }
}
