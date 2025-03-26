public enum Suit {

    HEARTS("COPAS"), CLUBS("PAUS"), DIAMONDS("OUROS"), SPADES("ESPADAS");

    private String suitName;

    public String getSuitName() {
        return suitName;
    }

    private Suit(String suitName) {
        this.suitName = suitName;
    }

    @Override
    public String toString() {
        return this.suitName;
    }
}
