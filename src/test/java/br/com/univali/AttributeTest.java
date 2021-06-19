package br.com.univali;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AttributeTest {
    @Test
    void testConstructionWithValueLowerThanMinimum() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                Attribute.fromScore(-1));
        assertEquals("Value needs to be at least 0 and at most 100", exception.getMessage());
    }

    @Test
    void testConstructionWithValueHigherThanMaximum() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                Attribute.fromScore(101));
        assertEquals("Value needs to be at least 0 and at most 100", exception.getMessage());
    }
}
