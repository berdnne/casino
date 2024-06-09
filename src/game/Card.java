package game;

public abstract class Card {

    protected final String value;
    protected final String suit;

    protected int intValue;

    public Card(String value, String suit) {

        this.value = value;
        this.suit = suit;

        setIntValue();

    }

    public String getValue() {
        return value;
    }

    public String getSuit() {
        return suit;
    }

    public int getIntValue() {
        return intValue;
    }

    abstract void setIntValue();

    @Override
    public String toString() {

        return value + " of " + suit;

    }
}
