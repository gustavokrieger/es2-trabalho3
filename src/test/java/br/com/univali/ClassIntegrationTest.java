package br.com.univali;

import org.junit.jupiter.api.Test;

class ClassIntegrationTest {
  private Player dida;
  private Player cafu;
  private Player cicinho;
  private Player adriano;
  private Player ronaldo;
  private Player robertoAbbondanzieri;
  private Player robertoAyala;
  private Player fabricioColoccini;
  private Player javierSaviola;
  private Player lionelMessi;

  private Team brazil;
  private Team argentina;

  // TODO: finish test.
  @Test
  void testCompleteExecution() {
    createPlayers();
    createTeams();
  }

  private void createPlayers() {
    dida =
        new Player(
            "Dida", 32, Position.GOALKEEPER, Attribute.fromScore(100), Attribute.fromHeight(196));
    cafu =
        new Player("Cafu", 36, Position.DEFENDER, Attribute.fromScore(0), Attribute.fromScore(50));
    cicinho =
        new Player(
            "Cicinho", 33, Position.DEFENDER, Attribute.fromScore(50), Attribute.fromScore(50));
    adriano =
        new Player(
            "Adriano", 24, Position.FORWARD, Attribute.fromScore(41), Attribute.fromScore(74));
    ronaldo =
        new Player(
            "Ronaldo", 29, Position.FORWARD, Attribute.fromScore(0), Attribute.fromScore(50));
    robertoAbbondanzieri =
        new Player(
            "Roberto Abbondanzieri",
            33,
            Position.GOALKEEPER,
            Attribute.fromScore(0),
            Attribute.fromHeight(196));
    robertoAyala =
        new Player(
            "Roberto Ayala", 33, Position.DEFENDER, Attribute.fromScore(0), Attribute.fromScore(0));
    fabricioColoccini =
        new Player(
            "Fabricio Coloccini",
            24,
            Position.DEFENDER,
            Attribute.fromScore(50),
            Attribute.fromScore(50));
    javierSaviola =
        new Player(
            "Javier Saviola",
            24,
            Position.FORWARD,
            Attribute.fromScore(41),
            Attribute.fromScore(74));
    lionelMessi =
        new Player(
            "Lionel Messi", 18, Position.FORWARD, Attribute.fromScore(90), Attribute.fromScore(90));
  }

  private void createTeams() {
    brazil =
        new Team.Builder("Brazil")
            .player("1", dida)
            .player("2", cafu)
            .player("6", cicinho)
            .player("7", adriano)
            .player("9", ronaldo)
            .build();
    argentina =
        new Team.Builder("Argentina")
            .player("1", robertoAbbondanzieri)
            .player("2", robertoAyala)
            .player("4", fabricioColoccini)
            .player("7", javierSaviola)
            .player("19", lionelMessi)
            .build();
  }
}
