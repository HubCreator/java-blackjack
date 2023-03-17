package domain.state;

import domain.game.Hand;

public final class Blackjack extends Finished {

    public Blackjack(final Hand hand) {
        super(hand);
    }

    @Override
    public boolean isStay() {
        return false;
    }

    @Override
    public boolean isBlackjack() {
        return true;
    }

    @Override
    public boolean isBust() {
        return false;
    }
}
