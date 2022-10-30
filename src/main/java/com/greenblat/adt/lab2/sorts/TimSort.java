package com.greenblat.adt.lab2.sorts;


import com.greenblat.adt.lab1.collections.LinkedList;
import com.greenblat.adt.lab1.collections.List;
import com.greenblat.adt.lab1.collections.Stack;
import com.greenblat.adt.lab1.utils.Arrays;
import com.greenblat.adt.lab1.utils.Collections;
import com.greenblat.adt.lab2.utils.PairRun;

import java.util.Optional;


public class TimSort<T extends Comparable<T>> implements Sort<T> {

    private static final int GALLOPING_VALUE = 7;

    private final List<T> arr;

    public TimSort(List<T> arr) {
        this.arr = arr;
    }

    public void sort() {
        InsertionSort<T> insertionSort = new InsertionSort<>(arr);

        int minRun = calculateMinRun(arr.size());
        Stack<PairRun> stack = new LinkedList<>();

        // step 1 insertion
        int start = 0;
        while (start < arr.size()) {
            int end = Math.min(start + minRun - 1, arr.size() - 1);

            // reverse
            boolean needReverse = false;
            int startDecrease = start;
            for (int k = start + 1; k < end + 1; k++) {
                if (arr.get(k).compareTo(arr.get(k - 1)) < 0 && !needReverse) {
                    startDecrease = k - 1;
                    needReverse = true;
                } else if (arr.get(k).compareTo(arr.get(k - 1)) > 0 && needReverse) {
                    needReverse = false;
                    reverseList(startDecrease, k);
                }
            }

            while (end < arr.size() - 1 && arr.get(end).compareTo(arr.get(end + 1)) < 0) {
                end++;
            }
            insertionSort.insertionSort(start, end);

            stack.push(new PairRun(start, end - start + 1));

            start = end + 1;
        }


        // step 2 merging
        while (!stack.isEmpty()) {
            PairRun pairRun1 = stack.pop();
            Optional<PairRun> pairRun2 = stack.isEmpty() ? Optional.empty() : Optional.ofNullable(stack.pop());
            Optional<PairRun> pairRun3 = stack.isEmpty() ? Optional.empty()  : Optional.ofNullable(stack.pop());

            if (pairRun2.isEmpty()) {
                break;
            }

            int low;
            int mid;
            int high;
            if (pairRun3.isEmpty()) {
                low = pairRun2.get().getIndex();
                high = pairRun1.getSize() + pairRun2.get().getSize() - 1;
                mid = low + pairRun2.get().getSize();
            } else {
                int size1 = pairRun1.getSize();
                int size2 = pairRun2.get().getSize();
                int size3 = pairRun3.get().getSize();

                if ((size3 > size2 + size1 && size2 > size1) || size3 > size1) {
                    low = pairRun2.get().getIndex();
                    high = pairRun1.getIndex() + size1 - 1;
                    mid = low + size2;
                } else {
                    low = pairRun3.get().getIndex();
                    high = pairRun2.get().getIndex() + size2 - 1;
                    mid = low + size3;
                }
            }
            mergeWithGallopingMode(low, mid, high);
        }

//        while (!stack.isEmpty()) {
//            PairRun pairRun1 = stack.pop();
//
//            int low = 0;
//            int high = 0;
//            int mid = 0;
//            if (stack.size() >= 3) {
//                PairRun pairRun2 = stack.pop();
//                PairRun pairRun3 = stack.pop();
//
//                if ((pairRun3.getSize() > pairRun2.getSize() + pairRun1.getSize() && pairRun2.getSize() > pairRun1.getSize())
//                        || (pairRun3.getSize() > pairRun1.getSize())) {
//                    low = pairRun2.getIndex();
//                    high = pairRun1.getIndex() + pairRun1.getSize() - 1;
//                    mid = low + pairRun2.getSize();
//                } else {
//                    low = pairRun3.getIndex();
//                    high = pairRun2.getIndex() + pairRun2.getSize() - 1;
//                    mid = low + pairRun3.getSize();
//                }
//            } else if (stack.size() == 2) {
//                PairRun pairRun2 = stack.pop();
//                low = pairRun2.getIndex();
//                high = pairRun1.getSize() + pairRun2.getSize() - 1;
//                mid = low + pairRun2.getSize();
//            }
//            mergeWithGallopingMode(low, mid, high);
//        }
    }


    private int calculateMinRun(int n) {
        int r = 0;
        while (n >= 64) {
            r |= n & 1;
            n >>= 1;
        }
        return n + r;
    }

    private void reverseList(int start, int end) {
        for (int i = start; i < (start + end) / 2; i++) {
            T temp = arr.get(i);
            arr.set(i, arr.get(end - i - 1));
            arr.set(end - i - 1, temp);
        }
    }

    void mergeWithGallopingMode(int low, int middle, int high) {

        T[] leftArray = Collections.copyToArrays(arr, low, middle - low + 1);
        T[] rightArray = Collections.copyToArrays(arr, middle + 1, high - middle);


        int leftSubArrCounter = 0;
        int rightSubArrCounter = 0;
        int arrCounter = low;

        int countGalloping = 0;
        boolean changeGallopingMode = false;
        boolean gallopingMode = false;
        int a = 0;
        while (leftSubArrCounter < leftArray.length && rightSubArrCounter < rightArray.length) {
            if (!gallopingMode) {
                T val;
                if (leftArray[leftSubArrCounter].compareTo(rightArray[rightSubArrCounter]) <= 0) {
                    val = leftArray[leftSubArrCounter++];

                    if (changeGallopingMode) {
                        countGalloping = 1;
                        changeGallopingMode = false;
                    } else {
                        countGalloping++;
                    }
                } else {
                    val = rightArray[rightSubArrCounter++];

                    if (!changeGallopingMode) {
                        countGalloping = 1;
                        changeGallopingMode = true;
                    } else {
                        countGalloping++;
                    }
                }

                arr.set(arrCounter++, val);

                if (countGalloping >= GALLOPING_VALUE) {
                    if (changeGallopingMode)
                        a = Arrays.searchIndexWithBinarySearch(rightArray, rightSubArrCounter, leftArray[leftSubArrCounter]);
                    else
                        a = Arrays.searchIndexWithBinarySearch(leftArray, leftSubArrCounter, rightArray[rightSubArrCounter]);

                    gallopingMode = true;
                }
            } else {
                if (changeGallopingMode) {
                    while (rightSubArrCounter < a) {
                        arr.set(arrCounter++, rightArray[rightSubArrCounter++]);
                    }
                } else {
                    while (leftSubArrCounter < a) {
                        arr.set(arrCounter++, leftArray[leftSubArrCounter++]);
                    }
                }
                gallopingMode = false;
                countGalloping = 0;
            }

        }

        while (leftSubArrCounter < leftArray.length) {
            arr.set(arrCounter++, leftArray[leftSubArrCounter++]);
        }

        while (rightSubArrCounter < rightArray.length) {
            arr.set(arrCounter++, rightArray[rightSubArrCounter++]);
        }

    }
    

}
