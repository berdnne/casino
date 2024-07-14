package game;

import java.util.ArrayList;

public class Player {

    private int money;
    private Hand hand;

    public Player() {

        money = 100;
        hand = new Hand();

    }

    public void drawCard(ArrayList<BlackjackCard> deck) {

        BlackjackCard card;
        int randomIndex = (int) (Math.random() * deck.size());
        card = deck.get(randomIndex);
        deck.remove(randomIndex);
        hand.add(card);

    }

    public Hand getHand() {
        return hand;
    }

    public boolean isBusted(){
        return hand.isBusted();
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

}
