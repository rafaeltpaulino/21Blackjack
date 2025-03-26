import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        var baralho = new Deck(); //Cria o baralho

        var card1 = baralho.drawcard(); //Cria os cards iniciais da mão do jogador
        var card2 = baralho.drawcard();

        System.out.printf("Sua mão: " + card1.toString() + " e " + card2.toString());

        System.out.println("\n");

        var card3 = baralho.drawcard(); //Cria os cards iniciais da mão da casa
        var card4 = baralho.drawcard();

        System.out.println("Mão da casa: " + card3.toString() + " e " + "???");

        System.out.println("\n");

        int vj = card1.getRank().getRankValue() + card2.getRank().getRankValue(); //pega o valor da mao do jogador
        int vc = card3.getRank().getRankValue() + card4.getRank().getRankValue(); //pega o valor da mao da casa

        System.out.println("Valor da sua mão: " + vj + "\n");

        System.out.println("Deseja continuar ou parar? (C para continuar / P para parar) ");

        Scanner scanner = new Scanner(System.in); //Scanner e string para pegar o input do jogador
        String c;
        c = scanner.next();

        while(!c.equals("C") && !c.equals("c") && !c.equals("P") && !c.equals("p")) { //Caso o jogador escolha uma opção inválida
            System.out.println("Opção inválida.");
            System.out.println("Deseja continuar ou parar? (C para continuar / P para parar) ");
            c = scanner.next();
        }

        while(c.equals("C") || c.equals("c")) { //Se o jogador quiser continuar, entra em um loop até estourar a mão ou até que o jogador queira parar
            var card5 = baralho.drawcard();
            System.out.printf("\n" + "Você comprou: " + card5.toString() + "\n");
            vj += card5.getRank().getRankValue(); //pega o valor atualizado da mao do jogador
            System.out.println("Valor da sua mão: " + vj);
            System.out.println("\n");
            if(vj>21) {
                System.out.println("Estourou! Perdeu tudo marreco!");
                return;
            }
            if(vj == 21) {
                System.out.println("21! Parabéns! Você ganhou!");
                return;
            }
            System.out.println("Deseja continuar ou parar? (C para continuar / P para parar) ");
            c = scanner.next();
        }
        if(c.equals("P") || c.equals("p")) {
            System.out.println("\n" + "Mão da casa: " + card3 + " e " + card4);
            System.out.println("Valor da mão da casa: " + vc + "\n");

            if(vj<vc) { //Se a casa tiver uma mão maior já de começo, o jogador perde e o programa é finalizado
                System.out.println("Perdeu otario kkkjkkjkkkkk");
                return;
            }

            while(vc<21) { //Se a mão do jogador for maior, a casa compra até vencer ou até estourar
                var card6 = baralho.drawcard();
                System.out.println("Casa comprou: " + card6.toString());
                vc += card6.getRank().getRankValue();
                System.out.println("Valor da mão da casa: " + vc + "\n");
                if(vc>21) {
                    System.out.println("A casa estourou! Você ganhou campeão! Parabéns!");
                    return;
                }
                else if(vc>vj) {
                    System.out.println("Perdeu otario kkkjkkjkkkkk");
                    return;
                }
                else if(vc == vj) {
                    System.out.println("Em caso de empate a casa ganha troxa kkkjkkjkkkkk");
                    return;
                }
            }
        }
    }
}