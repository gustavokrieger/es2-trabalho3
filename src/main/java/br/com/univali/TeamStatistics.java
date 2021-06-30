package br.com.univali;

class TeamStatistics {
  private static final int WIN_SCORE = 3;
  private static final int TIE_SCORE = 1;

  private int wins = 0;
  private int ties = 0;
  private int losses = 0;
  private int score = 0;
  private int goals = 0;

  int getWins() {
    return wins;
  }

  int getTies() {
    return ties;
  }

  int getLosses() {
    return losses;
  }

  int getScore() {
    return score;
  }

  int getGoals() {
    return goals;
  }

  void addWin() {
    wins++;
    score += WIN_SCORE;
  }

  void addTie() {
    ties++;
    score += TIE_SCORE;
  }

  void addLoss() {
    losses++;
  }

  void scoreGoals(int goals) {
    if (goals < 0) {
      throw new IllegalArgumentException("Cannot score negative goals");
    }

    this.goals += goals;
  }
}
