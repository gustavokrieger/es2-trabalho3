package br.com.univali;

class TeamStatistics {
  private static final int WIN_SCORE = 3;
  private static final int TIE_SCORE = 1;

  private int wins = 0;
  private int ties = 0;
  private int score = 0;
  private int goals = 0;

  int getWins() {
    return wins;
  }

  int getTies() {
    return ties;
  }

  int getScore() {
    return score;
  }

  int getGoals() {
    return goals;
  }

  void scoreWin() {
    wins++;
    score += WIN_SCORE;
  }

  void scoreTie() {
    ties++;
    score += TIE_SCORE;
  }

  void scoreGoals(int goals) {
    this.goals += goals;
  }
}
