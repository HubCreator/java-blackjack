package domain.game;

import domain.card.Card;

import java.util.List;
import java.util.Objects;

public final class Score {

    private final int score;

    private Score(final int score) {
        this.score = score;
    }

    public static Score from(final List<Card> cards) {
        return new Score(optimizeScore(cards));
    }

    private static int optimizeScore(final List<Card> cards) {
        int totalScore = cards.stream().mapToInt(Card::getNumber).sum();

        if (totalScore > 21) {
            final long count = cards.stream().filter(Card::isAce).count();
            if (count == 0) {
                return totalScore;
            }
            for (int i = 0; i < count && totalScore > 21; i++) {
                totalScore -= 10;
            }
        }
        return totalScore;
    }

    public static Score of(final int score) {
        return new Score(score);
    }

    public boolean isSameAs(final Score score) {
        return this.equals(score);
    }

    public boolean isOverThan(final Score value) {
        return this.score > value.score;
    }

    public int getScore() {
        return score;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Score other = (Score) o;
        return score == other.score;
    }

    @Override
    public int hashCode() {
        return Objects.hash(score);
    }
}
