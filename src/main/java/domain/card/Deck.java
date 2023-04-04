package domain.card;

import java.util.ArrayDeque;
import java.util.Deque;

public final class Deck {

    private final Deque<Card> cards;

    private Deck(final Deque<Card> cards) {
        this.cards = cards;
    }

    public static Deck create() {
        final Deque<Card> cardList = new ArrayDeque<>(52);
        for (Suit suit : Suit.values()) {
            for (Number value : Number.values()) {
                cardList.add(Card.of(suit, value));
            }
        }
        return new Deck(cardList);
    }

    public Card draw() {
        return cards.pollLast();
    }
}
