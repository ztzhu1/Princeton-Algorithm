#include <fmt/core.h>
#include <gtest/gtest.h>
#include <vector>
#include <random>
#include <algorithm>

using namespace std;

void insertionSort(vector<int> &arr);
void shellSort(vector<int> &arr);
void mergeSortV1(vector<int> &arr);
void mergeSortV2(vector<int> &arr);
void quickSort2Way(vector<int> &arr);
void quickSort3Way(vector<int> &arr);

TEST(testSort, testInsertionSort) {
    vector<int> arr = {2, 1, 4, 5, 7, 7, 3, -11, 0, 9};
    vector<int> ans = {-11, 0, 1, 2, 3, 4, 5, 7, 7, 9};
    random_device rd;
    shuffle(arr.begin(), arr.end(), mt19937{rd()});
    insertionSort(arr);
    EXPECT_EQ(arr, ans);
}

TEST(testSort, testShellSort) {
    vector<int> arr = {2, 1, 4, 5, 7, 7, 3, -11, 0, 9};
    vector<int> ans = {-11, 0, 1, 2, 3, 4, 5, 7, 7, 9};
    random_device rd;
    shuffle(arr.begin(), arr.end(), mt19937{rd()});
    shellSort(arr);
    EXPECT_EQ(arr, ans);
}

TEST(testSort, testMergeSortV1) {
    vector<int> arr = {2, 1, 4, 5, 7, 7, 3, -11, 0, 9};
    vector<int> ans = {-11, 0, 1, 2, 3, 4, 5, 7, 7, 9};
    random_device rd;
    shuffle(arr.begin(), arr.end(), mt19937{rd()});
    mergeSortV1(arr);
    EXPECT_EQ(arr, ans);
}

TEST(testSort, testMergeSortV2) {
    vector<int> arr = {2, 1, 4, 5, 7, 7, 3, -11, 0, 9};
    vector<int> ans = {-11, 0, 1, 2, 3, 4, 5, 7, 7, 9};
    random_device rd;
    shuffle(arr.begin(), arr.end(), mt19937{rd()});
    mergeSortV2(arr);
    EXPECT_EQ(arr, ans);
}

TEST(testSort, testQuickSort2Way) {
    vector<int> arr = {2, 1, 4, 5, 7, 7, 3, -11, 0, 9};
    vector<int> ans = {-11, 0, 1, 2, 3, 4, 5, 7, 7, 9};
    random_device rd;
    shuffle(arr.begin(), arr.end(), mt19937{rd()});
    quickSort2Way(arr);
    EXPECT_EQ(arr, ans);
}

TEST(testSort, testQuickSort3Way) {
    vector<int> arr = {2, 1, 4, 5, 7, 7, 3, -11, 0, 9};
    vector<int> ans = {-11, 0, 1, 2, 3, 4, 5, 7, 7, 9};
    random_device rd;
    shuffle(arr.begin(), arr.end(), mt19937{rd()});
    quickSort3Way(arr);
    EXPECT_EQ(arr, ans);
}