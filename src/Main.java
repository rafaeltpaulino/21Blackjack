import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Game game = new Game();

        game.startGame();
        game.showPlayer1Hand();
        game.showPlayer2Hand();
        game.showHouseHand2();

        System.out.println("Jogador 1 deseja continuar? (S/N)");

        Scanner scanner = new Scanner(System.in);

        String c = scanner.next();

        while(c.equals("S") || c.equals("s")) { //Vez do jogador 1
            System.out.println("\n-----Jogador 1 comprou-----\n");
            game.playerDraw(game.getPlayer1()); //Compra um card
            game.playerHandValue(game.getPlayer1()); //Atualiza o valor da mão do jogador
            game.showPlayer1Hand(); //Mostra a mão do jogador

            if(game.playerHandValue(game.getPlayer1()) > 21) {
                System.out.println("\n-----A mao do jogador 1 estourou e ele perdeu! Que pena!-----\n");
                break;
            } else if (game.playerHandValue(game.getPlayer1()) == 21) {
                System.out.println("\n-----21! Que sorte hein!-----\n");
                break;
            } else {
                System.out.println("Jogador 1 deseja continuar? (S/N)");
                c = scanner.next();
            }
        }

        System.out.println("\n-----Vez do Jogador 2-----\n");
        game.showPlayer2Hand();
        System.out.println("Jogador 2 deseja continuar? (S/N)");
        c = scanner.next();

        while(c.equals("S") || c.equals("s")) { //Vez do jogador 2
            System.out.println("\n-----Jogador 2 comprou-----\n");
            game.playerDraw(game.getPlayer2());
            game.playerHandValue(game.getPlayer2());
            game.showPlayer2Hand();

            if(game.playerHandValue(game.getPlayer2()) > 21) {
                System.out.println("\n-----A mao do jogador 2 estourou e ele perdeu! Que pena!-----\n");
                break;
            } else if (game.playerHandValue(game.getPlayer2()) == 21) {
                System.out.println("\n-----21! Que sorte hein-----\n");
                break;
            } else {
                System.out.println("Jogador 2 deseja continuar? (S/N)");
                c = scanner.next();
            }
        }

        if((game.playerHandValue(game.getPlayer1()) > 21) && (game.playerHandValue(game.getPlayer2()) > 21)) {
            System.out.println("\n-----As mãos dos dois jogadores estouraram e eles perderam!-----\n");
            return;
        }

        System.out.println("\n-----Ambos jogadores terminaram de jogar. Vez da Casa-----\n");
        System.out.println(game);

        while(game.playerHandValue(game.getHouse()) < 21) {
            //Casos em que a Casa pode ganhar antes de comprar uma carta
            if((game.playerHandValue(game.getHouse()) < 21) && (game.playerHandValue(game.getHouse()) > game.playerHandValue(game.getPlayer1())) && (game.playerHandValue(game.getHouse()) > game.playerHandValue(game.getPlayer2()))) {
                System.out.println("\n-----A Casa venceu os dois jogadores!-----\n" + game);
                return;
            }

            if((game.playerHandValue(game.getHouse()) < 21) && (game.playerHandValue(game.getHouse()) >= game.playerHandValue(game.getPlayer1())) && (game.playerHandValue(game.getPlayer2()) > 21)) {
                System.out.println("\n-----A Casa venceu os Jogador 1 enquanto a mão do Jogador 2 estourou!-----\n" + game);
                return;
            }

            if((game.playerHandValue(game.getHouse()) < 21) && (game.playerHandValue(game.getPlayer1()) > 21) && (game.playerHandValue(game.getHouse()) >= game.playerHandValue(game.getPlayer2()))) {
                System.out.println("\n-----A Casa venceu o jogador 2 enquanto a mão do Jogador 1 estourou!-----\n" + game);
                return;
            }

            if((game.playerHandValue(game.getHouse()) >= game.playerHandValue(game.getPlayer1())) && (game.playerHandValue(game.getHouse()) >= game.playerHandValue(game.getPlayer2()))) {
                System.out.println("\n-----Em caso de empate a casa vence!-----\n" + game);
                return;
            }

            if((game.playerHandValue(game.getHouse()) == game.playerHandValue(game.getPlayer1())) && (game.playerHandValue(game.getHouse()) == game.playerHandValue(game.getPlayer2()))) {
                System.out.println("-----EMPATE TRIPLO!!! QUAIS AS CHANCES DISSO ACONTECER? MAS A CASA VENCE NO FINAL KKKK!-----");
                return;
            }

            System.out.println("\n-----A Casa comprou-----\n");
            game.playerDraw(game.getHouse());
            game.playerHandValue(game.getHouse());
            game.showHouseHand();

            //Casos que só podem acontecer depois que a casa compra pelo menos uma carta
            if((game.playerHandValue(game.getHouse()) > 21) && (game.playerHandValue(game.getPlayer1()) <= 21) && (game.playerHandValue(game.getPlayer2()) <= 21)) {
                System.out.println("\n-----A mão da Casa estourou! E os dois jogadores venceram!-----\n" + game);
                return;
            }

            if((game.playerHandValue(game.getHouse()) > 21) && (game.playerHandValue(game.getPlayer1()) <= 21) && (game.playerHandValue(game.getPlayer2()) > 21)) {
                System.out.println("\n-----A mão da Casa estourou! E o Jogador 1 venceu!-----\n" + game);
                return;
            }

            if((game.playerHandValue(game.getHouse()) > 21) && (game.playerHandValue(game.getPlayer1()) > 21) && (game.playerHandValue(game.getPlayer2()) <= 21)) {
                System.out.println("\n-----A mão da Casa estourou! E o Jogador 2 venceu!-----\n" + game);
                return;
            }

            if(game.playerHandValue(game.getHouse()) == 21) {
                System.out.println("\n-----21! A Casa venceu!-----\n" + game);
                return;
            }
        }
    }
}