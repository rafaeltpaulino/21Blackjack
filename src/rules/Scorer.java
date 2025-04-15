package rules;

import items.Card;

import java.util.List;

public interface Scorer {
    int calculateScore(List<Card> cards);
}
