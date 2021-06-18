package br.com.univali;

class Goalkeeper {
    private final Height height;
    private final Attribute reflexes;

    Goalkeeper(Height height, Attribute reflexes) {
        this.height = height;
        this.reflexes = reflexes;
    }

    float calculateSkill() {
        return (height.normalize() * 4 + reflexes.value() * 6) / 10f;
    }
}
