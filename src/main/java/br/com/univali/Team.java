package br.com.univali;

import java.util.Arrays;
import java.util.List;

class Team {
    private final List<Player> players;

    Team(Player... players) {
        this.players = Arrays.asList(players);
        checkTeamFormation();
    }

    private void checkTeamFormation() {
        int goalkeepers = 1;
        int defenders = 2;
        int forwards = 2;
        if (!(countPlayersIn(Position.GOALKEEPER) == goalkeepers &&
                countPlayersIn(Position.DEFENDER) == defenders &&
                countPlayersIn(Position.FORWARD) == forwards
        )) {
            throw new IllegalArgumentException("There should be " + goalkeepers + " " + Position.GOALKEEPER + ", " +
                    defenders + " " + Position.DEFENDER + " and " +
                    forwards + " " + Position.FORWARD);
        }
    }

    private int countPlayersIn(Position position) {
        int counter = 0;
        for (Player player : players) {
            if (player.getPosition() == position) {
                counter++;
            }
        }
        return counter;
    }
}
