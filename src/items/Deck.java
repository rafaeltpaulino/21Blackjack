package items;

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

    public int remainingCards() {
        return this.cards.size();
    }

    @Override
    public String toString() {
        return "items.Deck{" +
                "cards=" + cards +
                '}';
    }
}
