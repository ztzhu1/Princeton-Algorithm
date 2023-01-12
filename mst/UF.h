#ifndef UF_H
#define UF_H

#include <vector>

class UF {

public:
    UF() = delete;
    explicit UF(int N);
    bool connected(int i, int j);
    void doUnion(int i, int j);

private:
    std::vector<int> id;
    int root(int i);
};

#endif // UF_H
