#include <vector>
#include <iostream>

using namespace std;

void insertionSort(vector<int> &arr) {
    int N = arr.size();
    int count = 0;
    for (int i = 1; i < N; i++) {
        int j   = i;
        int tmp = arr[i];
        while (j > 0 && tmp < arr[j - 1]) {
            arr[j] = arr[j - 1];
            j--;
            count++;
        }
        arr[j] = tmp;
    }
    cout << "count: " << count << endl;
}

void shellSort(vector<int> &arr) {
    int h = 1;
    int N = arr.size();
    while (h < N / 3) {
        h = 3 * h + 1;
    }
    int count = 0;
    while (h > 0) {
        for (int i = 0; i < h; i++) {
            /**
             * insertion sort
             */
            for (int j = i + h; j < N; j += h) {
                int tmp = arr[j];
                int k   = j;
                while (k >= h && tmp < arr[k - h]) {
                    arr[k] = arr[k - h];
                    k -= h;
                    count++;
                }
                arr[k] = tmp;
            }
        }
        h /= 3;
    }
    cout << "count: " << count << endl;
}