package br.com.univali;

import java.time.LocalDate;
import java.time.Period;

class Player {
  private int goals = 0;
  private final String name;
  private final LocalDate birthDate;
  private final Position position;
  private final Attribute primary;
  private final Attribute secondary;

  Player(
      String name, LocalDate birthDate, Position position, Attribute primary, Attribute secondary) {
    this.name = name;
    this.birthDate = birthDate;
    this.position = position;
    this.primary = primary;
    this.secondary = secondary;
  }

  int getGoals() {
    return goals;
  }

  Position getPosition() {
    return position;
  }

  void scoreGoal() {
    goals++;
  }

  int calculateAge() {
    return Period.between(birthDate, LocalDate.now()).getYears();
  }

  int calculateSkill() {
    return (primary.getScore() * 6 + secondary.getScore() * 4) / 10;
  }
}
