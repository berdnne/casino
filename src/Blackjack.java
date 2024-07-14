import game.BlackjackCard;
import game.Player;

import java.util.ArrayList;
import java.util.Scanner;

public class Blackjack {

    private final Scanner in;
    private final ArrayList<BlackjackCard> deck;
    private Player user;
    private Player dealer;

    public Blackjack() {

        in = new Scanner(System.in);
        deck = BlackjackCard.createSortedDeck();
        user = new Player();
        dealer = new Player();

    }

    // TODO: temporary bet variable
    public void playRound() {

        int bet = 100;

        System.out.println("Welcome to Blackjack! Please enter your bet amount.");

        // deal cards
        for (int i = 0; i < 2; i++) {

            user.drawCard(deck);
            dealer.drawCard(deck);

        }

        // print the dealer's first card only
        System.out.println("Dealer's hand: " + dealer.getHand().getCards().getFirst().toString() + ", Mystery Card");

        // gives hand info and allows player to hit or stand
        boolean userTurnActive = true;
        while (userTurnActive) {

            System.out.print("Your hand: ");
            user.getHand().printHand();
            System.out.println(" (" + user.getHand().getValue() + ")");

            // skips user choice if the user has busted
            if (user.isBusted()) {

                System.out.println("You busted!");
                break;
            }

            // user choice
            System.out.println("Would you like to hit or stand? (H/S)");

            switch (in.nextLine().toLowerCase()) {
                case "h": {
                    System.out.println("You hit.");
                    user.drawCard(deck);
                }
                break;
                case "s": {
                    System.out.println("You stand with " + user.getHand().getValue() + ".");
                    userTurnActive = false;
                }
            }

        }

        // dealer logic
        boolean dealerTurnActive = true;
        while (dealerTurnActive) {

            System.out.print("Dealer's hand: ");
            dealer.getHand().printHand();
            System.out.println(" (" + dealer.getHand().getValue() + ")");

            if (dealer.isBusted()) {

                System.out.println("You busted!");
                break;
            }

            if (dealer.getHand().getValue() <= 17) {

                System.out.println("The dealer hits.");
                dealer.drawCard(deck);

            }
            else {
                System.out.println("The dealer stands with " + dealer.getHand().getValue() + ".");
                dealerTurnActive = false;
            }

        }

        if (user.isBusted()) return;

        if (dealer.isBusted()) {
            System.out.println("You win $" + (bet * 2) + "! (2x payout)");
            // payout money
        }
        else if (user.getHand().getValue() == dealer.getHand().getValue()) {
            System.out.println("Push! You get your bet back.");
            // payout money
        }
        else if (user.getHand().getValue() == 21){
            System.out.println("Blackjack! You win $" + (bet * 3) + "! (3x payout)");
            // payout money
        }
        else if (user.getHand().getValue() > dealer.getHand().getValue()) {
            System.out.println("You win $" + (bet * 2) + "! (2x payout)");
            // payout money
        }
        else {
            System.out.println("Dealer wins! No payout.");
        }

    }

}
