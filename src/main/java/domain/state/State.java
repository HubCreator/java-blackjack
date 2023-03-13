package domain.state;

import domain.card.Card;
import domain.game.GamePoint;
import domain.game.Hand;

import java.util.List;

public abstract class State {

    public static final GamePoint BUST_POINT = GamePoint.of(0);
    public static final GamePoint BLACKJACK_POINT = GamePoint.of(21);
    public static final int BLACKJACK_CARD_COUNT = 2;

    protected Hand hand;

    protected State(final Hand hand) {
        this.hand = hand;
    }

    public abstract State draw(Card card);

    public abstract State stay();

    public abstract double calculateProfit(double bet, State dealerState);

    public final List<Card> cards() {
        return hand.getCards();
    }

    public GamePoint getGamePoint() {
        return hand.getGamePoint();
    }

    public boolean isStay() {
        return this instanceof Stay;
    }

    public boolean isBlackjack() {
        return hand.cardSize() == BLACKJACK_CARD_COUNT &&
                hand.getGamePoint().isSameAs(BLACKJACK_POINT);
    }

    public boolean isBust() {
        return hand.getGamePoint().isSameAs(BUST_POINT);
    }
}
