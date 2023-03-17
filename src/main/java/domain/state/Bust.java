package domain.state;

import domain.game.Hand;

public final class Bust extends Finished {

    public Bust(final Hand hand) {
        super(hand);
    }

    @Override
    public boolean isStay() {
        return false;
    }

    @Override
    public boolean isBlackjack() {
        return false;
    }

    @Override
    public boolean isBust() {
        return true;
    }
}
