package br.com.univali;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AttributeTest {
    @Test
    void testScoreLowerThanMinimum() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                Attribute.fromScore(-1));
        assertEquals("Value needs to be at least 0 and at most 100", exception.getMessage());
    }

    @Test
    void testScoreHigherThanMaximum() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                Attribute.fromScore(101));
        assertEquals("Value needs to be at least 0 and at most 100", exception.getMessage());
    }

    @Test
    void testHeightLowerThanMinimum() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                Attribute.fromHeight(-1));
        assertEquals("Value needs to be at least 0", exception.getMessage());
    }

    @Test
    void testMaximumHeight() {
        Attribute attribute = Attribute.fromHeight(210);
        assertEquals(100, attribute.getScore());
    }

    @Test
    void testHeightConversionRoundUp() {
        Attribute attribute = Attribute.fromHeight(104);
        assertEquals(50, attribute.getScore());
    }

    @Test
    void testHeightConversionRoundDown() {
        Attribute attribute = Attribute.fromHeight(106);
        assertEquals(50, attribute.getScore());
    }
}
