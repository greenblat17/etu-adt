package com.greenblat.adt.lab2.sorts;


import com.greenblat.adt.lab1.collections.List;

public class InsertionSort<T extends Comparable<T>> implements Sort<T> {

    private final List<T> arr;

    public InsertionSort(List<T> arr) {
        this.arr = arr;
    }

    @Override
    public void sort() {
        insertionSort(0, arr.size() - 1);
    }

    public void insertionSort(int start, int end) {
        for (int i = start + 1; i <= end; i++) {
            int j = i - 1;

            T selected = arr.get(i);
            int loc = binarySearch(selected, start, j);
            while (j >= loc) {
                arr.set(j + 1, arr.get(j));
                j--;
            }
            arr.set(j + 1, selected);
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
