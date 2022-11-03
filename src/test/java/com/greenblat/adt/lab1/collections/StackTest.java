package com.greenblat.adt.lab1.collections;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.EmptyStackException;

import static org.junit.jupiter.api.Assertions.*;

class StackTest {

    private final Stack<Integer> stack = new LinkedList<>();

    @AfterEach
    void clearStack() {
        while (!stack.isEmpty()) {
            stack.pop();
        }
    }

    @Test
    void isShouldPushElement() {
        assertEquals(1, stack.push(1));
    }

    @Test
    void itShouldPeekElement() {
        stack.push(1);
        stack.push(5);
        assertEquals(5, stack.peek());
        assertEquals(5, stack.peek());

        stack.pop();
        stack.pop();

        assertThrows(EmptyStackException.class, stack::peek);
    }

    @Test
    void itShouldPopElement() {
        for (int i = 0; i < 5; i++) {
            stack.push(i);
        }

        assertEquals(4, stack.pop());
        assertEquals(3, stack.peek());

        while (!stack.isEmpty()) {
            stack.pop();
        }

//        assertThrows(stack.pop());
    }

}