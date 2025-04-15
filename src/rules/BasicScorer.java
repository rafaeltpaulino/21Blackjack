package rules;

import items.Card;

import java.util.List;

//Regra clássica onde o Ás vale 1
public class BasicScorer implements Scorer{
    @Override
    public int calculateScore(List<Card> cards) {
        int i = 0;
        for(Card card : cards) {
            i += card.getRank().getRankValue();
        }
        return i;
    }
}
