package com.greenblat.adt.lab1.collections;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArrayListTest {

    private final List<Integer> list = new ArrayList<>();

    @BeforeEach
    void addElementsToList() {
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
    }

    @AfterEach
    void clearList() {
        list.clear();
    }

    @Test
    void listShouldBeNotEmpty() {
        assertFalse(list.isEmpty());
    }

    @Test
    void itShouldAddElement() {
        list.add(20);
        assertEquals(20, list.get(list.size() - 1));
        assertEquals(11, list.size());
    }

    @Test
    void itShouldRemoveElement() {
        list.remove(5);

        assertEquals(6, list.get(5));
        assertEquals(7 ,list.get(6));
        assertEquals(9, list.size());
        assertThrows(IndexOutOfBoundsException.class, () -> list.remove(123));

        assertTrue(list.remove((Object) 2));
        assertFalse(list.remove((Object) 123));
    }


    @Test
    void itShouldGetElementByIndex() {
        assertEquals(2, list.get(2));
        assertThrows(IndexOutOfBoundsException.class, () -> list.remove(123));
    }

    @Test
    void itShouldInsertElementByIndex() {
        list.set(3, 4);

        assertEquals(4, list.get(3));
    }

    @Test
    void isListContainsElement() {
        assertTrue(list.contains(5));

        assertFalse(list.contains(123));
        assertFalse(list.contains(-3));
    }

    @Test
    void itShouldReturnIndexByElement() {
        assertEquals(2, list.indexOf(2));

        assertEquals(-1, list.indexOf(123));
        assertEquals(-1, list.indexOf(-3));
    }

}