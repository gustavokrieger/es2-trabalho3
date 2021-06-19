package br.com.univali;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

class Team {
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
      checkTeamFormation();
      return new Team(playersByNumber);
    }

    private void checkTeamFormation() {
      Map<Position, Long> positionQuantity = collectPositionQuantity();
      int goalkeepers = 1;
      int defenders = 2;
      int forwards = 2;
      if (!(positionQuantity.remove(Position.GOALKEEPER) == goalkeepers
          && positionQuantity.remove(Position.DEFENDER) == defenders
          && positionQuantity.remove(Position.FORWARD) == forwards)) {
        throw new IllegalArgumentException(
            "There should be "
                + goalkeepers
                + " "
                + Position.GOALKEEPER
                + ", "
                + defenders
                + " "
                + Position.DEFENDER
                + " and "
                + forwards
                + " "
                + Position.FORWARD);
      }
    }

    private Map<Position, Long> collectPositionQuantity() {
      return playersByNumber.values().stream()
          .collect(Collectors.groupingBy(Player::getPosition, Collectors.counting()));
    }
  }
}
