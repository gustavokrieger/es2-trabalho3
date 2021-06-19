package br.com.univali;

class Player {
    private int goals = 0;
    private final String name;
    private final int age;
    private final Position position;
    private final Attribute primary;
    private final Attribute secondary;

    Player(String name,
           int age,
           Position position,
           Attribute primary,
           Attribute secondary) {
        this.name = name;
        this.age = age;
        this.position = position;
        this.primary = primary;
        this.secondary = secondary;
    }

    int getGoals() {
        return goals;
    }

    Position getPosition() {
        return position;
    }

    void scoreGoal() {
        goals++;
    }

    int calculateSkill() {
        return (primary.getScore() * 6 + secondary.getScore() * 4) / 10;
    }
}
