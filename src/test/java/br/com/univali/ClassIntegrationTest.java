package br.com.univali;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
  private Player replacement;

  private Team brazil;
  private Team argentina;

  private Collection<MatchSimulator> matchSimulators;
  private Iterable<MatchSimulator.Result> matchSimulatorResults;

  @Test
  void testCompleteExecution() {
    createPlayers();
    createTeams();
    replacePlayer();
    addMatchSimulators();
    executeMatchSimulators();

    for (MatchSimulator.Result result : matchSimulatorResults) {
      assertTrue(result.homeGoals() >= 0);
      assertTrue(result.awayGoals() >= 0);
      assertNotNull(result.date());
    }
  }

  private void createPlayers() {
    dida =
        new Player(
            "Dida",
            LocalDate.of(1974, 9, 3),
            Position.GOALKEEPER,
            Attribute.fromScore(100),
            Attribute.fromHeight(196));
    cafu =
        new Player(
            "Cafu",
            LocalDate.of(1970, 3, 9),
            Position.DEFENDER,
            Attribute.fromScore(0),
            Attribute.fromScore(50));
    cicinho =
        new Player(
            "Cicinho",
            LocalDate.of(1973, 1, 2),
            Position.DEFENDER,
            Attribute.fromScore(50),
            Attribute.fromScore(50));
    adriano =
        new Player(
            "Adriano",
            LocalDate.of(1982, 2, 1),
            Position.FORWARD,
            Attribute.fromScore(41),
            Attribute.fromScore(74));
    ronaldo =
        new Player(
            "Ronaldo",
            LocalDate.of(1982, 2, 1),
            Position.FORWARD,
            Attribute.fromScore(0),
            Attribute.fromScore(50));
    robertoAbbondanzieri =
        new Player(
            "Roberto Abbondanzieri",
            LocalDate.of(1977, 1, 1),
            Position.GOALKEEPER,
            Attribute.fromScore(0),
            Attribute.fromHeight(196));
    robertoAyala =
        new Player(
            "Roberto Ayala",
            LocalDate.of(1973, 12, 12),
            Position.DEFENDER,
            Attribute.fromScore(0),
            Attribute.fromScore(0));
    fabricioColoccini =
        new Player(
            "Fabricio Coloccini",
            LocalDate.of(1982, 12, 12),
            Position.DEFENDER,
            Attribute.fromScore(50),
            Attribute.fromScore(50));
    javierSaviola =
        new Player(
            "Javier Saviola",
            LocalDate.of(1982, 9, 10),
            Position.FORWARD,
            Attribute.fromScore(41),
            Attribute.fromScore(74));
    lionelMessi =
        new Player(
            "Lionel Messi",
            LocalDate.of(1988, 9, 10),
            Position.FORWARD,
            Attribute.fromScore(90),
            Attribute.fromScore(90));
    replacement =
        new Player(
            "Pele",
            LocalDate.of(1940, 10, 23),
            Position.GOALKEEPER,
            Attribute.fromScore(100),
            Attribute.fromHeight(200));
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

  private void replacePlayer() {
    brazil.replace("1", "1", replacement);
  }

  private void addMatchSimulators() {
    MatchSimulator matchSimulator1 = new MatchSimulator(brazil, argentina);
    MatchSimulator matchSimulator2 = new MatchSimulator(argentina, brazil);
    matchSimulators = List.of(matchSimulator1, matchSimulator2);
  }

  private void executeMatchSimulators() {
    matchSimulatorResults = matchSimulators.stream().map(MatchSimulator::execute).toList();
  }
}
