package br.com.univali;

record Attribute(int value) {
    Attribute {
        int minimum = 0;
        int maximum = 100;
        if (value < minimum || value > maximum) {
            throw new IllegalArgumentException("Value needs to be higher than " + minimum
                    + " and lower than " + maximum);
        }
    }
}
