package game;

import java.util.ArrayList;

public class BlackjackCard extends Card {

    public BlackjackCard(Rank rank, Suit suit) {
        super(rank, suit);
    }

    public void setIntValue() {
        intValue = switch (rank) {
            case TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN -> rank.ordinal() + 2;
            case JACK, QUEEN, KING -> 10;
            case ACE -> 11;
        };
    }

    public static ArrayList<BlackjackCard> createSortedDeck() {
        ArrayList<BlackjackCard> deck = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 13; j++) {
                deck.add(new BlackjackCard(Rank.values()[j], Suit.values()[i]));
            }
        }
        return deck;
    }

    public boolean isAce() {
        return rank == Rank.ACE;
    }

}
