package br.com.univali;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

class Team {
  private static final int GOALKEEPERS = 1;
  private static final int DEFENDERS = 2;
  private static final int FORWARDS = 2;

  private final String name;
  private final Map<String, Player> playerByNumber;

  private Team(String name, Map<String, Player> playerByNumber) {
    this.name = name;
    this.playerByNumber = playerByNumber;
  }

  static class Builder {
    private final Map<String, Player> playerByNumber = new HashMap<>();
    private final String name;

    Builder(String name) {
      this.name = name;
    }

    Builder player(String number, Player player) {
      if (playerByNumber.containsKey(number)) {
        throw new IllegalArgumentException("There cannot be players with duplicate numbers");
      }
      playerByNumber.put(number, player);
      return this;
    }

    Team build() {
      checkFormation();
      return new Team(name, playerByNumber);
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
