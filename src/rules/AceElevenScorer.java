package rules;

import items.Card;
import items.Rank;

import java.util.List;

//Regra alternativa onde o Ás vale 11
public class AceElevenScorer implements Scorer{
    @Override
    public int calculateScore(List<Card> cards) {
        int i = 0;
        for(Card card : cards) {
            if(card.getRank() == Rank.ACE) {
                i+= 11;
            }
            else {
                i += card.getRank().getRankValue();
            }
        }
        return i;
    }
}
