package br.com.univali;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

final class Team {
  private static final int GOALKEEPERS = 1;
  private static final int DEFENDERS = 2;
  private static final int FORWARDS = 2;

  private final String name;
  private final Map<String, Player> playerByNumber;
  private final TeamStatistics teamStatistics;
  private final Random random;

  private Team(
      String name,
      Map<String, Player> playerByNumber,
      TeamStatistics teamStatistics,
      Random random) {
    this.name = name;
    this.playerByNumber = playerByNumber;
    this.teamStatistics = teamStatistics;
    this.random = random;
  }

  void replace(String oldPlayerNumber, String newPlayerNumber, Player newPlayer) {
    Player oldPlayer = playerByNumber.get(oldPlayerNumber);
    if (oldPlayer == null) {
      throw new IllegalArgumentException("No existing player with such number");
    }
    if (oldPlayer.getPosition() != newPlayer.getPosition()) {
      throw new IllegalArgumentException(
          "The position of the new player needs to be the same as the one being replaced");
    }
    if (oldPlayerNumber.equals(newPlayerNumber)) {
      playerByNumber.replace(oldPlayerNumber, newPlayer);
    } else {
      checkIfHasPlayerWithNumber(playerByNumber, newPlayerNumber);
      playerByNumber.remove(oldPlayerNumber);
      playerByNumber.put(newPlayerNumber, newPlayer);
    }
  }

  private static void checkIfHasPlayerWithNumber(
      Map<String, Player> playerByNumber, String number) {
    if (playerByNumber.containsKey(number)) {
      throw new IllegalArgumentException("There cannot be players with duplicate numbers");
    }
  }

  int calculateTotalSkill() {
    return playerByNumber.values().stream().mapToInt(Player::calculateSkill).sum();
  }

  int getWins() {
    return teamStatistics.getWins();
  }

  int getTies() {
    return teamStatistics.getTies();
  }

  int getScore() {
    return teamStatistics.getScore();
  }

  int getGoals() {
    return teamStatistics.getGoals();
  }

  void scoreWin() {
    teamStatistics.scoreWin();
  }

  void scoreTie() {
    teamStatistics.scoreTie();
  }

  void scoreGoals(int goals) {
    teamStatistics.scoreGoals(goals);
    randomPlayersScoreGoals(goals);
  }

  private void randomPlayersScoreGoals(int goals) {
    String[] playerNumbers = playerByNumber.keySet().toArray(new String[0]);
    while (goals > 0) {
      goals--;
      Player player = randomPlayer(playerNumbers);
      player.scoreGoal();
    }
  }

  private Player randomPlayer(String[] playerNumbers) {
    int randomIndex = random.nextInt(playerNumbers.length);
    String playerNumber = playerNumbers[randomIndex];
    return playerByNumber.get(playerNumber);
  }

  static class Builder {
    private final Map<String, Player> playerByNumber = new HashMap<>();
    private final String name;
    private final TeamStatistics teamStatistics;
    private final Random random;

    Builder(String name) {
      this(name, new TeamStatistics(), new Random());
    }

    Builder(String name, TeamStatistics teamStatistics, Random random) {
      this.name = name;
      this.teamStatistics = teamStatistics;
      this.random = random;
    }

    Builder player(String number, Player player) {
      checkIfHasPlayerWithNumber(playerByNumber, number);
      playerByNumber.put(number, player);
      return this;
    }

    Team build() {
      checkFormation();
      return new Team(name, playerByNumber, teamStatistics, random);
    }

    private void checkFormation() {
      Map<Position, Long> quantityByPosition = collectQuantityByPosition();
      if (quantityByPosition.size() != 3 || !hasValidQuantityByPosition(quantityByPosition)) {
        throw createInvalidFormationException();
      }
    }

    private Map<Position, Long> collectQuantityByPosition() {
      return playerByNumber.values().stream()
          .collect(Collectors.groupingBy(Player::getPosition, Collectors.counting()));
    }

    private static boolean hasValidQuantityByPosition(Map<Position, Long> quantityByPosition) {
      return quantityByPosition.getOrDefault(Position.GOALKEEPER, 0L) == GOALKEEPERS
          && quantityByPosition.getOrDefault(Position.DEFENDER, 0L) == DEFENDERS
          && quantityByPosition.getOrDefault(Position.FORWARD, 0L) == FORWARDS;
    }

    private static IllegalArgumentException createInvalidFormationException() {
      return new IllegalArgumentException(
          "There should be "
              + GOALKEEPERS
              + " "
              + Position.GOALKEEPER
              + ", "
              + DEFENDERS
              + " "
              + Position.DEFENDER
              + " and "
              + FORWARDS
              + " "
              + Position.FORWARD);
    }
  }
}
