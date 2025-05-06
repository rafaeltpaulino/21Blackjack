package ui;

import core.Player;
import core.PlayerAction;
import items.Card;
import items.Deck;
import rules.Scorer;

import java.util.List;

public interface GameUI {
    String requestPlayerName(int playerNumber);
    Scorer requestRuleSet();
    void renderHand(List<Card> cards, int core);
    PlayerAction requestAction(Player player, Deck deck);
    void renderPlayerTurn(String playerName);
    void renderWinner(Player player);
    void renderTie();
}
