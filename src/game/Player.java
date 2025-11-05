package game;

import main.Blackjack;

import java.util.ArrayList;

public class Player {

    private int money;
    private final Hand hand;

    public Player() {
        money = Blackjack.STARTING_MONEY;
        hand = new Hand();
    }

    public void drawCard(ArrayList<BlackjackCard> deck) {
        int randomIndex = (int) (Math.random() * deck.size());
        BlackjackCard card = deck.get(randomIndex);
        deck.remove(randomIndex);
        hand.add(card);
    }

    public Hand getHand() {
        return hand;
    }

    public boolean isBusted() {
        return hand.isBusted();
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public void addMoney(int money) {
        this.money += money;
    }

    public void removeMoney(int money) {
        this.money -= money;
    }

}
