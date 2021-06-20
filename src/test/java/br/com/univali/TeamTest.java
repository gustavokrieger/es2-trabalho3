package br.com.univali;

import org.junit.jupiter.api.Test;

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

  // TODO: finish test.
  @Test
  void testBuild() {
    Team team =
        new Team.Builder("Classic Brazil")
            .player("1", player1)
            .player("2", player2)
            .player("6", player3)
            .player("7", player4)
            .player("9", player5)
            .build();
  }
}
