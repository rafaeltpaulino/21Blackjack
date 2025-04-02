import java.util.ArrayList;
import java.util.List;

public class Player {
    int i = 0;

    private List<Card> playerHand;

    public Player() {
        this.playerHand = new ArrayList<>();
    }

    public void receiveCard(Card card) {
        this.playerHand.add(card);
    }

    public int handValue(Player player) {
        i = 0;
        for (Card card : playerHand) {
            i += card.getRank().getRankValue();
        }
        return i;
    }

    public List<Card> getPlayerHand() {
        return playerHand;
    }

    @Override
    public String toString() {
        return playerHand + " Valor da mao: " + i;
    }
}
