package br.com.univali;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PlayerTest {
    private final Player player = new Player("Tsubasa",
            16,
            Position.FORWARD,
            Attribute.fromScore(100),
            Attribute.fromScore(100));

    @Test
    void testScoreGoal() {
        player.scoreGoal();
        assertEquals(1, player.getGoals());
    }

    @Test
    void testCalculateSkill() {
        assertEquals(100, player.calculateSkill());
    }
}
