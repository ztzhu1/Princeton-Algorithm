#include <gtest/gtest.h>
#include <vector>
#include <fmt/core.h>

using namespace std;

void insertionSort(vector<int> &arr);
void shellSort(vector<int> &arr);

TEST(testSort, testInsertionSort) {
    vector<int> arr = {2, 1, 4, 5, 7, 7, 3, -11, 0, 9};
    vector<int> ans = {-11, 0, 1, 2, 3, 4, 5, 7, 7, 9};
    insertionSort(arr);
    EXPECT_EQ(arr, ans);
}

TEST(testSort, testShellSort) {
    vector<int> arr = {2, 1, 4, 5, 7, 7, 3, -11, 0, 9};
    vector<int> ans = {-11, 0, 1, 2, 3, 4, 5, 7, 7, 9};
    shellSort(arr);
    EXPECT_EQ(arr, ans);
}