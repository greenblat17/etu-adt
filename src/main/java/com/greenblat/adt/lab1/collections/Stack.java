package com.greenblat.adt.lab1.collections;

/**
 * Изначально: []
 * Вызвал метод push в цикле for 5 раз: [ 0 1 2 3 4 ]
 * Вызвал метод peek(): 4
 * Вызвал метод pop(): выведено 4, осталось [ 0 1 2 3 ]
 * Вызвал метод pop() в цикле while: []
 */

public interface Stack<E> {

    E push(E item);

    E pop();

    E peek();

    boolean isEmpty();
}
