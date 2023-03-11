package domain.game;

import domain.card.Card;

import java.util.ArrayList;
import java.util.Arrays;
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

    public static Hand create(final Card... cards) {
        return new Hand(Arrays.stream(cards).collect(Collectors.toList()));
    }

    public Hand add(final Card card) {
        List<Card> cardList = new ArrayList<>(cards);
        cardList.add(card);
        return new Hand(cardList);
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

    public List<Card> getCards() {
        return cards.stream()
                .map(Card::of)
                .collect(Collectors.toUnmodifiableList());
    }

    public Card getFirstCard() {
        return cards.get(0);
    }

    public boolean isSameCount(final int count) {
        return this.cards.size() == count;
    }
}
