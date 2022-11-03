package com.greenblat.adt.lab2.sorts;

import com.greenblat.adt.lab1.collections.List;
import com.greenblat.adt.lab1.collections.utils.Collections;


public class MergeSort<T extends Comparable<T>> implements Sort<T> {

    private final List<T> arr;
    private final T[] tempArr;

    public MergeSort(List<T> arr) {
        this.arr = arr;
        this.tempArr = (T[]) new Comparable[arr.size()];
    }

    @Override
    public void sort() {
        mergeSort(0, arr.size() / 2, arr.size() - 1);
    }

    public void mergeSort(int start, int mid, int end) {
        if (start >= end)
            return;
        mergeSort(start, (start + mid) / 2, mid);
        mergeSort(mid + 1, (mid + 1 + end) / 2, end);
        merge(start, mid, end);
    }

    void merge(int low, int middle, int high) {
        T[] leftArray = (T[]) new Comparable[middle - low + 1];
        T[] rightArray = (T[]) new Comparable[high - middle];


        int countToCopy = low;
        for (int i = 0; i < leftArray.length; i++) {
            leftArray[i] = arr.get(countToCopy++);
        }
        countToCopy = middle + 1;
        for (int i = 0; i < rightArray.length; i++) {
            rightArray[i] = arr.get(countToCopy++);
        }

        int leftSubArrCounter = 0;
        int rightSubArrCounter = 0;
        int arrCounter = low;

        while (leftSubArrCounter < leftArray.length && rightSubArrCounter < rightArray.length) {
            T val;
            if (leftArray[leftSubArrCounter].compareTo(rightArray[rightSubArrCounter]) <= 0) {
                val = leftArray[leftSubArrCounter++];

            } else {
                val = rightArray[rightSubArrCounter++];
            }

            arr.set(arrCounter++, val);
        }

        while (leftSubArrCounter < leftArray.length) {
            arr.set(arrCounter++, leftArray[leftSubArrCounter++]);
        }

        while (rightSubArrCounter < rightArray.length) {
            arr.set(arrCounter++, rightArray[rightSubArrCounter++]);
        }
    }
}
