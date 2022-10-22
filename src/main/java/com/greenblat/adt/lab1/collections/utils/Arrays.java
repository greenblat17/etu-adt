package com.greenblat.adt.lab1.collections.utils;

public class Arrays {
    private static final double MULTIPLY_COEFFICIENT = 1.5;

    public static Object[] createNewArrayWithCopy(Object[] oldArr) {
        Object[] newArr = new Object[(int) (oldArr.length * MULTIPLY_COEFFICIENT)];
        for (int i = 0; i < oldArr.length; i++) {
            newArr[i] = oldArr[i];
        }
        return newArr;
    }

    public static <T extends Comparable<T>> int searchIndexWithBinarySearch(T[] arr, int start, T target) {
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
