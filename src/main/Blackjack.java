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
    private static final Scanner scanner = new Scanner(System.in);

    public static void play() {
        final ArrayList<BlackjackCard> deck = BlackjackCard.createSortedDeck();
        Player user = new Player();
        Player dealer = new Player();
        playRound(user, dealer, deck);
    }

    public static void playRound(Player user, Player dealer, ArrayList<BlackjackCard> deck) {
        int bet = getBet(user);
        for (int i = 0; i < 2; i++) {
            user.drawCard(deck);
            dealer.drawCard(deck);
        }
        printDealerHand(dealer.getHand(), true);
        boolean playerBusted = doPlayerTurn(user, deck);
        if (!playerBusted) {
            doDealerTurn(dealer, deck);
            payout(user, dealer, bet);
        } else {
            System.out.println("You busted! No payout.");
        }
    }

    private static void payout(Player user, Player dealer, int bet) {
        int userHandValue = user.getHand().getValue();
        if (dealer.isBusted() && userHandValue == BLACKJACK_SCORE) {
            System.out.println("Blackjack! You win $" + (bet * 3) + "! (3x payout)");
            user.addMoney(bet * 3);
        } else if (dealer.isBusted()) {
            System.out.println("You win $" + (bet * 2) + "! (2x payout)");
            user.addMoney(bet * 2);
        } else if (userHandValue == dealer.getHand().getValue()) {
            System.out.println("Push! You get your bet back.");
            user.addMoney(bet);
        } else if (userHandValue == BLACKJACK_SCORE) {
            System.out.println("Blackjack! You win $" + (bet * 3) + "! (3x payout)");
            user.addMoney(bet * 3);
        } else if (userHandValue > dealer.getHand().getValue()) {
            System.out.println("You win $" + (bet * 2) + "! (2x payout)");
            user.addMoney(bet * 2);
        } else {
            System.out.println("Dealer wins! No payout.");
        }
    }

    private static void doDealerTurn(Player dealer, ArrayList<BlackjackCard> deck) {
        Hand dealerHand = dealer.getHand();
        boolean dealerTurnActive = true;
        while (dealerTurnActive) {
            printDealerHand(dealerHand, false);
            if (dealer.isBusted()) {
                System.out.println("The dealer busted!");
                dealerTurnActive = false;
                continue;
            }
            if (dealerHand.getValue() <= Blackjack.DEALER_HIT_THRESHOLD) {
                System.out.println("The dealer hits.");
                dealer.drawCard(deck);
            } else {
                System.out.println("The dealer stands.");
                dealerTurnActive = false;
            }
        }
    }

    private static void printDealerHand(Hand dealerHand, boolean concealLastCard) {
        if (concealLastCard) {
            System.out.println("Dealer's hand: " + dealerHand.getCards().getFirst() + ", ?");
        } else {
            System.out.println("Dealer's hand: " + dealerHand + " (" + dealerHand.getValue() + ")");
        }
    }

    private static boolean doPlayerTurn(Player user, ArrayList<BlackjackCard> deck) {
        Hand userHand = user.getHand();
        while (!user.isBusted()) {
            printUserHand(userHand);
            System.out.println("Would you like to hit or stand? (H/S)");
            switch (scanner.nextLine().toLowerCase()) {
                case "h": {
                    System.out.println("You hit.");
                    user.drawCard(deck);
                }
                break;
                case "s": {
                    System.out.println("You stand with " + user.getHand().getValue() + ".");
                    return false;
                }
            }
        }
        System.out.println("Your hand: " + userHand + " (" + userHand.getValue() + ")");
        return true;
    }

    private static void printUserHand(Hand userHand) {
        System.out.println("Your hand: " + userHand + " (" + userHand.getValue() + ")");
    }

    private static int getBet(Player user) {
        int bet = 0;
        while (bet <= 0 || bet > user.getMoney()) {
            System.out.println("You have $" + user.getMoney());
            System.out.print("Please enter a bet (at least $1): ");
            bet = scanner.nextInt();
            scanner.nextLine();
        }
        user.removeMoney(bet);
        return bet;
    }

}
