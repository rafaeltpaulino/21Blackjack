public class Game {
    private Deck deck;
    private Card card;
    private Player player1;
    private Player player2;
    private Player house;

    public void startGame() {
        this.deck = new Deck();
        player1 = new Player();
        for(int i=0; i<2; i++) {
            playerDraw(player1);
        }
        playerHandValue(player1);

        player2 = new Player();
        for(int i=0; i<2; i++) {
            playerDraw(player2);
        }
        playerHandValue(player2);

        house = new Player();
        for(int i=0; i<2; i++) {
            playerDraw(house);
        }
        playerHandValue(house);
    }

    public void playerDraw(Player player) {
        card = deck.drawCard();
        player.receiveCard(card);
    }

    public int playerHandValue(Player player) {
        return player.handValue(player);
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public Player getHouse() {
        return house;
    }

    public void showPlayer1Hand() {
        System.out.println("Mão do Jogador 1 = " + player1.toString());
    }

    public void showPlayer2Hand() {
        System.out.println("Mão do Jogador 2 = " + player2.toString());
    }

    public void showHouseHand() {
        System.out.println("Mão da Casa = " + house.toString());
    }

    public void showHouseHand2() {
        card = house.getPlayerHand().getFirst();
        System.out.println("Mão da Casa: [" + card.toString() + ", " + "???] " + "Valor da mão: " + "???");
    }

    @Override
    public String toString() {
        return "Mão do Jogador 1 = " + player1 + "\n" + "Mão do Jogador 2 = " + player2 + "\n" + "Mão da Casa = " + house;
    }
}
