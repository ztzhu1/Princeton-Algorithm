#include "mst.h"

#include "UF.h"

#include <cassert>
#include <fmt/core.h>
#include <queue>

using namespace std;

Edge::Edge(int v, int w, double weight)
    : v(v)
    , w(w)
    , weight(weight) {}

int Edge::either() const {
    return v;
}

int Edge::other(int v) const {
    if (v == this->v)
        return this->w;
    else if (v == this->w)
        return this->v;
    else
        assert(false);
}

int Edge::compareTo(Edge &that) const {
    if (this->weight < that.weight)
        return -1;
    else if (this->weight > that.weight)
        return 1;
    else
        return 0;
}

double Edge::getWeight() const {
    return this->weight;
}

EdgeWeightedGraph::EdgeWeightedGraph(int V) {
    adjList.resize(V);
}

int EdgeWeightedGraph::V() {
    return adjList.size();
}

void EdgeWeightedGraph::addEdge(const Edge &e) {
    int v = e.either();
    int w = e.other(v);
    adjList[v].push_back(e);
    adjList[w].push_back(e);
    edgeList.push_back(e);
}

edgeItPair EdgeWeightedGraph::adj(int v) {
    return make_pair<edgeIt, edgeIt>(adjList[v].begin(), adjList[v].end());
}

edgeItPair EdgeWeightedGraph::edges() {
    return make_pair<edgeIt, edgeIt>(edgeList.begin(), edgeList.end());
}

KruskalMST::KruskalMST(const EdgeWeightedGraph &G)
    : G(G) {

    auto cmp = [](Edge *left, Edge *right) {
        return left->getWeight() < right->getWeight();
    };
    priority_queue<Edge *, vector<Edge *>, decltype(cmp)> pq(cmp);

    auto itPair = this->G.edges();
    for (auto it = itPair.first; it != itPair.second; it++) {
        pq.push(&*it);
    }

    UF uf(this->G.V());
    while (pq.size() != 0 && (int)mst.size() < this->G.V() - 1) {
        auto e = pq.top();
        pq.pop();
        int v = e->either();
        int w = e->other(v);
        if (!uf.connected(v, w)) {
            uf.doUnion(v, w);
            mst.push_back(e);
        }
    }
}

vector<Edge *> KruskalMST::edges() {
    return mst;
}
