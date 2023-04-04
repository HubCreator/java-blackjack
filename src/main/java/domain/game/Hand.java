package domain.game;

import domain.card.Card;

import java.util.ArrayList;
import java.util.List;

public final class Hand {

    private final List<Card> cards;

    private Hand(final List<Card> cards) {
        this.cards = cards;
    }

    public static Hand of(final List<Card> cards) {
        return new Hand(cards);
    }

    public Hand take(final Card card) {
        final ArrayList<Card> newCards = new ArrayList<>(cards);
        newCards.add(card);
        return of(newCards);
    }

    public List<Card> getCards() {
        return List.copyOf(cards);
    }
}
