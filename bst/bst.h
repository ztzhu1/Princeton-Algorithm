#ifndef BST_H
#define BST_H

#include <memory>
#include <optional>

struct Node {
    int key;
    std::weak_ptr<Node> parent;
    std::shared_ptr<Node> left;
    std::shared_ptr<Node> right;
    Node() = default;
    Node(int key);
    ~Node();
};

class BST {

public:
    void insert(int key);
    int depth();
    std::optional<int> get(int key);
    void remove(int key);
    void removeMin();
    void display();

private:
    std::shared_ptr<Node> root;

    static void insert(std::shared_ptr<Node> &root, const std::weak_ptr<Node> &parent,int key);
    static int depth(std::shared_ptr<Node> &root);
    static std::shared_ptr<Node> remove(std::shared_ptr<Node> node, int key);
    static std::shared_ptr<Node> removeMin(std::shared_ptr<Node> node);
    static std::shared_ptr<Node> minNode(std::shared_ptr<Node> node);
    static std::optional<int> get(std::shared_ptr<Node> &root, int key);
};


#endif // BST_H