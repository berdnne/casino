package main;

import game.BlackjackCard;
import game.Hand;
import game.Player;

import java.util.ArrayList;
import java.util.Scanner;

public class Blackjack {

    public static final int DEALER_HIT_THRESHOLD = 17;
    public static final int STARTING_MONEY = 100;
    public static final int BLACKJACK_SCORE = 21;

    public static void play(Scanner scanner) {
        final ArrayList<BlackjackCard> deck = BlackjackCard.createSortedDeck();
        Player user = new Player();
        Player dealer = new Player();
        playRound(scanner, user, dealer, deck);
    }

    // TODO: temporary bet variable
    public static void playRound(Scanner scanner, Player user, Player dealer, ArrayList<BlackjackCard> deck) {
        int bet = 0;
        while (bet <= 0 || bet > user.getMoney()) {
            System.out.println("You have $" + user.getMoney());
            System.out.print("Please enter a bet (at least $1): ");
            bet = scanner.nextInt();
            scanner.nextLine();
        }
        user.removeMoney(bet);

        // deal cards
        for (int i = 0; i < 2; i++) {
            user.drawCard(deck);
            dealer.drawCard(deck);
        }

        // print the dealer's first card only
        System.out.println("Dealer's hand: " + dealer.getHand().getCards().getFirst().toString() + ", MYSTERY");

        // gives hand info and allows player to hit or stand
        boolean userTurnActive = true;
        while (userTurnActive) {
            Hand userHand = user.getHand();
            System.out.println("Your hand: " + userHand.toString() + " (" + userHand.getValue() + ")");
            if (user.isBusted()) {
                System.out.println("You busted!");
                userTurnActive = false;
                continue;
            }
            System.out.println("Would you like to hit or stand? (H/S)");
            switch (scanner.nextLine().toLowerCase()) {
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
            System.out.print("Dealer's hand: " + dealer.getHand().toString() + " (" + dealer.getHand().getValue() + ")");
            if (dealer.isBusted()) {
                System.out.println("The dealer busted!");
                dealerTurnActive = false;
                continue;
            }
            if (dealer.getHand().getValue() <= Blackjack.DEALER_HIT_THRESHOLD) {
                System.out.println("The dealer hits.");
                dealer.drawCard(deck);
            } else {
                System.out.println("The dealer stands with " + dealer.getHand().getValue() + ".");
                dealerTurnActive = false;
            }
        }

        if (user.isBusted()) {
            System.out.println("No payout because you busted.");
            return;
        }

        if (dealer.isBusted()) {
            System.out.println("You win $" + (bet * 2) + "! (2x payout)");
            user.addMoney(bet * 2);
        } else if (user.getHand().getValue() == dealer.getHand().getValue()) {
            System.out.println("Push! You get your bet back.");
            user.addMoney(bet);
        } else if (user.getHand().getValue() == BLACKJACK_SCORE) {
            System.out.println("main.Casino! You win $" + (bet * 3) + "! (3x payout)");
            user.addMoney(bet * 3);
        } else if (user.getHand().getValue() > dealer.getHand().getValue()) {
            System.out.println("You win $" + (bet * 2) + "! (2x payout)");
            user.addMoney(bet * 2);
        } else {
            System.out.println("Dealer wins! No payout.");
        }
    }

}
