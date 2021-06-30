package br.com.univali;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TeamStatisticsTest {
  private TeamStatistics teamStatistics;

  @BeforeEach
  void init() {
    teamStatistics = new TeamStatistics();
  }

  @Test
  void testAddWin() {
    teamStatistics.addWin();
    assertEquals(1, teamStatistics.getWins());
    assertEquals(3, teamStatistics.getScore());
  }

  @Test
  void testAddTie() {
    teamStatistics.addTie();
    assertEquals(1, teamStatistics.getTies());
    assertEquals(1, teamStatistics.getScore());
  }

  @Test
  void testAddLoss() {
    teamStatistics.addLoss();
    assertEquals(1, teamStatistics.getLosses());
    assertEquals(0, teamStatistics.getScore());
  }

  @Test
  void testScoreGoals() {
    teamStatistics.scoreGoals(1);
    teamStatistics.scoreGoals(2);
    assertEquals(3, teamStatistics.getGoals());
  }

  @Test
  void testScoreNegativeGoals() {
    Exception exception =
        assertThrows(IllegalArgumentException.class, () -> teamStatistics.scoreGoals(-1));
    assertEquals("Cannot score negative goals", exception.getMessage());
  }
}
