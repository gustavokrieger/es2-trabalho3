package br.com.univali;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

class Team {
  private static final int GOALKEEPERS = 1;
  private static final int DEFENDERS = 2;
  private static final int FORWARDS = 2;

  private final Map<String, Player> playersByNumber;

  private Team(Map<String, Player> playersByNumber) {
    this.playersByNumber = playersByNumber;
  }

  static class Builder {
    private final Map<String, Player> playersByNumber = new HashMap<>();

    Builder player(String number, Player player) {
      if (playersByNumber.containsKey(number)) {
        throw new IllegalArgumentException("There can be no players with duplicate numbers");
      }
      playersByNumber.put(number, player);
      return this;
    }

    Team build() {
      checkFormation();
      return new Team(playersByNumber);
    }

    private void checkFormation() {
      Map<Position, Long> quantityByPosition = collectQuantityByPosition();
      try {
        if (quantityByPosition.remove(Position.GOALKEEPER) == GOALKEEPERS
            && quantityByPosition.remove(Position.DEFENDER) == DEFENDERS
            && quantityByPosition.remove(Position.FORWARD) == FORWARDS) {
          throw createInvalidFormationException();
        }
      } catch (NullPointerException e) {
        throw createInvalidFormationException();
      }
    }

    private Map<Position, Long> collectQuantityByPosition() {
      return playersByNumber.values().stream()
          .collect(Collectors.groupingBy(Player::getPosition, Collectors.counting()));
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
