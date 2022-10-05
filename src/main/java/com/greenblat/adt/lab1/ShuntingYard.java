package com.greenblat.adt.lab1;

import com.greenblat.adt.lab1.collections.ArrayList;
import com.greenblat.adt.lab1.collections.LinkedList;
import com.greenblat.adt.lab1.collections.List;
import com.greenblat.adt.lab1.collections.Stack;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Ввод: 2 + 2
 * Вывод: 2 2 +
 *
 * Ввод: ( 4 + 7 ) + 12 / 3
 * Вывод: 4 7 + 12 3 / +
 *
 * Вывод: 6 * 3 - ( 4 - 5 ) + 2
 * Вывод: 6 3 * 4 5 - - 2 +
 *
 * Ввод: ( 7 + 3 ) / 3 + ( 8 - 3 ) / ( 3 * 2 )
 * Вывод: 7 3 + 3 / 8 3 - 3 2 * / +
 *
 * Ввод: ( 8 + 2 * 5 ) / ( 1 + 3 * 2 - 4 )
 * Вывод: 8 2 5 * + 1 3 2 * 4 - + /
 *
 * Ввод: 2 + sin ( 5 + 3 ) * 8 + ( 9 * 7 )
 * Ввод: 2 5 3 + sin 8 * 9 7 * + +
 */

public class ShuntingYard {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println(polishNotation(sc.nextLine()));
    }

    public static String polishNotation(String input) {
        String[] tokens = input.split(" ");

        System.out.println("Tokens are: " + Arrays.toString(tokens));

        Stack<String> stack = new LinkedList<>();
        List<String> print = new ArrayList<>();

        for (String token : tokens) {
            if (token.matches("\\d+")) {
                print.add(token);
            } else if (token.equals(")")) {
                String lastElement = stack.pop();
                while (!lastElement.equals("(")) {
                    print.add(lastElement);
                    lastElement = stack.pop();
                }
            } else if (token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/") || token.equals("^")) {
                String lastElement = stack.peek();

                if (lastElement != null) {
                    int lastElementValue = switch (lastElement) {
                        case "+", "-" -> 1;
                        case "*", "/" -> 2;
                        case "^" -> 3;
                        default -> 0;
                    };

                    int tokenValue =  switch (token){
                        case "*", "/" -> 2;
                        case "^" -> 3;
                        default -> 1;
                    };

                    if (!lastElement.equals("(") && (lastElementValue == 0 || lastElementValue >= tokenValue)) {
                        print.add(stack.pop());
                    }

                }
                stack.push(token);
            } else {
                stack.push(token);
            }
        }

        while (!stack.isEmpty()) {
            print.add(stack.pop());
        }

        return print.toString();
    }
}
