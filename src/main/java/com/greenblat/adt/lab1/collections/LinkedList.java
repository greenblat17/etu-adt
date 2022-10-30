package com.greenblat.adt.lab1.collections;

import java.util.EmptyStackException;

/**
 * Изначальный список: []
 * Вызвал метод add в цикле for 10 раз: [ 0 1 2 3 4 5 6 7 8 9 ]
 * Вызвал set(3, 4): [ 0 1 2 4 3 4 5 6 7 8 9 ]
 * Вызвал contains(5): true
 * Вызвал contains(123): false
 * Вызвал indexOf(5): 6
 * Вызвал indexOf(123): -1
 * Вызвал remove(9): [ 0 1 2 4 3 4 5 6 7 9 ], 9 - индекс
 * Вызвал remove(5): [ 0 1 2 4 3 4 6 7 9 ], 5 - Object
 * Вызвал remove(-1): Exception in thread "main" java.lang.IndexOutOfBoundsException: Index out of range: -1
 * Вызвал size(): 9
 * Вызвал clear: []
 * Вызвал isEmpty(): true
 */

public class LinkedList<E> implements List<E>, Stack<E> {

    private int size = 0;
    private Node<E> head;


    @Override
    public int size() {
        return this.size;
    }

    @Override
    public E push(E item) {
        add(item, 0);
        size++;

        return item;
    }

    @Override
    public E pop() {
        return remove(0);
    }

    @Override
    public E peek() {
        if (head == null)
            throw new EmptyStackException();

        return head.value;
    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public boolean add(E value) {

        if (head == null) {
            head = new Node<>(value, null);
        } else {
            Node<E> temp = head;

            while (temp.next != null) {
                temp = temp.next;
            }

            temp.next = new Node<>(value, null);
        }

        size++;
        return true;
    }

    @Override
    public boolean add(E value, int index) {

        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException(index);

        if (index == 0) {
            head = new Node<>(value, head);
            size++;
            return true;
        } else {
            Node<E> temp = head;
            int currIdx = 0;
            while (temp.next != null) {
                if (currIdx + 1 == index) {
                    Node<E> next = temp.next;
                    temp.next = new Node<>(value, next);
                    size++;
                    return true;
                }
                currIdx++;
                temp = temp.next;
            }
        }

        return false;
    }

    @Override
    public boolean remove(Object o) {
        Node<E> temp = new Node<>(null, head);
        while (temp.next != null) {
            if (temp.next.value.equals(o)) {
                temp.next = temp.next.next;
                size--;
                return true;
            }
            temp = temp.next;
        }

        return false;
    }

    @Override
    public E remove(int index) {
        if (index == 0) {
            E result = head.value;
            head = head.next;
            size--;
            return result;
        }

        Node<E> previous = null;
        int currIdx = 0;
        Node<E> temp = head;
        while (temp != null) {
            if (currIdx == index) {
                E result = temp.value;;
                previous.next = temp.next;
                size--;
                return result;
            }

            previous = temp;
            temp = temp.next;
            currIdx++;

        }

        throw new IndexOutOfBoundsException(index);
    }

    @Override
    public E get(int index) {
        int currIdx = 0;
        Node<E> temp = head;

        while (temp != null) {
            if (currIdx == index) {
                return temp.value;
            }

            temp = temp.next;
            currIdx++;
        }

        throw new IndexOutOfBoundsException(index);
    }

    public E set(int index, E element) {
        int currIdx = 0;
        Node<E> temp = head;
        while (temp != null) {
            if (currIdx == index) {
                temp.value = element;
                return element;
            }

            currIdx++;
            temp = temp.next;
        }

        throw new IndexOutOfBoundsException(index);
    }

    @Override
    public boolean contains(Object o) {
        return indexOf(o) != -1;
    }

    @Override
    public int indexOf(Object o) {
        int currIdx = 0;
        Node<E> temp  = head;

        while (temp != null) {
            if (temp.value == o)
                return currIdx;

            temp = temp.next;
            currIdx++;
        }

        return -1;
    }

    @Override
    public void clear() {
        Node<E> temp = head;
        while (temp != null) {
            Node<E> next = temp.next;
            temp.next = null;
            temp.value = null;
            temp = next;
        }
        head = null;
        size = 0;
    }

    @Override
    public void sort() {

    }

    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node<E> temp = head;
        while (temp.next != null) {
            sb.append(temp.value).append(", ");
            temp = temp.next;
        }
        return sb.append(temp.value).append("]").toString();
    }


    private static class Node<E> {
        Node<E> next;
        E value;


        public Node(E value, Node<E> next) {
            this.value = value;
            this.next = next;
        }
    }

}
