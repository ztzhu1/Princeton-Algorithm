#include "llrb.h"

#include <assert.h>
#include <fmt/core.h>
#include <queue>

using namespace std;

Node::Node(int key)
    : key(key) {}

Node::~Node() {
    fmt::print("*\n");
}

optional<int> LLRB::get(int key) {
    return get(root, key);
}

optional<int> LLRB::get(std::shared_ptr<Node> node, int key) {
    if (node == nullptr)
        return nullopt;
    else if (node->key == key)
        return node->value;
    else if (key < node->key)
        return get(node->left, key);
    else
        return get(node->right, key);
}

void LLRB::put(int key, int value) {
    root = put(root, key, value);
    if (isRed(root))
        root->color = Node::Color::BLACK;
}

shared_ptr<Node> LLRB::put(shared_ptr<Node> node, int key, int value) {
    if (node == nullptr)
        return make_shared<Node>(key);

    if (key < node->key)
        node->left = put(node->left, key, value);
    else if (key > node->key)
        node->right = put(node->right, key, value);
    else
        node->value = value;

    if (isBlack(node->left) && isRed(node->right))
        node = rotateLeft(node);
    if (isRed(node->left) && isRed(node->left->left))
        node = rotateRight(node);
    if (isRed(node->left) && isRed(node->right))
        flipColors(node);

    return node;
}

int LLRB::depth() {
    return depth(root);
}

int LLRB::depth(std::shared_ptr<Node> node) {
    if (node == nullptr)
        return 0;
    else
        return 1 + max(depth(node->left), depth(node->right));
}

bool LLRB::isRed(shared_ptr<Node> node) {
    if (node == nullptr)
        return false;
    return node->color == Node::Color::RED;
}

/**
 *    node            x
 *        \          /
 *         x   ->  node
 *         /          \
 *      xleft       xleft
 */
shared_ptr<Node> LLRB::rotateLeft(shared_ptr<Node> node) {
    auto x = node->right;
    assert(isRed(x));

    node->right = x->left;
    x->left     = node;
    x->color    = node->color;
    node->color = Node::Color::RED;
    return x;
}

/**
 *    node            x
 *    /                \
 *   x        ->      node
 *    \                /
 *   xright         xright
 */
shared_ptr<Node> LLRB::rotateRight(shared_ptr<Node> node) {
    auto x = node->left;
    assert(isRed(x));

    node->left  = x->right;
    x->right    = node;
    x->color    = node->color;
    node->color = Node::Color::RED;
    return x;
}

void LLRB::flipColors(std::shared_ptr<Node> node) {
    assert(isBlack(node) && isRed(node->left) && isRed(node->right));
    node->color        = Node::Color::RED;
    node->left->color  = Node::Color::BLACK;
    node->right->color = Node::Color::BLACK;
}

void LLRB::display() {
    queue<shared_ptr<Node>> q;
    if (root == nullptr) {
        fmt::print("empty tree\n");
        return;
    }
    q.push(root);
    while (q.size() != 0) {
        queue<shared_ptr<Node>> tmp;
        while (q.size() != 0) {
            shared_ptr<Node> n = q.front();
            q.pop();
            fmt::print("{: <+4}", n->key);
            tmp.push(n);
        }
        while (tmp.size() != 0) {
            shared_ptr<Node> n = tmp.front();
            tmp.pop();
            if (n->left != nullptr)
                q.push(n->left);
            if (n->right != nullptr)
                q.push(n->right);
        }
        fmt::print("\n");
    }
}