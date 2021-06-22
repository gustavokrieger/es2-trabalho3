package br.com.univali;

import java.time.LocalDate;

class Match {
  private final Team home;
  private final Team away;

  Match(Team home, Team away) {
    this.home = home;
    this.away = away;
  }

  MatchResult execute() {
    // TODO: complete method.
    return new MatchResult(0, 0, LocalDate.now());
  }

  static record MatchResult(int homeScore, int awayScore, LocalDate date) {}
}
