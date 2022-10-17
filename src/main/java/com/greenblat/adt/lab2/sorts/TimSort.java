package com.greenblat.adt.lab2.sorts;




import com.greenblat.adt.lab2.utils.PairRun;

import javax.swing.text.html.Option;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Stack;

public class TimSort<T extends Comparable<T>> {

    private final List<T> arr;

    public TimSort(List<T> arr) {
        this.arr = arr;
    }

    public void sort() {
        InsertionSort<T> insertionSort = new InsertionSort<>(arr);
        MergeSort<T> mergeSort = new MergeSort<>(arr);

//        int minrun = calculateMinRun(arr.size());
        int minrun = 5;
        Stack<PairRun> stack = new Stack<>();

        // step 1 insertion
        int i = 0;
        while (i <  arr.size()) {
            int j = Math.min(i + minrun - 1, arr.size() - 1);
            while (j < arr.size() - 1 && arr.get(j).compareTo(arr.get(j + 1)) < 0) {
                j++;
            }
            insertionSort.sort(i, j);

            stack.push(new PairRun(i, j - i + 1));

            i = j + 1;
        }


        // step 2 merging
        while (!stack.isEmpty()) {
            PairRun pairRun1 = stack.pop();
            Optional<PairRun> pairRun2 = stack.isEmpty() ? Optional.empty() : Optional.ofNullable(stack.pop());
            Optional<PairRun> pairRun3 = stack.isEmpty() ? Optional.empty()  : Optional.ofNullable(stack.pop());

            if (pairRun2.isEmpty()) {
                break;
            }

            if (pairRun3.isEmpty()) {
                int start = pairRun2.get().getIndex();
                int end = pairRun1.getSize() + pairRun2.get().getSize() - 1;
                int mid = start + pairRun2.get().getSize();

                if (mid - start + 1 < 0 || end - mid < 0) {
                    System.out.println("skgjrigjjeqrg");
                }

                mergeSort.merge(start, mid, end);
            } else {
                int size1 = pairRun1.getSize();
                int size2 = pairRun2.get().getSize();
                int size3 = pairRun3.get().getSize();

                if (size3 > size2 + size1 && size2 > size1) {
                    int start = pairRun2.get().getIndex();
                    int end = pairRun1.getIndex() + pairRun1.getSize() - 1;
                    int mid = start + pairRun2.get().getSize();

                    if (mid - start + 1 < 0 || end - mid < 0) {
                        System.out.println("skgjrigjjeqrg");
                    }

                    mergeSort.merge(start, mid, end);
                } else {
                    int start = pairRun3.get().getIndex();
                    int end = pairRun2.get().getIndex() + pairRun2.get().getSize() - 1;
                    int mid = start + pairRun3.get().getSize();

                    if (mid - start + 1 < 0 || end - mid < 0) {
                        System.out.println("skgjrigjjeqrg");
                    }

                    mergeSort.merge(start, mid, end);
                }

            }
        }
    }

    private  int calculateMinRun(int n) {
        int r = 0;
        while (n >= 64) {
            r |= n & 1;
            n >>= 1;
        }
        return n + r;
    }
}
