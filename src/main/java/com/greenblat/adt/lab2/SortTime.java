package com.greenblat.adt.lab2;

import com.greenblat.adt.lab2.sorts.InsertionSort;
import com.greenblat.adt.lab2.sorts.MergeSort;
import com.greenblat.adt.lab2.sorts.TimSort;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SortTime {
    public static void main(String[] args) {

        System.out.println("Testing ArrayList");
        List<Integer> list = new ArrayList<>();
//        timeSort(list, 1000);
//        timeSort(list, 10000);
        timeSort(list, 100000);

    }

    private static void timeSort(List<Integer> arrayList, int count) {
        addElementsToList(arrayList, count);
        long start = System.currentTimeMillis();
        new TimSort<>(arrayList).sort();
        long end  = System.currentTimeMillis();
        System.out.println("TimSort time: " + (end - start));
        arrayList.clear();

        addElementsToList(arrayList, count);
        start = System.currentTimeMillis();
        new MergeSort<>(arrayList).sort(0, arrayList.size() / 2, arrayList.size() - 1);
        end  = System.currentTimeMillis();
        System.out.println("MergeSort time: " + (end - start));
        arrayList.clear();

        addElementsToList(arrayList, count);
        start = System.currentTimeMillis();
        new InsertionSort<>(arrayList).sort(0, arrayList.size() - 1);
        end  = System.currentTimeMillis();
        System.out.println("InsertionSort time: " + (end - start));
        arrayList.clear();
    }

    private static void addElementsToList(List<Integer> list, int countElements) {
        Random random = new Random();
        for (int i = 0; i < countElements; i++) {
            list.add(random.nextInt(100));
        }
    }
}
