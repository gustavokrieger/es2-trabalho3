package br.com.univali;


class Height {
    private static final int HIGHEST = 210;
    private static final int HIGHEST_NORMALIZED = 100;

    private final int value;

    Height(int value) {
        int minimum = 0;
        if (value < minimum) {
            throw new IllegalArgumentException("Value needs to be higher than " + minimum);
        }
        this.value = value;
    }

    int normalize() {
        if (value > HIGHEST) {
            return HIGHEST_NORMALIZED;
        }
        float scaled = scaleForNormalization();
        return (int) Math.rint(scaled);
    }

    private float scaleForNormalization() {
        return (float) value * HIGHEST_NORMALIZED / HIGHEST;
    }
}
