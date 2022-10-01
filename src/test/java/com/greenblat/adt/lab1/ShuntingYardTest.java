package com.greenblat.adt.lab1;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;


class ShuntingYardTest {

    @Test
    void digitPlusDigit() {
        assertEquals("[2, 2, +]", ShuntingYard.polishNotation("2 + 2"));
    }

    @Test
    void operationsWithParenthesis() {
        assertEquals("[7, 3, +, 3, /, 8, 3, -, 3, 2, *, /, +]", ShuntingYard.polishNotation("( 7 + 3 ) / 3 + ( 8 - 3 ) / ( 3 * 2 )"));
    }

    @Test
    void operationsWithParenthesisAndSin() {
        assertEquals("[2, 5, 3, +, sin, 8, *, 9, 7, *, +, +]", ShuntingYard.polishNotation("2 + sin ( 5 + 3 ) * 8 + ( 9 * 7 )"));
    }

    @Test
    void xorExpression() {
        assertEquals("[2, 3, 4, ^, 5, ^, +, cos]", ShuntingYard.polishNotation("cos ( 2 + 3 ^ 4 ^ 5 )"));
    }

}