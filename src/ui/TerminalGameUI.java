package ui;

import core.Player;
import core.PlayerAction;
import items.Card;
import items.Deck;
import rules.AceElevenScorer;
import rules.BasicScorer;
import rules.Scorer;

import java.util.List;
import java.util.Scanner;

public class TerminalGameUI implements GameUI{
    @Override
    public String requestPlayerName(int playerNumber) {
        var sc = new Scanner(System.in);
        System.out.println("Digite o nome do jogador " + playerNumber + " : ");
        return sc.nextLine();
    }

    @Override
    public Scorer requestRuleSet() {
        var sc = new Scanner(System.in);

        while(true) {
            try {
                System.out.println("Escolha uma regra (1-Clássica, 2-As vale 11):");
                int rule = sc.nextInt();

                switch (rule) {
                    case 1:
                        System.out.println("Regra clássica selecionada.\n");
                        return new BasicScorer();
                    case 2:
                        System.out.println("Regra As vale 11 selecionada.\n");
                        return new AceElevenScorer();
                    default:
                        System.out.println("Input inválido. Digite 1 ou 2.");
                }
            }catch (Exception e) {
                System.out.println("Input inválido. Por favor, digite um número");
                sc.next();
            }
        }
    }

    @Override
    public void renderHand(List<Card> cards, int score) {
        System.out.println(cards.toString() + " " + score);
    }

    @Override
    public PlayerAction requestAction(Player player, Deck deck) {
        System.out.println("Digite a ação escolhida: (1)HIT ou (2)STAND");
        var sc = new Scanner(System.in);
        int action = sc.nextInt();

        switch (action) {
            case 1 -> {
                Card card = deck.drawCard();
                System.out.println("\nComprou: " + card.toString());
                player.receiveCard(card);
                return PlayerAction.HIT;
            }
            case 2 -> {
                System.out.println("\nO jogador " + player.getName() + " finalizou seu turno");
                return PlayerAction.STAND;
            }
            default ->{
                return PlayerAction.STAND;
            }
        }
    }

    @Override
    public void renderPlayerTurn(String playerName) {
        System.out.println("\nDeseja comprar uma carta, " + playerName + " ?");
    }

    @Override
    public void renderWinner(Player player) {
        System.out.println(player.getName() + " venceu a partida!");
    }

    @Override
    public void renderTie() {
        System.out.println("Empate!");
    }
}
