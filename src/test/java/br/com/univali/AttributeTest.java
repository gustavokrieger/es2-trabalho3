package br.com.univali;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AttributeTest {
    @Test
    void testValueLowerThanMinimum() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new Attribute(-1));
        assertEquals("Value needs to be higher than 0 and lower than 100", exception.getMessage());
    }

    @Test
    void testValueHigherThanMaximum() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new Attribute(101));
        assertEquals("Value needs to be higher than 0 and lower than 100", exception.getMessage());
    }
}
