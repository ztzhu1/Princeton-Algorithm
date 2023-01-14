#include <algorithm>
#include <iostream>
#include <random>
#include <vector>

using namespace std;

/******************
 * insertion sort *
 ******************/
void insertionSort(vector<int> &arr) {
    int N     = arr.size();
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

/**************
 * shell sort *
 **************/
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

/**************
 * merge sort *
 **************/
void sort(vector<int> &arr, vector<int> &aux, int lo, int hi);
void sortWithCheckBeforeMerge(vector<int> &arr,
                              vector<int> &aux,
                              int lo,
                              int hi);
void merge(vector<int> &arr, vector<int> &aux, int lo, int mid, int hi);

void mergeSortV1(vector<int> &arr) {
    vector<int> aux(arr);
    sort(arr, aux, 0, arr.size() - 1);
}

void sort(vector<int> &arr, vector<int> &aux, int lo, int hi) {
    if (lo >= hi)
        return;
    int mid = lo + (hi - lo) / 2;
    sort(arr, aux, lo, mid);
    sort(arr, aux, mid + 1, hi);
    merge(arr, aux, lo, mid, hi);
}

void mergeSortV2(vector<int> &arr) {
    vector<int> aux(arr);
    sortWithCheckBeforeMerge(arr, aux, 0, arr.size() - 1);
}

void sortWithCheckBeforeMerge(vector<int> &arr,
                              vector<int> &aux,
                              int lo,
                              int hi) {
    if (lo >= hi)
        return;
    int mid = lo + (hi - lo) / 2;
    sortWithCheckBeforeMerge(arr, aux, lo, mid);
    sortWithCheckBeforeMerge(arr, aux, mid + 1, hi);
    if (arr[mid] > arr[mid + 1])
        merge(arr, aux, lo, mid, hi);
}

// void mergeSortV3(vector<int> &arr) {
//     vector<int> aux(arr);
//     sortWithInsertion(arr, aux, 0, arr.size() - 1);
// }

// #define CUTOFF 7

// void sortWithInsertion(vector<int> &arr, vector<int> &aux, int lo, int hi) {
//     if (lo >= hi)
//         return;
//     if (hi - lo < CUTOFF) {
//         insertionSort(arr, lo, hi);
//     }
//     int mid = lo + (hi - lo) / 2;
//     sortWithInsertion(arr, aux, lo, mid);
//     sortWithInsertion(arr, aux, mid + 1, hi);
//     if (arr[mid] > arr[mid + 1])
//         merge(arr, aux, lo, mid, hi);
// }

void merge(vector<int> &arr, vector<int> &aux, int lo, int mid, int hi) {
    copy(arr.begin() + lo, arr.begin() + hi + 1, aux.begin() + lo);
    int i = lo;
    int j = mid + 1;
    for (int k = lo; k <= hi; k++) {
        if (i > mid)
            arr[k] = aux[j++];
        else if (j > hi)
            arr[k] = aux[i++];
        else if (aux[i] < aux[j])
            arr[k] = aux[i++];
        else
            arr[k] = aux[j++];
    }
}

/**************
 * quick sort *
 **************/
void quickSort2Way(vector<int> &arr, int lo, int hi);
int partition(vector<int> &arr, int lo, int hi);

void quickSort2Way(vector<int> &arr) {
    random_device rd;
    mt19937 g(rd());
    shuffle(arr.begin(), arr.end(), g);
    quickSort2Way(arr, 0, arr.size() - 1);
}

void quickSort2Way(vector<int> &arr, int lo, int hi) {
    if (lo >= hi)
        return;
    int j = partition(arr, lo, hi);
    quickSort2Way(arr, lo, j - 1);
    quickSort2Way(arr, j + 1, hi);
}

void quickSort3Way(vector<int> &arr, int lo, int hi);

void quickSort3Way(vector<int> &arr) {
    random_device rd;
    mt19937 g(rd());
    shuffle(arr.begin(), arr.end(), g);
    quickSort3Way(arr, 0, arr.size() - 1);
}

void quickSort3Way(vector<int> &arr, int lo, int hi) {
    if (lo >= hi)
        return;

    int i    = lo + 1;
    int lt   = lo;
    int gt   = hi;
    int base = arr[lo];
    while (i <= gt) {
        if (arr[i] < base)
            swap(arr[lt++], arr[i++]);
        else if (arr[i] > base)
            swap(arr[gt--], arr[i]);
        else
            i++;
    }
    quickSort3Way(arr, lo, lt - 1);
    quickSort3Way(arr, gt + 1, hi);
}

int partition(vector<int> &arr, int lo, int hi) {
    int i    = lo + 1;
    int j    = hi;
    int base = arr[lo];
    while (true) {
        while (i <= j && arr[i] < base)
            i++;
        while (i <= j && arr[j] > base)
            j--;
        if (i >= j)
            break;
        swap(arr[i], arr[j]);
    }
    swap(arr[lo], arr[j]);
    return j;
}