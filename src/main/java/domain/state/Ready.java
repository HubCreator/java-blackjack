package domain.state;

import domain.card.Card;
import domain.game.Hand;
import domain.participant.Dealer;

import java.util.Collections;
import java.util.List;

public final class Ready extends State {

    public Ready() {
        super(Hand.create(Collections.emptyList()));
    }

    public Ready(final List<Card> hand) {
        super(Hand.create(hand));
    }

    @Override
    public State draw(final Card card) {
        final Hand newHand = hand.add(card);
        if (newHand.isBlackJack()) {
            return new Blackjack(newHand);
        }
        if (newHand.isBusted()) {
            return new Bust(newHand);
        }
        return new Hit(newHand);
    }

    @Override
    public State stay() {
        throw new IllegalStateException("게임을 멈출 수 없습니다.");
    }

    @Override
    public double calculateProfit(final double bet, final State dealerState) {
        throw new UnsupportedOperationException();
    }

}
