package domain.state;

import domain.card.Card;
import domain.game.GamePoint;
import domain.game.Hand;
import domain.participant.Dealer;

import java.util.Collections;
import java.util.List;

public final class Ready extends State {


    public static final GamePoint BLACKJACK_POINT = GamePoint.of(21);
    public static final int BLACKJACK_CARD_COUNT = 2;

    public Ready() {
        super(Hand.create(Collections.emptyList()));
    }

    public Ready(final List<Card> hand) {
        super(Hand.create(hand));
    }

    public Ready(final Hand newHand) {
        super(newHand);
    }

    @Override
    public State draw(final Card card) {
        this.hand = hand.add(card);
        if (isBlackjack()) {
            return new Blackjack(hand);
        }
        if (hand.cardSize() < 2) {
            return new Ready(hand);
        }
        return new Hit(hand);
    }

    @Override
    public State stay() {
        throw new IllegalStateException("게임을 멈출 수 없습니다.");
    }

    @Override
    public double calculateProfit(final double bet, final State dealerState) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isStay() {
        return false;
    }

    @Override
    public boolean isBlackjack() {
        return hand.cardSize() == BLACKJACK_CARD_COUNT &&
                hand.getGamePoint().isSameAs(BLACKJACK_POINT);
    }

    @Override
    public boolean isBust() {
        return false;
    }
}
