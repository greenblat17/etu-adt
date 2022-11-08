package com.greenblat.adt.lab3.tree.binary;

import com.greenblat.adt.lab1.collections.LinkedList;
import com.greenblat.adt.lab1.collections.Stack;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Optional;
import java.util.Scanner;

public class BinaryTree{

    public static TreeNode<Integer> parseSequence() {
        TreeNode<Integer> result = new TreeNode<>(null);
        File file = new File("src/main/java/com/greenblat/adt/lab3/resources/test.txt");
        char[] sequence = readFile(file).toCharArray();

        Stack<Character> stack = new LinkedList<>();
        Stack<TreeNode<Integer>> elementsOfTree = new LinkedList<>();
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


    public static <T> void depthFirstSearch(TreeNode<T> node) {
        if (node != null) {
            depthFirstSearch(node.getLeft());
            System.out.print(node.getValue() + " ");
            depthFirstSearch(node.getRight());
        }
    }


}
