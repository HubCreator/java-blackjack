package domain.game;

import domain.card.Card;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public final class Hand {

    public static final GamePoint BLACKJACK_POINT = GamePoint.of(21);
    public static final int BLACKJACK_CARD_COUNT = 2;

    private final List<Card> cards;
    private final GamePoint gamePoint;

    private Hand(final List<Card> cards) {
        this.cards = new ArrayList<>(cards);
        this.gamePoint = GamePoint.create(cards);
    }

    public static Hand create(final List<Card> cards) {
        return new Hand(cards);
    }

    public Hand add(final Card card) {
        cards.add(card);
        return create(cards);
    }

    public GamePoint getGamePoint() {
        return gamePoint;
    }

    public List<Card> getCards() {
        return cards.stream()
                .map(Card::of)
                .collect(Collectors.toUnmodifiableList());
    }

    public int cardSize() {
        return cards.size();
    }
}
