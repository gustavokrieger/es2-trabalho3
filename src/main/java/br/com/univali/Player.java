package br.com.univali;

class Player {
    private final Position position;
    private final Attribute primary;
    private final Attribute secondary;

    Player(Position position, Attribute primary, Attribute secondary) {
        this.position = position;
        this.primary = primary;
        this.secondary = secondary;
    }

    Position getPosition() {
        return position;
    }

    int calculateSkill() {
        return (primary.getScore() * 6 + secondary.getScore() * 4) / 10;
    }
}
