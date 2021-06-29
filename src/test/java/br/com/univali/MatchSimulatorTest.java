package br.com.univali;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MatchSimulatorTest {
  private final LocalDate localDate = LocalDate.of(2006, 9, 3);
  private MatchSimulator matchSimulator;

  @BeforeEach
  void init(@Mock Team team1, @Mock Team team2) {
    when(team1.calculateTotalSkill()).thenReturn(265);
    when(team2.calculateTotalSkill()).thenReturn(231);
    matchSimulator = new MatchSimulator(team1, team2, new Random(0), createClock());
  }

  private Clock createClock() {
    ZoneId zoneId = ZoneId.systemDefault();
    Instant instant = localDate.atStartOfDay(zoneId).toInstant();
    return Clock.fixed(instant, zoneId);
  }

  @Test
  void testExecution() {
    MatchSimulator.Result result = matchSimulator.execute();
    assertEquals(2, result.homeGoals());
    assertEquals(0, result.awayGoals());
    assertEquals(localDate, result.date());
  }
}
