package br.com.univali;

class MatchTest {
  private final Team brazil = buildBrazil();
  private final Team argentina = buildArgentina();

  private Team buildBrazil() {
    var dida =
        new Player(
            "Dida", 32, Position.GOALKEEPER, Attribute.fromScore(100), Attribute.fromHeight(196));
    var cafu =
        new Player("Cafu", 36, Position.DEFENDER, Attribute.fromScore(0), Attribute.fromScore(50));
    var cicinho =
        new Player(
            "Cicinho", 33, Position.DEFENDER, Attribute.fromScore(50), Attribute.fromScore(50));
    var adriano =
        new Player(
            "Adriano", 24, Position.FORWARD, Attribute.fromScore(41), Attribute.fromScore(74));
    var ronaldo =
        new Player(
            "Ronaldo", 29, Position.FORWARD, Attribute.fromScore(0), Attribute.fromScore(50));

    return new Team.Builder("Brazil")
        .player("1", dida)
        .player("2", cafu)
        .player("6", cicinho)
        .player("7", adriano)
        .player("9", ronaldo)
        .build();
  }

  private Team buildArgentina() {
    var robertoAbbondanzieri =
        new Player(
            "Roberto Abbondanzieri",
            33,
            Position.GOALKEEPER,
            Attribute.fromScore(0),
            Attribute.fromHeight(196));
    var robertoAyala =
        new Player(
            "Roberto Ayala", 33, Position.DEFENDER, Attribute.fromScore(0), Attribute.fromScore(0));
    var fabricioColoccini =
        new Player(
            "Fabricio Coloccini",
            24,
            Position.DEFENDER,
            Attribute.fromScore(50),
            Attribute.fromScore(50));
    var javierSaviola =
        new Player(
            "Javier Saviola",
            24,
            Position.FORWARD,
            Attribute.fromScore(41),
            Attribute.fromScore(74));
    var lionelMessi =
        new Player(
            "Lionel Messi", 18, Position.FORWARD, Attribute.fromScore(90), Attribute.fromScore(90));

    return new Team.Builder("Argentina")
        .player("1", robertoAbbondanzieri)
        .player("2", robertoAyala)
        .player("4", fabricioColoccini)
        .player("7", javierSaviola)
        .player("19", lionelMessi)
        .build();
  }
}
