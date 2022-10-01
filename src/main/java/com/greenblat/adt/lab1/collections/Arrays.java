package com.greenblat.adt.lab1.collections;

public class Arrays {
    private static final double MULTIPLY_COEFFICIENT = 1.5;

    public static Object[] createNewArrayWithCopy(Object[] oldArr) {
        Object[] newArr = new Object[(int) (oldArr.length * MULTIPLY_COEFFICIENT)];
        for (int i = 0; i < oldArr.length; i++) {
            newArr[i] = oldArr[i];
        }
        return newArr;
    }
}
