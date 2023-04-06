package domain.game;

import domain.card.Card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class Hand {

    private static final int BLACKJACK_CARD_COUNT = 2;
    public static final int BLACKJACK_NUMBER = 21;
    public static final int DEALER_STANDARD_NUMBER = 16;
    private static final Score BLACKJACK_SCORE = Score.valueOf(BLACKJACK_NUMBER);
    private static final Score DEALER_STANDARD_SCORE = Score.valueOf(DEALER_STANDARD_NUMBER);

    private final List<Card> cards;
    private final Score score;

    private Hand(final List<Card> cards) {
        this.cards = cards;
        this.score = Score.from(cards);
    }

    public static Hand from(final List<Card> cards) {
        return new Hand(cards);
    }

    public static Hand empty() {
        return new Hand(Collections.emptyList());
    }

    public Hand take(final Card card) {
        final ArrayList<Card> newCards = new ArrayList<>(cards);
        newCards.add(card);
        return from(newCards);
    }

    public boolean isBusted() {
        return score.isGreaterThan(BLACKJACK_SCORE);
    }

    public boolean isBlackjack() {
        return score.isSameAs(BLACKJACK_SCORE) && cards.size() == BLACKJACK_CARD_COUNT;
    }

    public boolean isOverDealerStandard() {
        return score.isGreaterThan(DEALER_STANDARD_SCORE);
    }

    public boolean isLowerThan(final Score score) {
        return this.score.isLowerThan(score);
    }

    public boolean isSameAs(final Score score) {
        return this.score.isSameAs(score);
    }

    public boolean isGreaterThan(final Score score) {
        return this.score.isGreaterThan(score);
    }

    public List<Card> getCards() {
        return List.copyOf(cards);
    }

    public Score getScore() {
        return score;
    }

    public int getScoreValue() {
        return score.getScore();
    }
}
