package core;

import items.Deck;
import rules.Scorer;
import ui.GameUI;
import java.util.Optional;
import java.util.Scanner;

public class Game {
    private Deck deck;
    private Player player1;
    private Player player2;
    private Scorer scorer;
    private GameUI ui;
    String confirma;

    public Game(GameUI gameUI) {//inicializa o jogo
        this.ui = gameUI;
        this.play();
    }

    public void initialize() {
        this.deck = new Deck();
        this.scorer = ui.requestRuleSet();
        this.player1 = new Player(ui.requestPlayerName(1));
        this.player2 = new Player(ui.requestPlayerName(2));

        distributeCards();
    }

    private void distributeCards() {
        this.player1.receiveCard(this.deck.drawCard());
        this.player1.receiveCard(this.deck.drawCard());

        this.player2.receiveCard(this.deck.drawCard());
        this.player2.receiveCard(this.deck.drawCard());
    }

    public void play() {
        do{
            //Inicializa o jogo, eu decidi colocar aqui, pois isso faz com que o deck, as mãos e as pontuações zeram se o jogador quiser jogar novamente
            initialize();
            //Turno do jogador 1
            playerTurn(player1);
            playerTurn(player2);
            var maybeWinner = verifyWinner();

            maybeWinner.ifPresentOrElse((winner) -> ui.renderWinner(winner), ui::renderTie);

            System.out.println("\nDeseja jogar novamente? S / N\n");
            var sc = new Scanner(System.in);
            confirma = sc.nextLine();
        }while(confirma.equalsIgnoreCase("s"));

        System.out.println("\nObrigado por jogar! Saindo...\n");
    }

    private Optional<Player> verifyWinner() {
        var scorePlayer1 = this.scorer.calculateScore(this.player1.getPlayerHand());
        var scorePlayer2 = this.scorer.calculateScore(this.player2.getPlayerHand());
        //Verificar condições de vitória:
        // - Se ambas atingiram a mesma pontuação: Empate e reinicia a rodada x
        // - Se ambas estouraram, ou atingirem 21: Empate e reinicia a rodada x
        // - Se um jogador tiver 21: exibe ele como ganhador
        // - Se um jogador tiver passado de 21: O outro jogador é o vencedor
        // - Se ninguém atingiu 21 e não estourou: Ganha quem estiver mais próximo de 21
        //Caso em que os dois tiram 21
        if (scorePlayer1 == 21 && scorePlayer2 == 21) {
            System.out.println("Mão de " + this.player1.getName());
            this.ui.renderHand(this.player1.getPlayerHand(), scorePlayer1);
            System.out.println("\nMão de " + this.player2.getName());
            this.ui.renderHand(this.player2.getPlayerHand(), scorePlayer2);
            System.out.println("\nOS DOIS JOGADORES TIRARAM 21! QUAIS SÃO AS CHANCES???");
        }
        //Caso em que os dois jogadores tiram a mesma pontuação
        else if (scorePlayer1 == scorePlayer2) {
            System.out.println("Mão de " + this.player1.getName());
            this.ui.renderHand(this.player1.getPlayerHand(), scorePlayer1);
            System.out.println("\nMão de " + this.player2.getName());
            this.ui.renderHand(this.player2.getPlayerHand(), scorePlayer2);
            System.out.println("\nEmpate! Os dois jogadores atingiram a mesma pontuação.");
        }
        //Caso em que os dois jogadores estouram
        else if (scorePlayer1 > 21 && scorePlayer2 > 21) {
            System.out.println("Mão de " + this.player1.getName());
            this.ui.renderHand(this.player1.getPlayerHand(), scorePlayer1);
            System.out.println("\nMão de " + this.player2.getName());
            this.ui.renderHand(this.player2.getPlayerHand(), scorePlayer2);
            System.out.println("\nEmpate! As mãos dos dois jogadores estouraram.");
        }
        //Caso em que 1 dos jogadores tira 21
        else if (scorePlayer1 == 21 || scorePlayer2 == 21) {
            System.out.println("Mão de " + this.player1.getName());
            this.ui.renderHand(this.player1.getPlayerHand(), scorePlayer1);
            System.out.println("\nMão de " + this.player2.getName());
            this.ui.renderHand(this.player2.getPlayerHand(), scorePlayer2);
            System.out.println("\nO jogador " + (scorePlayer1 > scorePlayer2 ? player1.getName() : player2.getName()) + " tirou 21 e venceu!");
        }
        //Caso em que um dos jogadores estoura
        else if (scorePlayer1 > 21 || scorePlayer2 > 21) {
            System.out.println("Mão de " + this.player1.getName());
            this.ui.renderHand(this.player1.getPlayerHand(), scorePlayer1);
            System.out.println("\nMão de " + this.player2.getName());
            this.ui.renderHand(this.player2.getPlayerHand(), scorePlayer2);
            System.out.println("\nO jogador " + (scorePlayer1 <= 21 ? player1.getName() : player2.getName()) + " venceu, pois a mão do jogador " + (scorePlayer1 > 21 ? player1.getName() : player2.getName()) + " estourou!");
            return Optional.ofNullable(scorePlayer1 <= 21 ? player1 : player2);
        }
        //Caso em que nenhum dos jogadores atinge 21 ou estoura
        else {
            System.out.println("Mão de " + this.player1.getName());
            this.ui.renderHand(this.player1.getPlayerHand(), scorePlayer1);
            System.out.println("\nMão de " + this.player2.getName());
            this.ui.renderHand(this.player2.getPlayerHand(), scorePlayer2);
            System.out.println("\nO jogador " + (scorePlayer1 > scorePlayer2 ? player1.getName() : player2.getName()) + " venceu, pois atingiu a maior pontuação!");
            return Optional.ofNullable(scorePlayer1 > scorePlayer2 ? player1 : player2);
        }
        return Optional.empty();
    }

    private void playerTurn(Player player) {
        System.out.println("Jogador: " + player.getName());
        this.ui.renderHand(player.getPlayerHand(), this.scorer.calculateScore(player.getPlayerHand()));
        //Questiona o jogador se dele deseja comprar uma carta ou não
        this.ui.renderPlayerTurn(player.getName());

        PlayerAction action;
        do {
            //Pega a resposta do jogador
            action = this.ui.requestAction(player, this.deck);
            //Se for stand na primeira resposta, tem que dar um break
            if (action == PlayerAction.STAND) {
                break;
            }
            //Mostra a mão atualizada após comprar a nova carta
            this.ui.renderHand(player.getPlayerHand(), this.scorer.calculateScore(player.getPlayerHand()));
            //Diz a novas opções para o jogador dependendo do caso dele
            if (this.scorer.calculateScore(player.getPlayerHand()) < 21) {
                System.out.println("\nDeseja comprar outra carta?\n");
            }
            if (this.scorer.calculateScore(player.getPlayerHand()) == 21) {
                System.out.println("\n21! Que sorte!\n");
            }
            if (this.scorer.calculateScore(player.getPlayerHand()) > 21) {
                System.out.println("\nQue pena! Sua mão estourou!\n");
            }
        } while (action == PlayerAction.HIT && (this.scorer.calculateScore(player.getPlayerHand()) < 21));
    }

    @Override
    public String toString() {
        return "Mão de " + this.player1 + " Valor da mão: " + this.scorer.calculateScore(this.player1.getPlayerHand())+ "\n" + "Mão de " + this.player2 + " Valor da mão: " + this.scorer.calculateScore(this.player2.getPlayerHand()) + "\nQuantidade de cartas remanescentes no deck: " + this.deck.remainingCards();
    }
}
