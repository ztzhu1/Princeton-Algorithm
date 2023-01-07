#include <fmt/core.h>
#include "bst.h"

int main(int argc, char **argv) {
    BST b;
    b.insert(10);
    b.insert(5);
    b.insert(8);
    b.insert(7);
    b.display();
    b.remove(8);
    b.display();
    // b.insert(1);
    // b.insert(-10);
    // b.insert(12);
    // b.insert(2);
    // b.insert(-11);
    // b.insert(15);
    // fmt::print("depth: {}\n", b.depth());
    // fmt::print("depth: {}\n", b.get(15).value_or(100));
    // b.display();
    // std::shared_ptr<int> a = std::make_shared<int>(1);
    // a.reset();
    // if (a) {
    //     fmt::print("111\n");
    // } else {
    //     fmt::print("222\n");
    // }
}