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
}
