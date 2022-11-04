package com.greenblat.adt.lab2.sorts;


import com.greenblat.adt.lab1.collections.List;
import com.greenblat.adt.lab1.collections.utils.Collections;
import com.greenblat.adt.lab2.utils.PairRun;

import java.util.Stack;


public class TimSort<T extends Comparable<T>> implements Sort<T> {

    private final List<T> arr;

    public TimSort(List<T> arr) {
        this.arr = arr;
    }

    public void sort() {
        int minRun = calculateMinRun(arr.size());
        Stack<PairRun> stackForMerge = new java.util.Stack<>();

        int startRun = 0;
        while (startRun < arr.size()) {
            int endRun = countRunLengthAndMakeAscendingSequence(startRun, arr.size());

            if (endRun - startRun < minRun) {
                endRun = Math.min(startRun + minRun - 1, arr.size() - 1);
                insertionBinarySort(startRun, endRun);
            }
            stackForMerge.push(new PairRun(startRun, endRun - startRun + 1));

            mergeTopElements(stackForMerge);

            startRun = endRun + 1;

        }

    }


    private void mergeTopElements(Stack<PairRun> stackForMerge) {
        
        while (stackForMerge.size() > 1) {
            PairRun pairRunX = stackForMerge.pop();
            int[] infoRunX = {pairRunX.getIndex(), pairRunX.getSize()};
            PairRun pairRunY = stackForMerge.pop();
            int[] infoRunY = {pairRunY.getIndex(), pairRunY.getSize()};

            if (stackForMerge.size() > 0) {
                PairRun pairRunZ = stackForMerge.pop();
                int[] infoRunZ = {pairRunZ.getIndex(), pairRunZ.getSize()};

                if (infoRunZ[1] <= infoRunY[1] + infoRunX[1]) {
                    if (infoRunZ[1] < infoRunX[1]) {
                        mergeWithGallopingMode(pairRunY, pairRunX);

                        stackForMerge.push(pairRunZ);
                        stackForMerge.push(new PairRun(pairRunY.getIndex(), pairRunY.getSize() + pairRunX.getSize()));
                    } else {
                        mergeWithGallopingMode(pairRunZ, pairRunY);

                        stackForMerge.push(new PairRun(pairRunZ.getIndex(), pairRunZ.getSize() + pairRunY.getSize()));
                        stackForMerge.push(pairRunX);
                    }
                } else if (infoRunY[1] <= infoRunX[1]) {
                    mergeWithGallopingMode(pairRunY, pairRunX);

                    stackForMerge.push(pairRunZ);
                    stackForMerge.push(new PairRun(pairRunY.getIndex(), pairRunY.getSize() + pairRunX.getSize()));
                }
            } else {
                mergeWithGallopingMode(pairRunY, pairRunX);

                stackForMerge.push(new PairRun(pairRunY.getIndex(), pairRunY.getSize() + pairRunX.getSize()));
            }

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

    private int countRunLengthAndMakeAscendingSequence(int low, int high) {
        int idx = low + 1;
        if (arr.get(idx++).compareTo(arr.get(low)) < 0) {
            while (idx < high && arr.get(idx).compareTo(arr.get(low)) < 0)
                idx++;
            Collections.reverseList(arr, low, idx);
        } else {
            while (idx < high && arr.get(idx).compareTo(arr.get(idx - 1)) >= 0)
                idx++;
        }

        return idx - low;
    }

    private void mergeWithGallopingMode(PairRun pairRun1, PairRun pairRun2) {

        T[] leftArray = Collections.copyToArrays(arr, pairRun1.getIndex(), pairRun1.getSize());
        T[] rightArray = Collections.copyToArrays(arr, pairRun2.getIndex(), pairRun2.getSize());

        int leftSubArrCounter = 0;
        int rightSubArrCounter = 0;
        int arrCounter = pairRun1.getIndex();

        while (leftSubArrCounter < leftArray.length && rightSubArrCounter < rightArray.length) {
            if (leftArray[leftSubArrCounter].compareTo(rightArray[rightSubArrCounter]) <= 0) {
                int endGalloping = searchEndPointForGalloping(leftArray, leftSubArrCounter, rightArray[rightSubArrCounter]);
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

    private void insertionBinarySort(int start, int end) {
        for (int i = start + 1; i <= end; i++) {
            int j = i - 1;

            T selected = arr.get(i);
            int loc = Collections.binarySearch(arr, selected, start, j);
            while (j >= loc) {
                arr.set(j + 1, arr.get(j));
                j--;
            }
            arr.set(j + 1, selected);
        }
    }

}
