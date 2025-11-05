package game;

import main.Blackjack;

import java.util.ArrayList;

public class Hand {

    private final ArrayList<Card> cards;
    private int numberOfAces;
    private int value;
    private boolean busted;

    public Hand() {
        cards = new ArrayList<>();
        numberOfAces = 0;
        value = 0;
        busted = false;
    }

    public void add(BlackjackCard card) {
        cards.add(card);
        value += card.getIntValue();
        if (card.isAce()) numberOfAces++;
        while (value > Blackjack.BLACKJACK_SCORE) {
            if (numberOfAces <= 0) {
                busted = true;
                break;
            }
            value -= 10;
            numberOfAces--;
        }
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public boolean isBusted() {
        return busted;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        StringBuilder handString = new StringBuilder();
        for (int i = 0; i < cards.size(); i++) {
            handString.append(cards.get(i).toString());
            if (i != cards.size() - 1) handString.append(", ");
        }
        return handString.toString();
    }

}
