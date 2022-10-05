package com.greenblat.adt.lab1.collections;

public interface List<E> {
    int size();

    boolean isEmpty();

    boolean add(E e);

    boolean add(E e, int index);

    boolean remove(Object o);

    E remove(int index);

    E get(int index);

    E set(int index, E element);

    boolean contains(Object o);

    int indexOf(Object o);

    void clear();

    void sort();

}
