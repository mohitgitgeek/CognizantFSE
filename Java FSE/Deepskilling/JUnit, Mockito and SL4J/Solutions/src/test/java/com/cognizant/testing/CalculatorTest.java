package com.cognizant.testing;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class CalculatorTest {
    private Calculator calculator;
    @BeforeEach void setUp() { calculator = new Calculator(); }
    @Test void addsTwoIntegers() { assertEquals(7, calculator.add(3, 4)); }
    @Test void divisionByZeroHasUsefulError() { assertThrows(IllegalArgumentException.class, () -> calculator.divide(1, 0)); }
    @ParameterizedTest
    @CsvSource({"8,2,4", "9,3,3"})
    void dividesIntegers(int dividend, int divisor, int expected) { assertEquals(expected, calculator.divide(dividend, divisor)); }
}
