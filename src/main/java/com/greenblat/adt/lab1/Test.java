package com.greenblat.adt.lab1;


import com.greenblat.adt.lab1.collections.ArrayList;
import com.greenblat.adt.lab1.collections.LinkedList;
import com.greenblat.adt.lab1.collections.List;
import com.greenblat.adt.lab1.collections.Stack;

public class Test {

    public static void main(String[] args) {
        System.out.println("Testing LinkedList");
        List<Integer> list = new LinkedList<>();
        checkList(list);

        System.out.println("Testing ArrayList");
        list = new ArrayList<>();
        checkList(list);

        System.out.println("Testing Stack");
        Stack<Integer> stack = new LinkedList<>();
        for (int i = 0; i < 5; i++) {
            stack.push(i);
        }
        System.out.println("add 5 elements: " + stack);
        System.out.println("peek(): " + stack.peek());
        System.out.println("pop(): " + stack.pop());

        System.out.println("delete all elements");
        while (!stack.isEmpty()) {
            System.out.println(stack.pop());
        }
    }

    private static void checkList(List<Integer> list) {
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
        System.out.println("add elements: " + list);

        list.set(3, 4);
        System.out.println("insert 4 for third index: " + list);
        list.set(0, -1);
        System.out.println("insert -1 to head:" + list);

        System.out.println("check contains 5 in list: " + list.contains(5));
        System.out.println("check contains 123 in list: " + list.contains(123));
        System.out.println("index of 5: " + list.indexOf(5));
        System.out.println("index of 123: " + list.indexOf(123));

        list.remove( 9);
        System.out.println("remove element with index 9: " + list);
        list.remove((Object) 5);
        System.out.println("remove element with value 5: " + list);

        System.out.println("size of list: " + list.size());
        list.clear();
        System.out.println("list is empty: " + list.isEmpty());

        System.out.println("--------------------------");
    }

}
