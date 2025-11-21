package game;

public abstract class Card {

    public enum Suit {
        HEARTS, SPADES, CLUBS, DIAMONDS
    }

    public enum Rank {
        TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING, ACE
    }

    protected final Rank rank;
    protected final Suit suit;

    protected int intValue;

    public Card(Rank rank, Suit suit) {
        this.rank = rank;
        this.suit = suit;
        setIntValue();
    }

    public Rank getRank() {
        return rank;
    }

    public Suit getSuit() {
        return suit;
    }

    public int getIntValue() {
        return intValue;
    }

    abstract void setIntValue();

    @Override
    public String toString() {
        return getRankString() + getSuitString();
    }

    public String getRankString() {
        return switch (rank) {
            case TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN -> String.valueOf(rank.ordinal() + 2);
            case JACK -> "J";
            case QUEEN -> "Q";
            case KING -> "K";
            case ACE -> "A";
        };
    }

    public String getSuitString() {
        return switch (suit) {
            case HEARTS -> "♥";
            case SPADES -> "♠";
            case CLUBS -> "♣";
            case DIAMONDS -> "♦";
        };
    }
}
