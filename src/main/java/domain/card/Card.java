package domain.card;

import java.util.Objects;

public final class Card {

    private final Suit suit;
    private final Number value;

    private Card(final Suit suit, final Number value) {
        this.suit = suit;
        this.value = value;
    }

    public static Card of(final Suit suit, final Number value) {
        return new Card(suit, value);
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
        return suit == card.suit && value == card.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(suit, value);
    }
}
