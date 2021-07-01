package br.com.univali;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PlayerTest {
  @Mock private Attribute primary;
  @Mock private Attribute secondary;
  private Player player;

  @BeforeEach
  void init() {
    player = new Player("Tsubasa", LocalDate.of(1981, 1, 1), Position.FORWARD, primary, secondary);
  }

  @Test
  void testScoreGoal() {
    player.scoreGoal();
    assertEquals(1, player.getGoals());
  }

  @Test
  void testCalculateAge() {
    LocalDate now = LocalDate.of(2006, 9, 3);
    int age;
    try (MockedStatic<LocalDate> mockedStatic = mockStatic(LocalDate.class, CALLS_REAL_METHODS)) {
      mockedStatic.when(LocalDate::now).thenReturn(now);

      age = player.calculateAge();
    }
    assertEquals(25, age);
  }

  @Test
  void testCalculateSkill() {
    when(primary.getScore()).thenReturn(100);
    when(secondary.getScore()).thenReturn(100);

    assertEquals(100, player.calculateSkill());
  }
}
