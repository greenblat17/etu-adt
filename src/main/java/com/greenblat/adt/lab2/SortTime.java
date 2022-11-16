package com.greenblat.adt.lab2;

import com.greenblat.adt.lab1.collections.ArrayList;
import com.greenblat.adt.lab1.collections.List;
import com.greenblat.adt.lab2.sorts.InsertionSort;
import com.greenblat.adt.lab2.sorts.MergeSort;
import com.greenblat.adt.lab2.sorts.Sort;
import com.greenblat.adt.lab2.sorts.TimSort;

import java.util.Collections;
import java.util.Random;

public class SortTime {
    public static void main(String[] args) {
        System.out.println("Testing ArrayList");
        List<Integer> list = new ArrayList<>();

        Sort<Integer> timSort = new TimSort<>(list);
        Sort<Integer> mergeSort = new MergeSort<>(list);
        Sort<Integer> insertionSort = new InsertionSort<>(list);

//        timeOfSoringList(list, 100, timSort);
//        timeOfSoringList(list, 1000, timSort);
//        timeOfSoringList(list, 10000, timSort);
//        timeOfSoringList(list, 100000, timSort);
        timeOfSoringList(list, 1000000, timSort);
        Random random = new Random();
        java.util.List<Integer> list1 = new java.util.ArrayList<>();
        for (int i = 0; i < 1000000; i++) {
            list1.add(random.nextInt(100));
        }
        long start = System.currentTimeMillis();
        Collections.sort(list1);
        long end = System.currentTimeMillis();
        System.out.println("jdk timsort: " + (end - start));

    }


    private static void timeOfSoringList(List<Integer> list, int countElements, Sort<Integer> sortingList) {
        addElementsToList(list, countElements);
        long start = System.currentTimeMillis();
        sortingList.sort();
        long end  = System.currentTimeMillis();
        System.out.println("Time: " + (end - start));
        list.clear();
    }

    private static void addElementsToList(List<Integer> list, int countElements) {
        Random random = new Random();
        for (int i = 0; i < countElements; i++) {
            list.add(random.nextInt(100));
        }
    }
}
