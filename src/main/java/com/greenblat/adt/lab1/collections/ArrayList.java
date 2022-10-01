package com.greenblat.adt.lab1.collections;

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

public class ArrayList<E> implements List<E> {

    private final int DEFAULT_CAPACITY = 10;

    private Object[] values;
    private int size = 0;

    public ArrayList(int capacity) {
        values = new Object[capacity];
    }

    public ArrayList() {
        values = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public boolean add(E e) {
        if (size == values.length) {
            values = Arrays.createNewArrayWithCopy(values);
        }

        values[size++] = e;
        return true;
    }

    @Override
    public boolean add(E e, int index) {
        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException(index);

        if (size == values.length) {
            values = Arrays.createNewArrayWithCopy(values);
        }

        for (int i = size; i > index; i--) {
            values[i] = values[i - 1];
        }
        values[index] = e;

        size++;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        boolean isReadyToRemove = false;
        for (int i = 0; i < size; i++) {
            if (values[i].equals(o))
                isReadyToRemove = true;

            if (isReadyToRemove) {
                if (i == size - 1)
                    values[i] = null;
                else
                    values[i] = values[i + 1];
            }

        }

        if (isReadyToRemove) {
            --size;
        }
        return isReadyToRemove;
    }

    @Override
    public E remove(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException(index);

        Object result = values[index];
        for (int i = index; i < size - 1; i++) {
            values[i] = values[i + 1];
        }

        size--;
        return (E) result;
    }

    @Override
    public E get(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException(index);
        return (E) values[index];
    }

    @Override
    public E set(int index, E element) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException(index);

        values[index] = element;
        return element;
    }

    @Override
    public boolean contains(Object o) {
        for (int i = 0; i < size; i++) {
            if (values[i].equals(o))
                return true;
        }
        return false;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < size; i++) {
            if (values[i].equals(o))
                return i;
        }
        return -1;
    }

    @Override
    public void clear() {
        values =  new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    @Override
    public void sort() {

    }

    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < size - 1; i++) {
            sb.append(values[i]).append(", ");
        }
        return sb.append(values[size - 1]).append("]").toString();
    }

}

