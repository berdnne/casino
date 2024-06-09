import game.BlackjackCard;
import game.Card;

import java.util.ArrayList;
import java.util.Scanner;

public class Blackjack {

    private Scanner in;
    private ArrayList<BlackjackCard> deck;

    private ArrayList<BlackjackCard> playerHand;
    private ArrayList<BlackjackCard> dealerHand;

    public Blackjack(){

        in = new Scanner(System.in);
        deck = BlackjackCard.createSortedDeck();
        playerHand = new ArrayList<>();
        dealerHand = new ArrayList<>();

    }

    // TODO: change back to private
    public void playRound(int bet){

        boolean playerBusted = false;
        boolean dealerBusted = false;

        int numAces;
        int handValue;

        boolean roundActive = true;
        while (roundActive){

            for (int i = 0; i < 2; i++){

                playerHand.add(drawCard());
                dealerHand.add(drawCard());

            }

            System.out.println("Dealer's hand: " + dealerHand.getFirst().toString() + ", Mystery Card");

            boolean turnActive = true;
            while (turnActive){

                handValue = 0;
                numAces = 0;

                System.out.print("Your hand: ");
                for (int i = 0; i < playerHand.size(); i++){

                    System.out.print(playerHand.get(i).toString());
                    if (i != playerHand.size() - 1) System.out.print(", ");

                    handValue += playerHand.get(i).getIntValue();
                    if (playerHand.get(i).isAce()) numAces++;
                }
                System.out.print(" ");

                while (handValue > 21){

                    if (numAces <= 0) {

                        System.out.print("You busted! ");
                        playerBusted = true;
                        turnActive = false;
                        break;

                    }

                    numAces--;
                    handValue -= 10;

                }

                System.out.println("(" + handValue + ")");

                if (!turnActive) break;

                System.out.println("Would you like to hit or stand? (H/S)");

                switch (in.nextLine().toLowerCase()){
                    case "h": playerHand.add(drawCard());
                    break;
                    case "s": turnActive = false;
                }

            }

            // TODO: if the player has busted (playerBusted == true), escape the following for a single player scenario
            // dealer logic



            roundActive = false;

        }

    }

    private BlackjackCard drawCard(){

        BlackjackCard card;
        int randomIndex = (int)(Math.random() * deck.size());
        card = deck.get(randomIndex);
        deck.remove(randomIndex);
        return card;

    }

}
