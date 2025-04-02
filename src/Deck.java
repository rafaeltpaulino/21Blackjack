import java.util.ArrayList;
import java.util.Collections;

public class Deck {

    private ArrayList<Card> cards;

    public Deck() { //construtor
        this.cards = new ArrayList<Card>();

        for(Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                    cards.add(new Card(rank, suit));
            }
        }
        Collections.shuffle(cards);
    }

    public Card drawCard() {
        return this.cards.removeLast();
    }

    @Override
    public String toString() {
        return "Deck{" +
                "cards=" + cards +
                '}';
    }
}
