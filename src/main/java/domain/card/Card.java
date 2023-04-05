package domain.card;

import java.util.Objects;

public final class Card {

    private final Suit suit;
    private final Number number;

    private Card(final Suit suit, final Number number) {
        this.suit = suit;
        this.number = number;
    }

    public static Card of(final Suit suit, final Number value) {
        return new Card(suit, value);
    }

    public boolean isAce() {
        return number == Number.ACE;
    }

    public Number getNumber() {
        return number;
    }

    public Suit getSuit() {
        return suit;
    }

    @Override
    public boolean equals(final Object o) {

        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Card card = (Card) o;
        return suit == card.suit && number == card.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(suit, number);
    }
}
