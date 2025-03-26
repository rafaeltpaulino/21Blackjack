import java.util.Objects;

public class Card {

    public Card(Rank rank, Suit suit) {
        this.rank = rank;
        this.suit = suit;
    }

    private Suit suit;

    private Rank rank;

    public Rank getRank() {
        return rank;
    }

    @Override
    public String toString() {
        return rank + " DE " + suit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return suit == card.suit && rank == card.rank;
    }

    @Override
    public int hashCode() {
        return Objects.hash(suit, rank);
    }
}
