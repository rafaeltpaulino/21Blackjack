package core;

import items.Card;
import items.Deck;
import rules.Scorer;
import ui.TerminalGameUI;

public class Game {
    private Deck deck;
    private Card card;
    private Player player1;
    private Player player2;
    private Scorer scorer;

    public Game() {//inicializa o jogo
        this.initialize();
    }

    public void initialize() {
        TerminalGameUI ui = new TerminalGameUI();
        this.deck = new Deck();
        this.scorer = ui.requestRuleSet();

        this.player1 = new Player(ui.requestPlayerName(1));
        for(int i=0; i<2; i++) {
            playerDraw(this.player1);
        }

        player2 = new Player(ui.requestPlayerName(2));
        for(int i=0; i<2; i++) {
            playerDraw(this.player2);
        }

    }

    public void playerDraw(Player player) {
        card = this.deck.drawCard();
        player.receiveCard(card);
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    @Override
    public String toString() {
        return "Mão de " + this.player1 + " Valor da mão: " + this.scorer.calculateScore(this.player1.getPlayerHand())+ "\n" + "Mão de " + this.player2 + " Valor da mão: " + this.scorer.calculateScore(this.player2.getPlayerHand()) + "\nQuantidade de cartas remanescentes no deck: " + this.deck.remainingCards();
    }
}
