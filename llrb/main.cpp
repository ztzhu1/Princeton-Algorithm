#include "llrb.h"

#include <fmt/core.h>

int main(int argc, char **argv) {
    LLRB b;
    b.put(10, 1);
    b.display();
    fmt::print("#\n");
    b.put(5, 2);
    b.display();
    fmt::print("#\n");
    b.put(8, 3);
    b.display();
    fmt::print("#\n");
    b.put(7, 4);
    b.display();
    fmt::print("#\n");
}