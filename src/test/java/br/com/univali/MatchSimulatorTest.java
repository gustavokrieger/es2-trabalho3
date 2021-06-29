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
  @Mock private Team team1;
  @Mock private Team team2;
  @Mock private Random random;
  private MatchSimulator matchSimulator;

  @BeforeEach
  void init() {
    matchSimulator = new MatchSimulator(team1, team2, random);
  }

  @Test
  void testExecution() {
    when(team1.calculateTotalSkill()).thenReturn(265);
    when(team2.calculateTotalSkill()).thenReturn(231);
    when(random.nextBoolean()).thenReturn(true).thenReturn(true).thenReturn(false);

    LocalDate localDate = LocalDate.of(2006, 9, 3);
    MatchSimulator.Result result;
    try (MockedStatic<LocalDate> mockedStatic = mockStatic(LocalDate.class)) {
      mockedStatic.when(LocalDate::now).thenReturn(localDate);

      result = matchSimulator.execute();
    }

    assertEquals(2, result.homeGoals());
    assertEquals(0, result.awayGoals());
    assertEquals(localDate, result.date());
  }
}
