import game.BlackjackCard;

import java.util.ArrayList;
import java.util.Scanner;

public class Blackjack {

    private final Scanner in;
    private final ArrayList<BlackjackCard> deck;

    private final ArrayList<BlackjackCard> playerHand;
    private final ArrayList<BlackjackCard> dealerHand;

    public Blackjack() {

        in = new Scanner(System.in);
        deck = BlackjackCard.createSortedDeck();
        playerHand = new ArrayList<>();
        dealerHand = new ArrayList<>();

    }

    // TODO: change back to private
    public void playRound(int bet) {

        boolean playerBusted = false;
        boolean dealerBusted = false;

        int numPlayerAces;
        int playerHandValue = 0;

        int numDealerAces;
        int dealerHandValue = 0;


        for (int i = 0; i < 2; i++) {

            playerHand.add(drawCard());
            dealerHand.add(drawCard());

        }

        System.out.println("Dealer's hand: " + dealerHand.getFirst().toString() + ", Mystery Card");

        boolean playerTurnActive = true;
        while (playerTurnActive) {

            playerHandValue = 0;
            numPlayerAces = 0;

            System.out.print("Your hand: ");
            for (int i = 0; i < playerHand.size(); i++) {

                System.out.print(playerHand.get(i).toString());
                if (i != playerHand.size() - 1) System.out.print(", ");

                playerHandValue += playerHand.get(i).getIntValue();
                if (playerHand.get(i).isAce()) numPlayerAces++;
            }
            System.out.print(" ");

            while (playerHandValue > 21) {

                if (numPlayerAces <= 0) {

                    System.out.print("You busted! ");
                    playerBusted = true;
                    playerTurnActive = false;
                    break;

                }

                numPlayerAces--;
                playerHandValue -= 10;

            }

            System.out.println("(" + playerHandValue + ")");
            if (!playerTurnActive) break;

            System.out.println("Would you like to hit or stand? (H/S)");

            switch (in.nextLine().toLowerCase()) {
                case "h": {
                    System.out.println("You hit.");
                    playerHand.add(drawCard());
                }
                break;
                case "s": {
                    System.out.println("You stand with " + playerHandValue + ".");
                    playerTurnActive = false;
                }
            }

        }

        // TODO: if the player has busted (playerBusted == true), escape the following for a single player scenario
        // dealer logic

        boolean dealerTurnActive = true;
        while (dealerTurnActive) {

            dealerHandValue = 0;
            numDealerAces = 0;

            System.out.print("Dealer's hand: ");
            for (int i = 0; i < dealerHand.size(); i++) {

                System.out.print(dealerHand.get(i).toString());
                if (i != dealerHand.size() - 1) System.out.print(", ");

                dealerHandValue += dealerHand.get(i).getIntValue();
                if (dealerHand.get(i).isAce()) numDealerAces++;
            }
            System.out.print(" ");

            while (dealerHandValue > 21) {

                if (numDealerAces <= 0) {

                    System.out.print("The dealer busted! ");
                    dealerBusted = true;
                    dealerTurnActive = false;
                    break;

                }

                numDealerAces--;
                dealerHandValue -= 10;

            }

            System.out.println("(" + dealerHandValue + ")");
            if (!dealerTurnActive) break;

            if (dealerHandValue <= 17) {

                System.out.println("The dealer hits.");
                dealerHand.add(drawCard());

            } else {
                System.out.println("The dealer stands with " + dealerHandValue + ".");
                dealerTurnActive = false;
            }

        }

        if (playerBusted) return;

        if (dealerBusted) {
            System.out.println("You win $" + (bet * 2) + "! (2x payout)");
            // payout money
        }
        else if (playerHandValue == dealerHandValue) {
            System.out.println("Push! You get your bet back.");
            // payout money
        }
        else if (playerHandValue == 21){
            System.out.println("Blackjack! You win $" + (bet * 3) + "! (3x payout)");
            // payout money
        }
        else if (playerHandValue > dealerHandValue) {
            System.out.println("You win $" + (bet * 2) + "! (2x payout)");
            // payout money
        }
        else {
            System.out.println("Dealer wins! No payout.");
        }

    }

    private BlackjackCard drawCard() {

        BlackjackCard card;
        int randomIndex = (int) (Math.random() * deck.size());
        card = deck.get(randomIndex);
        deck.remove(randomIndex);
        return card;

    }

}
