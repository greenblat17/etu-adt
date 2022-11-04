package com.greenblat.adt.lab2.tests;

import com.greenblat.adt.lab1.collections.ArrayList;
import com.greenblat.adt.lab1.collections.List;
import com.greenblat.adt.lab2.sorts.Sort;
import com.greenblat.adt.lab2.sorts.TimSort;

import java.util.Random;

public class TestSort {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();

        TimSort<Integer> timSort = new TimSort<>(list);
//        InsertionSort<Integer> insertionSort = new InsertionSort<>(list);
//        MergeSort<Integer> mergeSort = new MergeSort<>(list);

        System.out.println("Testing TimSort");
        testSortingElement(list, timSort);


//        System.out.println("Testing MergeSort");
//        testSortingElement(list, mergeSort);
//
//        System.out.println("Testing InsertionSort");
//        testSortingElement(list, insertionSort);
    }

    private static void testSortingElement(List<Integer> list, Sort<Integer> sortingElement) {
        Random random = new Random();
        for (int i = 0; i < 1000000; i++) {
            list.add(random.nextInt(100));
        }
        System.out.println("Before sorting");
        System.out.println(list);
        System.out.println("After sorting");
        sortingElement.sort();
        System.out.println(list);

        for (int i = 0; i < list.size() - 1; i++) {
            if (list.get(i) > list.get(i + 1)) {
                System.out.print("!");
            }
        }

        list.clear();
        System.out.println();
    }
}
