package core;

import items.Card;

import java.util.ArrayList;
import java.util.List;

public class Player {
    int i = 0;
    private String name;
    private List<Card> playerHand;

    public Player(String name) {
        this.playerHand = new ArrayList<>();
        this.name = name;
    }

    public void receiveCard(Card card) {
        this.playerHand.add(card);
    }

    public List<Card> getPlayerHand() {
        return playerHand;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return name + " " + playerHand;
    }
}
