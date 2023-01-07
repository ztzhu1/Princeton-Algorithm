#include <assert.h>
#include <fmt/core.h>
#include <queue>
#include "bst.h"

using namespace std;

Node::Node(int key)
    : key(key) { }

Node::~Node() {
    fmt::print("*\n");
}

optional<int> BST::get(int key) {
    return get(root, key);
}

optional<int> BST::get(std::shared_ptr<Node> &root, int key) {
    if (root == nullptr) {
        return nullopt;
    } else if (root->key == key) {
        return key;
    } else if (key < root->key) {
        return get(root->left, key);
    } else {
        return get(root->right, key);
    }
}

void BST::insert(int key) {
    insert(root, weak_ptr<Node>{}, key);
}

void BST::insert(shared_ptr<Node> &root, const weak_ptr<Node> &parent, int key) {
    if (root == nullptr) {
        root = make_shared<Node>(key);
        root->parent = parent;
    } else if (key < root->key) {
        insert(root->left, weak_ptr<Node>{root}, key);
    } else if (key > root->key) {
        insert(root->right, weak_ptr<Node>{root}, key);
    } else {
        assert(false);
    }
}

int BST::depth() {
    return depth(root);
}

int BST::depth(std::shared_ptr<Node> &root) {
    if (root == nullptr) {
        return 0;
    } else {
        return 1 + max(depth(root->left), depth(root->right));
    }
}

void BST::remove(int key) {
    root = remove(root, key);
}

/**
 * If the node is not the target, the function returns
 * the node itself, otherwise it returns the node which
 * replaces the original node.
 * In summary, the function returns the node which
 * appears at the position of the original node. 
 */
shared_ptr<Node> BST::remove(shared_ptr<Node> node, int key) {
    if (node == nullptr) {
        return nullptr;
    }

    if (key < node->key) {
        node->left = remove(node->left, key);
    } else if (key > node->key) {
        node->right = remove(node->right, key);
    } else {
        if (!node->left)
            return node->right;
        if (!node->right)
            return node->left;

        auto t = node;
        node = minNode(node);
        node->right = removeMin(t->right);
        node->left = t->left;
    }
    return node;
}

void BST::removeMin() {
    root = removeMin(root);
}

shared_ptr<Node> BST::removeMin(std::shared_ptr<Node> node) {
    if (!node)
        return nullptr;
    if (!node->left)
        return node->right;
    node->left = removeMin(node->left);
    return node;
}

shared_ptr<Node> BST::minNode(std::shared_ptr<Node> node) {
    if (!node)
        return nullptr;
    if (!node->left)
        return node;
    return minNode(node->left);
}

void BST::display() {
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