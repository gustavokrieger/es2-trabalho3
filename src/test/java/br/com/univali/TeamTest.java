package br.com.univali;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TeamTest {
  private final Player player1 =
      new Player(
          "Dida", 32, Position.GOALKEEPER, Attribute.fromScore(100), Attribute.fromHeight(196));
  private final Player player2 =
      new Player("Cafu", 36, Position.DEFENDER, Attribute.fromScore(0), Attribute.fromScore(50));
  private final Player player3 =
      new Player(
          "Cicinho", 33, Position.DEFENDER, Attribute.fromScore(50), Attribute.fromScore(50));
  private final Player player4 =
      new Player("Adriano", 24, Position.FORWARD, Attribute.fromScore(41), Attribute.fromScore(74));
  private final Player player5 =
      new Player("Ronaldo", 29, Position.FORWARD, Attribute.fromScore(0), Attribute.fromScore(50));

  @Test
  void testAddPlayersWithDuplicateNumbers() {
    Team.Builder teamBuilder = new Team.Builder("Classic Brazil").player("1", player1);
    Exception exception =
        assertThrows(IllegalArgumentException.class, () -> teamBuilder.player("1", player2));
    assertEquals("There cannot be players with duplicate numbers", exception.getMessage());
  }

  @Test
  void testBuildWithoutEnoughPositions() {
    Team.Builder teamBuilder = new Team.Builder("Classic Brazil").player("1", player1);
    Exception exception = assertThrows(IllegalArgumentException.class, teamBuilder::build);
    assertEquals("There should be 1 GOALKEEPER, 2 DEFENDER and 2 FORWARD", exception.getMessage());
  }

  @Test
  void testBuildInvalidQuantityInPositions() {
    Team.Builder teamBuilder =
        new Team.Builder("Classic Brazil")
            .player("1", player1)
            .player("2", player2)
            .player("6", player4);
    Exception exception = assertThrows(IllegalArgumentException.class, teamBuilder::build);
    assertEquals("There should be 1 GOALKEEPER, 2 DEFENDER and 2 FORWARD", exception.getMessage());
  }

  @Nested
  class AfterBuild {
    private Team team;

    @BeforeEach
    void build() {
      team =
          new Team.Builder("Classic Brazil")
              .player("1", player1)
              .player("2", player2)
              .player("6", player3)
              .player("7", player4)
              .player("9", player5)
              .build();
    }

    // TODO: add successful replace tests.

    @Test
    void testReplaceNonexistent() {
      Exception exception =
          assertThrows(IllegalArgumentException.class, () -> team.replace("-1", "1", player1));
      assertEquals("No existing player with such number", exception.getMessage());
    }

    @Test
    void testReplaceInvalidNewPosition() {
      Exception exception =
          assertThrows(IllegalArgumentException.class, () -> team.replace("1", "2", player2));
      assertEquals(
          "The position of the new player needs to be the same as the one being replaced",
          exception.getMessage());
    }

    @Test
    void testReplaceWithExistingNumber() {
      Exception exception =
          assertThrows(IllegalArgumentException.class, () -> team.replace("2", "6", player2));
      assertEquals("There cannot be players with duplicate numbers", exception.getMessage());
    }
  }
}
