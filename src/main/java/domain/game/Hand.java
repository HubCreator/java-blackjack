package domain.game;

import domain.card.Card;

import java.util.ArrayList;
import java.util.List;

public final class Hand {

    public static final Score BLACKJACK_SCORE = Score.of(21);
    public static final Score DEALER_STANDARD_SCORE = Score.of(16);

    private final List<Card> cards;
    private final Score score;

    private Hand(final List<Card> cards) {
        this.cards = cards;
        this.score = Score.from(cards);
    }

    public static Hand of(final List<Card> cards) {
        return new Hand(cards);
    }

    public Hand take(final Card card) {
        final ArrayList<Card> newCards = new ArrayList<>(cards);
        newCards.add(card);
        return of(newCards);
    }

    public boolean isBusted() {
        return score.isOverThan(BLACKJACK_SCORE);
    }

    public boolean isBlackjack() {
        return score.isSameAs(BLACKJACK_SCORE);
    }

    public boolean isOverDealerStandard() {
        return score.isOverThan(DEALER_STANDARD_SCORE);
    }

    public List<Card> getCards() {
        return List.copyOf(cards);
    }

    public Score getScore() {
        return score;
    }

    public boolean hasLowerScoreThan(final Score score) {
        return this.score.getScore() < score.getScore();
    }

    public boolean hasSameScoreAs(final Score score) {
        return this.score.getScore() == score.getScore();
    }

    public boolean hasGreaterScoreThan(final Score score) {
        return this.score.getScore() > score.getScore();
    }
}
