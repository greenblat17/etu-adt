package com.greenblat.adt.lab3.tree.util;

import com.greenblat.adt.lab1.collections.LinkedList;
import com.greenblat.adt.lab1.collections.Stack;
import com.greenblat.adt.lab3.tree.binary.TreeNode;
import com.greenblat.adt.lab3.tree.exception.IncorrectBraceException;
import com.greenblat.adt.lab3.tree.exception.IncorrectSymbolException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Trees {

    public static TreeNode<Integer> parseSequence(String fileName) throws IncorrectBraceException, IncorrectSymbolException {
        char[] sequence = readFile(fileName).toCharArray();
        Stack<TreeNode<Integer>> stack = new LinkedList<>();

        if (sequence[0] != '(')
            throw new IncorrectBraceException("Sequence of brackets is incorrect");

        int idx = 1;
        char last = '(';
        int countOpenBrackets = 0;
        while (idx < sequence.length - 1) {
            if (Character.isDigit(sequence[idx])) {
                if (last != '(')
                    throw new IncorrectBraceException("Sequence of brackets is incorrect");

                StringBuilder number = new StringBuilder();
                while ('0' <= sequence[idx] && sequence[idx] <= '9') {
                    number.append(sequence[idx++]);
                }
                stack.push(new TreeNode<>(Integer.parseInt(number.toString())));
                idx -= 1;


                last = '1';
            } else if (sequence[idx] == ')') {
                if (last == '(' || --countOpenBrackets < 0)
                    throw new IncorrectBraceException("Sequence of brackets is incorrect");


                TreeNode<Integer> child = stack.pop();

                TreeNode<Integer> parent = stack.peek();

                if (parent.getLeft() == null)
                    parent.setLeft(child);
                else
                    parent.setRight(child);

                last = ')';
            } else {
                if (sequence[idx] != '(')
                    throw new IncorrectSymbolException("Incorrect symbol in sequence: " + sequence[idx]);
                if (last == '(')
                    throw new IncorrectBraceException("Sequence of brackets is incorrect");
                countOpenBrackets++;
                last = sequence[idx];
            }
            idx++;
        }


        return stack.pop();
    }


    private static String readFile(String fileName) {
        File file = new File("src/main/java/com/greenblat/adt/lab3/resources/" + fileName);
        String data;
        try {
            Scanner reader = new Scanner(file);
            data = reader.nextLine();
            return data;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
