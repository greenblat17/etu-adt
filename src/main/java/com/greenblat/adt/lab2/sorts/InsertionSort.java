package com.greenblat.adt.lab2.sorts;


import java.util.List;

public class InsertionSort<T extends Comparable<T>>{

    private final List<T> arr;

    public InsertionSort(List<T> arr) {
        this.arr = arr;
    }

    public void sort(int start, int end) {
        for (int i = start; i <= end; i++) {
            int j = i;
            while (j > 0 && arr.get(j).compareTo(arr.get(j - 1)) < 0) {
                T temp = arr.get(j);
                arr.set(j, arr.get(j - 1));
                arr.set(j - 1, temp);
                j--;
            }
        }
    }

    private int binarySearch(T item, int low, int high) {
        if (high <= low)
            return (item.compareTo(arr.get(low)) > 0) ?
                    (low + 1) : low;

        int mid = (low + high) / 2;

        if (item == arr.get(mid))
            return mid + 1;

        if (item.compareTo(arr.get(mid)) > 0)
            return binarySearch(item,
                    mid + 1, high);
        return binarySearch(item, low,
                mid - 1);
    }
}
