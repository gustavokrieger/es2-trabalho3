package br.com.univali;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AttributeTest {
    @Test
    void testConstructionWithValidValue() {
        int value = 50;
        Attribute attribute = new Attribute(value);
        assertEquals(value, attribute.value());
    }

    @Test
    void testConstructionWithValueLowerThanMinimum() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new Attribute(-1));
        assertEquals("Value needs to be at least 0 and at most 100", exception.getMessage());
    }

    @Test
    void testConstructionWithValueHigherThanMaximum() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new Attribute(101));
        assertEquals("Value needs to be at least 0 and at most 100", exception.getMessage());
    }
}
