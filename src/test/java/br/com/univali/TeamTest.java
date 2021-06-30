package br.com.univali;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TeamTest {
  @Nested
  class BeforeBuild {
    private Team.Builder teamBuilder;

    @BeforeEach
    void init() {
      teamBuilder = new Team.Builder("Brazil");
    }

    @Test
    void testAddPlayersWithDuplicateNumbers(@Mock Player player1, @Mock Player player2) {
      teamBuilder.player("1", player1);
      Exception exception =
          assertThrows(IllegalArgumentException.class, () -> teamBuilder.player("1", player2));
      assertEquals("There cannot be players with duplicate numbers", exception.getMessage());
    }

    @Test
    void testBuildWithoutEnoughPositions(@Mock Player player1) {
      when(player1.getPosition()).thenReturn(Position.GOALKEEPER);

      teamBuilder.player("1", player1);
      Exception exception = assertThrows(IllegalArgumentException.class, teamBuilder::build);
      assertEquals(
          "There should be 1 GOALKEEPER, 2 DEFENDER and 2 FORWARD", exception.getMessage());
    }

    @Test
    void testBuildInvalidQuantityInPositions(
        @Mock Player player1, @Mock Player player2, @Mock Player player3) {
      when(player1.getPosition()).thenReturn(Position.GOALKEEPER);
      when(player2.getPosition()).thenReturn(Position.DEFENDER);
      when(player3.getPosition()).thenReturn(Position.FORWARD);

      teamBuilder.player("1", player1).player("2", player2).player("3", player3);
      Exception exception = assertThrows(IllegalArgumentException.class, teamBuilder::build);
      assertEquals(
          "There should be 1 GOALKEEPER, 2 DEFENDER and 2 FORWARD", exception.getMessage());
    }
  }

  @Nested
  class AfterBuild {
    @Mock private TeamStatistics teamStatistics;
    @Mock private Random random;
    @Mock private Player player1;
    @Mock private Player player2;
    @Mock private Player player3;
    @Mock private Player player4;
    @Mock private Player player5;
    private Team team;

    @BeforeEach
    void init() {
      when(player1.getPosition()).thenReturn(Position.GOALKEEPER);
      when(player2.getPosition()).thenReturn(Position.DEFENDER);
      when(player3.getPosition()).thenReturn(Position.DEFENDER);
      when(player4.getPosition()).thenReturn(Position.FORWARD);
      when(player5.getPosition()).thenReturn(Position.FORWARD);

      team =
          new Team.Builder("Brazil", teamStatistics, random)
              .player("1", player1)
              .player("2", player2)
              .player("3", player3)
              .player("4", player4)
              .player("5", player5)
              .build();
    }

    // TODO: add successful replace tests.

    @Test
    void testReplaceNonexistent() {
      Exception exception =
          assertThrows(IllegalArgumentException.class, () -> team.replace("0", "1", player1));
      assertEquals("No existing player with such number", exception.getMessage());
    }

    @Test
    void testReplaceInvalidNewPosition(@Mock Player replacement) {
      when(replacement.getPosition()).thenReturn(Position.DEFENDER);

      Exception exception =
          assertThrows(IllegalArgumentException.class, () -> team.replace("1", "1", replacement));
      assertEquals(
          "The position of the new player needs to be the same as the one being replaced",
          exception.getMessage());
    }

    @Test
    void testReplaceWithExistingNumber(@Mock Player replacement) {
      when(replacement.getPosition()).thenReturn(Position.GOALKEEPER);

      Exception exception =
          assertThrows(IllegalArgumentException.class, () -> team.replace("1", "2", replacement));
      assertEquals("There cannot be players with duplicate numbers", exception.getMessage());
    }

    @Test
    void testCalculateTotalSkill() {
      when(player1.calculateSkill()).thenReturn(1);
      when(player2.calculateSkill()).thenReturn(1);
      when(player3.calculateSkill()).thenReturn(1);
      when(player4.calculateSkill()).thenReturn(1);
      when(player5.calculateSkill()).thenReturn(1);

      assertEquals(5, team.calculateTotalSkill());
    }

    @Test
    void testScoreGoals() {
      when(random.nextInt(5)).thenReturn(0);

      int goals = 1;
      team.scoreGoals(goals);
      verify(teamStatistics).scoreGoals(goals);
      verify(player1).scoreGoal();
    }
  }
}
