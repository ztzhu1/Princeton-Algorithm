#ifndef BST_H
#define BST_H

#include <memory>
#include <optional>

struct Node {
    int key;
    int value{0};
    std::shared_ptr<Node> left;
    std::shared_ptr<Node> right;
    bool color{RED};

    enum Color {
        RED   = true,
        BLACK = false,
    };

    Node() = default;
    Node(int key);
    ~Node();
};

class LLRB {

public:
    int depth();
    std::optional<int> get(int key);
    void put(int key, int value);
    void display();

private:
    std::shared_ptr<Node> root;

    static std::shared_ptr<Node> put(std::shared_ptr<Node> node,
                                     int key,
                                     int value);
    static int depth(std::shared_ptr<Node> node);
    static std::optional<int> get(std::shared_ptr<Node> node, int key);
    static bool isRed(std::shared_ptr<Node> node);
    static bool isBlack(std::shared_ptr<Node> node) { return !isRed(node); }
    static std::shared_ptr<Node> rotateLeft(std::shared_ptr<Node> node);
    static std::shared_ptr<Node> rotateRight(std::shared_ptr<Node> node);
    static void flipColors(std::shared_ptr<Node> node);
};

#endif // BST_H