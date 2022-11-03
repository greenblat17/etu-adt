package com.greenblat.adt.lab2.sorts;


import com.greenblat.adt.lab1.collections.List;
import com.greenblat.adt.lab1.collections.utils.Collections;
import com.greenblat.adt.lab2.utils.PairRun;

import java.util.Optional;
import java.util.Stack;


public class TimSort<T extends Comparable<T>> implements Sort<T> {

    private final List<T> arr;

    public TimSort(List<T> arr) {
        this.arr = arr;
    }

    public void sort() {
        InsertionSort<T> insertionSort = new InsertionSort<>(arr);

        int minRun = calculateMinRun(arr.size());
        Stack<PairRun> stackForMerge = new java.util.Stack<>();

        // step 1 insertion
        int start = 0;
        while (start < arr.size()) {
            int idx = start + 1;
            int end = start + 1;

            boolean descendingSequence = false;
            while (idx < minRun) {
                if (!descendingSequence && arr.get(idx).compareTo(arr.get(idx - 1)) < 0)
                    descendingSequence = true;
                else if (descendingSequence && arr.get(idx).compareTo(arr.get(idx - 1)) > 0) {
                    end = idx - 1;
                    Collections.reverseList(arr, start, end);
                } else break;;
                idx++;
            }

            if (end  - start < minRun) {
                end = Math.min(start + minRun - 1, arr.size() - 1);
            }

            insertionSort.insertionSort(start, end);
            stackForMerge.push(new PairRun(start, end -start + 1));

            start = end + 1;

        }

        // step 2 merging
        while (!stackForMerge.isEmpty()) {
            PairRun pairRun1 = stackForMerge.pop();
            Optional<PairRun> pairRun2 = stackForMerge.isEmpty() ? Optional.empty() : Optional.ofNullable(stackForMerge.pop());
            Optional<PairRun> pairRun3 = stackForMerge.isEmpty() ? Optional.empty()  : Optional.ofNullable(stackForMerge.pop());

            if (pairRun2.isEmpty()) {
                break;
            }

            int low;
            int mid;
            int high;
            if (pairRun3.isEmpty()) {
                low = pairRun2.get().getIndex();
                high = pairRun1.getSize() + pairRun2.get().getSize() - 1;
                mid = low + pairRun2.get().getSize() - 1;
            } else {
                int size1 = pairRun1.getSize();
                int size2 = pairRun2.get().getSize();
                int size3 = pairRun3.get().getSize();

                if ((size3 > size2 + size1 && size2 > size1) || size3 > size1) {
                    low = pairRun2.get().getIndex();
                    high = pairRun1.getIndex() + size1 - 1;
                    mid = low + size2 - 1;

                    stackForMerge.push(pairRun3.get());
                    stackForMerge.push(new PairRun(low, high-  low + 1));
                } else {
                    low = pairRun3.get().getIndex();
                    high = pairRun2.get().getIndex() + size2 - 1;
                    mid = low + size3 - 1;

                    stackForMerge.push(new PairRun(low, high - low + 1));
                    stackForMerge.push(pairRun1);
                }
            }
            mergeWithGallopingMode(low, mid, high);
        }
    }


    private int calculateMinRun(int n) {
        int r = 0;
        while (n >= 64) {
            r |= n & 1;
            n >>= 1;
        }
        return n + r;
    }

    void mergeWithGallopingMode(int low, int middle, int high) {

        T[] leftArray = Collections.copyToArrays(arr, low, middle - low + 1);
        T[] rightArray = Collections.copyToArrays(arr, middle + 1, high - middle);

        int leftSubArrCounter = 0;
        int rightSubArrCounter = 0;
        int arrCounter = low;

        while (leftSubArrCounter < leftArray.length && rightSubArrCounter < rightArray.length) {
            if (leftArray[leftSubArrCounter].compareTo(rightArray[rightSubArrCounter]) <= 0) {
                int endGalloping = searchEndPointForGalloping(leftArray, leftSubArrCounter, rightArray[rightSubArrCounter]);;
                while (leftSubArrCounter < endGalloping)
                    arr.set(arrCounter++, leftArray[leftSubArrCounter++]);

            } else {
                int endGalloping = searchEndPointForGalloping(rightArray, rightSubArrCounter, leftArray[leftSubArrCounter]);
                while (rightSubArrCounter < endGalloping)
                    arr.set(arrCounter++, rightArray[rightSubArrCounter++]);
            }
        }

        while (leftSubArrCounter < leftArray.length) {
            arr.set(arrCounter++, leftArray[leftSubArrCounter++]);
        }

        while (rightSubArrCounter < rightArray.length) {
            arr.set(arrCounter++, rightArray[rightSubArrCounter++]);
        }
    }

    private int searchEndPointForGalloping(T[] arr, int idx, T target) {
        int gallopValue = 1;
        while (idx < arr.length) {
            if (arr[idx].compareTo(target) <= 0) {
                idx += gallopValue;
            } else {
                return idx - gallopValue / 2 + 1;
            }

            gallopValue *= 2;
        }
        return idx - gallopValue / 2 + 1;
    }

}
