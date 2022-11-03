package com.greenblat.adt.lab1.collections.utils;


import com.greenblat.adt.lab1.collections.List;

public class Collections {

    public static <T extends Comparable<T>> T[] copyToArrays(List<T> list, int startIndexToCopy, int sizeArray) {
        T[] copyArray = (T[]) new Comparable[sizeArray];
        int countToCopy = startIndexToCopy;
        for (int i = 0; i < sizeArray; i++) {
            copyArray[i] = list.get(countToCopy++);
        }
        return copyArray;
    }

    public static <T extends Comparable<T>> void reverseList(List<T> arr, int start, int end) {
        for (int i = 0; i < (end - start) / 2; i++) {
            T tmp = arr.get(start + i);
            arr.set(start + i, arr.get(end - i - 1));
            arr.set(end - i - 1, tmp);
        }
    }
}
