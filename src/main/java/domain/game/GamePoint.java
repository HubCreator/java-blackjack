package domain.game;

import domain.card.Card;

import java.util.List;

public final class GamePoint {

    public static final int ACE = 1;
    public static final int ACE_UPPER = 11;
    public static final int FACE_CARD = 10;
    public static final int MAX = 21;

    private final int gamePoint;

    private GamePoint(final int gamePoint) {
        this.gamePoint = gamePoint;
    }

    private GamePoint(final List<Card> cards) {
        this.gamePoint = getPoint(calculateMaxPoint(cards), getCountOfAce(cards));
    }

    public static GamePoint create(final List<Card> cards) {
        return new GamePoint(cards);
    }

    public static GamePoint of(final int gamePoint) {
        return new GamePoint(gamePoint);
    }

    private int getPoint(int point, int aceCount) {
        while (point > MAX && aceCount > 0) {
            point -= 10;
            aceCount -= 1;
        }
        return point;
    }

    private int calculateMaxPoint(final List<Card> cards) {
        return cards.stream()
                .mapToInt(this::transform)
                .sum();
    }

    private int getCountOfAce(final List<Card> cards) {
        return (int) cards.stream()
                .filter(card -> card.isSameAs(ACE))
                .count();
    }

    private int transform(Card card) {
        if (card.isSameAs(ACE)) {
            return ACE_UPPER;
        }
        if (card.isOver(FACE_CARD)) {
            return FACE_CARD;
        }
        return card.getCardNumberValue();
    }

    public int getPoint() {
        return gamePoint;
    }

    public boolean isLowerThan(final GamePoint point) {
        return gamePoint < point.gamePoint;
    }

    public boolean isGreaterThan(final GamePoint point) {
        return gamePoint > point.gamePoint;
    }

    public boolean isSameAs(final GamePoint point) {
        return gamePoint == point.gamePoint;
    }
}
