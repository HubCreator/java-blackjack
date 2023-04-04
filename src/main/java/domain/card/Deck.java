package domain.card;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

public final class Deck {

    private final Deque<Card> cards;

    private Deck(final Deque<Card> cards) {
        this.cards = cards;
    }

    public static Deck create() {
        final List<Card> cardList = new ArrayList<>(52);
        for (Suit suit : Suit.values()) {
            for (Number value : Number.values()) {
                cardList.add(Card.of(suit, value));
            }
        }
        Collections.shuffle(cardList);
        return new Deck(new ArrayDeque<>(cardList));
    }

    public Card draw() {
        return cards.pollLast();
    }

    public int leftCardCount() {
        return cards.size();
    }
}
