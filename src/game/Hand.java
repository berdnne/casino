package game;

import java.util.ArrayList;

public class Hand {

    private ArrayList<Card> cards;
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

        value += card.getIntValue();
        if (card.isAce()) numberOfAces++;

        while (value > 21){

            if (numberOfAces <= 0){

                busted = true;
                break;
            }

            value -= 10;
            numberOfAces--;

        }

        cards.add(card);
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public void printHand(){

        for (int i = 0; i < cards.size(); i++) {

            System.out.print(cards.get(i).toString());
            if (i != cards.size() - 1) System.out.print(", ");

        }

    }

    public boolean isBusted() {
        return busted;
    }

    public int getValue() {
        return value;
    }

}
