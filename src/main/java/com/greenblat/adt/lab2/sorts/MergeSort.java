package com.greenblat.adt.lab2.sorts;

import java.util.List;

public class MergeSort<T extends Comparable<T>> {

    private static final int GALLOPING_VALUE =0;

    private final List<T> arr;
    private final T[] tempArr;

    public MergeSort(List<T> arr) {
        this.arr = arr;
        this.tempArr = (T[]) new Comparable[arr.size()];
    }

    public void sort(int start, int mid, int end) {
        if (start >= end)
            return;
        sort(start, (start + mid) / 2, mid);
        sort(mid + 1, (mid + 1 + end) / 2, end);
        merge(start, mid, end);
    }

    void merge(int low, int middle, int high) {

        if (middle - low + 1 < 0 || high - middle < 0) {
            System.out.println("skgjrigjjeqrg");
        }

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

        int countGalloping = 0;
        boolean changeGallopingMode = false;
        boolean gallopingMode = false;
        int a = 0;
        while (leftSubArrCounter < leftArray.length && rightSubArrCounter < rightArray.length) {
            if (!gallopingMode) {
                T val;
                if (leftArray[leftSubArrCounter].compareTo(rightArray[rightSubArrCounter]) <= 0) {
                    val = leftArray[leftSubArrCounter++];

                    if (changeGallopingMode) {
                        countGalloping = 1;
                        changeGallopingMode = false;
                    } else {
                        countGalloping++;
                    }
                } else {
                    val = rightArray[rightSubArrCounter++];

                    if (!changeGallopingMode) {
                        countGalloping = 1;
                        changeGallopingMode = true;
                    } else {
                        countGalloping++;
                    }
                }

                arr.set(arrCounter++, val);

                if (countGalloping >= GALLOPING_VALUE) {
                    if (changeGallopingMode)
                        a = binarySearch(rightArray, rightSubArrCounter, leftArray[leftSubArrCounter]);
                    else
                        a = binarySearch(leftArray, leftSubArrCounter, rightArray[rightSubArrCounter]);

                    gallopingMode = true;
                }
            } else {
                if (changeGallopingMode) {
                    while (rightSubArrCounter < a) {
                        arr.set(arrCounter++, rightArray[rightSubArrCounter++]);
                    }
                } else {
                    while (leftSubArrCounter < a) {
                        arr.set(arrCounter++, leftArray[leftSubArrCounter++]);
                    }
                }
                gallopingMode = false;
                countGalloping = 0;
            }




        }

        while (leftSubArrCounter < leftArray.length) {
            arr.set(arrCounter++, leftArray[leftSubArrCounter++]);
        }

        while (rightSubArrCounter < rightArray.length) {
            arr.set(arrCounter++, rightArray[rightSubArrCounter++]);
        }

    }

    private int binarySearch(T[] arr, int start, T target) {
        int left = start;
        int right = arr.length - 1;
        while (left  < right) {
            int mid = left + (right - left) / 2;

            if (arr[mid].compareTo(target) < 0)
                left = mid + 1;
            else if (arr[mid].compareTo(target) > 0)
                right = mid - 1;
            else
                return mid;

        }
        return right;
    }

}
