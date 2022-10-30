package com.greenblat.adt.lab2;

import com.greenblat.adt.lab1.collections.ArrayList;
import com.greenblat.adt.lab1.collections.List;
import com.greenblat.adt.lab2.sorts.InsertionSort;
import com.greenblat.adt.lab2.sorts.MergeSort;
import com.greenblat.adt.lab2.sorts.Sort;
import com.greenblat.adt.lab2.sorts.TimSort;

import java.util.Random;

public class SortTime {
    public static void main(String[] args) {
        System.out.println("Testing ArrayList");
        List<Integer> list = new ArrayList<>();

        Sort<Integer> timSort = new TimSort<>(list);
        Sort<Integer> mergeSort = new MergeSort<>(list);
        Sort<Integer> insertionSort = new InsertionSort<>(list);

        timeOfSoringList(list, 1000000, timSort);
        timeOfSoringList(list, 1000000, mergeSort);
//        timeOfSoringList(list, 1000000, insertionSort);

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
