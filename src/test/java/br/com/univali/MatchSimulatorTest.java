package br.com.univali;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MatchSimulatorTest {
  @Mock private Team home;
  @Mock private Team away;
  @Mock private Random random;
  private MatchSimulator matchSimulator;

  @BeforeEach
  void init() {
    matchSimulator = new MatchSimulator(home, away, random);
  }

  @Test
  void testExecution() {
    when(home.calculateTotalSkill()).thenReturn(100);
    when(away.calculateTotalSkill()).thenReturn(50);
    when(random.nextBoolean()).thenReturn(true).thenReturn(false);
    when(random.nextInt(110)).thenReturn(55);
    when(random.nextInt(50)).thenReturn(25);

    LocalDate now = LocalDate.of(2006, 9, 3);
    MatchSimulator.Result result;
    try (MockedStatic<LocalDate> mockedStatic = mockStatic(LocalDate.class)) {
      mockedStatic.when(LocalDate::now).thenReturn(now);

      result = matchSimulator.execute();
    }

    assertEquals(1, result.homeGoals());
    assertEquals(0, result.awayGoals());
    assertEquals(now, result.date());
  }
}
