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
        final ArrayList<Card> result = new ArrayList<>(cards);
        result.add(card);
        return Hand.create(result);
    }

    public boolean isBlackJack() {
        return gamePoint.isSameAs(BLACKJACK_POINT)
                && cards.size() == BLACKJACK_CARD_COUNT;
    }

    public boolean isBusted() {
        return gamePoint.isBusted();
    }

    public GamePoint getGamePoint() {
        return gamePoint;
    }

    public boolean isStay() {
        return !isBusted() && !isBlackJack();
    }

    public List<Card> getCards() {
        return cards.stream()
                .map(Card::of)
                .collect(Collectors.toUnmodifiableList());
    }

}
