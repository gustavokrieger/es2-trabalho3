package br.com.univali;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PlayerTest {
  @Mock private Attribute primary;
  @Mock private Attribute secondary;
  private Player player;

  @BeforeEach
  void init() {
    player = new Player("Tsubasa", 16, Position.FORWARD, primary, secondary);
  }

  @Test
  void testScoreGoal() {
    player.scoreGoal();
    assertEquals(1, player.getGoals());
  }

  @Test
  void testCalculateSkill() {
    when(primary.getScore()).thenReturn(100);
    when(secondary.getScore()).thenReturn(100);

    assertEquals(100, player.calculateSkill());
  }
}
