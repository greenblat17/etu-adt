package com.greenblat.adt.lab2.tests;

import com.greenblat.adt.lab2.sorts.InsertionSort;
import com.greenblat.adt.lab2.sorts.MergeSort;
import com.greenblat.adt.lab2.sorts.TimSort;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TestSort {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();

        TimSort<Integer> timSort = new TimSort<>(list);
        InsertionSort<Integer> insertionSort = new InsertionSort<>(list);
        MergeSort<Integer> mergeSort = new MergeSort<>(list);

        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            list.add(random.nextInt(50));
        }
        System.out.println("TimSort");
        System.out.println("Before sorting");
        System.out.println(list);
        System.out.println("After sorting");
        timSort.sort();
        System.out.println(list);
        list.clear();
        System.out.println("-------------------------------");

//        for (int i = 0; i < 20; i++) {
//            list.add(random.nextInt(100));
//        }
//        System.out.println("InsertionSort");
//        System.out.println("Before sorting");
//        System.out.println(list);
//        System.out.println("After sorting");
//        insertionSort.sort(0, list.size() - 1);
//        System.out.println(list);
//        list.clear();
//        System.out.println("-------------------------------");
//
//        for (int i = 0; i < 20; i++) {
//            list.add(random.nextInt(100));
//        }
//        System.out.println("MergeSort");
//        System.out.println("Before sorting");
//        System.out.println(list);
//        System.out.println("After sorting");
//        mergeSort.sort(0, list.size() / 2, list.size() - 1);
//        System.out.println(list);
//        System.out.println("-------------------------------");
    }
}
