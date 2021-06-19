package br.com.univali;


class Attribute {
    private static final int MINIMUM_SCORE = 0;
    private static final int MAXIMUM_SCORE = 100;
    private static final int MINIMUM_HEIGHT = 0;
    private static final int MAXIMUM_HEIGHT = 210;

    private final int score;

    private Attribute(int score) {
        this.score = score;
    }

    static Attribute fromScore(int score) {
        if (score < MINIMUM_SCORE || score > MAXIMUM_SCORE) {
            throw new IllegalArgumentException("Value needs to be at least " + MINIMUM_SCORE
                    + " and at most " + MAXIMUM_SCORE);
        }
        return new Attribute(score);
    }

    static Attribute fromHeight(int height) {
        if (height < MINIMUM_HEIGHT) {
            throw new IllegalArgumentException("Value needs to be at least " + MINIMUM_HEIGHT);
        }

        if (height >= MAXIMUM_HEIGHT) {
            return new Attribute(MAXIMUM_SCORE);
        }
        float score = (float) height * MAXIMUM_SCORE / MAXIMUM_HEIGHT;
        return new Attribute((int) Math.rint(score));
    }

    int getScore() {
        return score;
    }
}
