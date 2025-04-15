package items;

public enum Rank {

    KING("REI", 10), QUEEN("RAINHA", 10), JACK("VALETE", 10), TEN("DEZ", 10),
    NINE("NOVE", 9), EIGHT("OITO", 8), SEVEN("SETE", 7), SIX("SEIS", 6),
    FIVE("CINCO", 5), FOUR("QUATRO", 4), THREE("TRES", 3), TWO("DOIS", 2),
    ACE("AS", 1);

    private String rankName;
    private int rankValue;

    private Rank(String rankName, int rankValue) {
        this.rankName = rankName;
        this.rankValue = rankValue;
    }

    public String getRankName() {
        return rankName;
    }

    public int getRankValue() {
        return rankValue;
    }

    @Override
    public String toString() {
        return this.rankName;
    }

}
