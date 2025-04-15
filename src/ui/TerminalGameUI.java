package ui;

import rules.AceElevenScorer;
import rules.BasicScorer;
import rules.Scorer;

import java.util.Scanner;

public class TerminalGameUI implements GameUI{
    @Override
    public String requestPlayerName(int playernumber) {
        var sc = new Scanner(System.in);
        System.out.println("Digite o nome do jogador " + playernumber + " : ");
        return sc.nextLine();
    }

    @Override
    public Scorer requestRuleSet() {
        var sc = new Scanner(System.in);

        while(true) {
            try {
                System.out.println("Escolha uma regra (1-Clássica, 2-Ace Eleven):");
                int rule = sc.nextInt();

                switch (rule) {
                    case 1:
                        System.out.println("Regra clássica selecionada.\n");
                        return new BasicScorer();
                    case 2:
                        System.out.println("Regra Ace Eleven selecionada.\n");
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
}
