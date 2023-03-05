package blackjack.domain.card;

import java.util.Objects;

public class Card {

    private final Shape shape;
    private final CardNumber cardNumber;

    public Card(final Shape shape, final CardNumber cardNumber) {
        this.shape = shape;
        this.cardNumber = cardNumber;
    }

    public boolean isSameAs(int value) {
        return cardNumber.getValue() == value;
    }

    public boolean isOver(final int value) {
        return cardNumber.getValue() > value;
    }

    public int getCardNumberValue() {
        return cardNumber.getValue();
    }

    public Shape getShape() {
        return shape;
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
        return shape == card.shape && Objects.equals(cardNumber, card.cardNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(shape, cardNumber);
    }
}
