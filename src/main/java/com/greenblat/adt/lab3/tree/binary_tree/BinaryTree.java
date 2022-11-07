package com.greenblat.adt.lab3.binary_tree;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class BinaryTree {

    private static TreeNode<Integer> parseSequence() {
        TreeNode<Integer> result = new TreeNode<>(null);
        File file = new File("src/main/java/com/greenblat/adt/lab3/test.txt");
        char[] sequence = readFile(file).toCharArray();

        Stack<Character> stack = new Stack<>();
        Stack<TreeNode<Integer>> elementsOfTree = new Stack<>();
        char last = '(';
        int idx = 0;
        while (idx < sequence.length) {
            if (sequence[idx] == '(') {
                stack.push(sequence[idx++]); // (
                stack.push(sequence[idx++]); // 0-9
                last = sequence[idx++];

                if (last != ' ')
                    stack.push(last);
            } else if (sequence[idx] == ')' && last == ')') {
                while (true) {
                    char el = stack.pop();
                    if (el == ')') {
                        elementsOfTree.push(new TreeNode<>(stack.pop() - '0'));
                        stack.pop();
                    } else if ('0' <= el && el <= '9') {
                        TreeNode<Integer> node = new TreeNode<>(el - '0');

                        Optional<TreeNode<Integer>> child1 = Optional.ofNullable(elementsOfTree.pop());
                        Optional<TreeNode<Integer>> child2 = elementsOfTree.isEmpty() ? Optional.empty() : Optional.ofNullable(elementsOfTree.pop());

                        if (child2.isPresent()) {
                            node.setRight(child1.get());
                            node.setLeft(child2.get());
                        } else {
                            node.setLeft(child1.get());
                        }

                        elementsOfTree.push(node);
                        stack.pop();
                        last = '(';
                        idx++;
                        break;
                    }
                }
            } else idx++;
        }

        if (!stack.isEmpty()) {
            throw new IllegalStateException("некорректно введеное значение");
        }

        return elementsOfTree.pop();
    }

    private static String readFile(File file) {
        String data;
        try {
            Scanner reader = new Scanner(file);
            data = reader.nextLine();
            return data;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T extends Comparable<T>> void depthFirstSearch(TreeNode<T> node) {
        if (node.getLeft() != null) {
            depthFirstSearch(node.getLeft());
        }

        if (node.getRight() != null) {
            depthFirstSearch(node.getRight());
        }

        System.out.print(node.getData() + " ");

//        if (node != null) {
//            depthFirstSearch(node.getLeft());
//            System.out.print(node.getData() + " ");
//            depthFirstSearch(node.getRight());
//        }
    }

    
}
