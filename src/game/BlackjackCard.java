package game;

import java.util.ArrayList;

public class BlackjackCard extends Card {

    public BlackjackCard(String value, String suit) {
        super(value, suit);
    }

    public void setIntValue() {

        switch (value) {
            case "2","3","4","5","6","7","8","9","10": intValue = Integer.parseInt(value);
                break;
            case "Jack","Queen","King": intValue = 10;
                break;
            case "Ace": intValue = 11;
                break;
            default: throw new NumberFormatException("Invalid card value");
        }

    }

    public static ArrayList<BlackjackCard> createSortedDeck() {

        ArrayList<BlackjackCard> deck = new ArrayList<>();

        for (int i = 0; i < 4; i++) {

            for (int j = 0; j < 13; j++) {

                deck.add(new BlackjackCard(

                        switch(j){

                            case 0,1,2,3,4,5,6,7,8 -> (j + 2) + "";
                            case 9 -> "Jack";
                            case 10 -> "Queen";
                            case 11 -> "King";
                            case 12 -> "Ace";
                            default -> throw new NumberFormatException("Invalid card value");

                },
                        switch (i){

                            case 0 -> "Clubs";
                            case 1 -> "Diamonds";
                            case 2 -> "Hearts";
                            case 3 -> "Spades";
                            default -> throw new NumberFormatException("Invalid card value");

                }));

            }

        }

        return deck;

    }

    public boolean isAce(){
        return value.equals("Ace");
    }

}
