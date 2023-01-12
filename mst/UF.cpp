#include "UF.h"

UF::UF(int N)
    : id(N) {

    for (int i = 0; i < N; i++)
        id[i] = i;
}

int UF::root(int i) {
    while (id[i] != i)
        i = id[i];
    return i;
}

bool UF::connected(int i, int j) {
    return root(i) == root(j);
}

void UF::doUnion(int i, int j) {
    id[root(i)] = root(j);
}
