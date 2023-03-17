package domain.state;

import domain.game.Hand;

public final class Stay extends Finished {

    public Stay(final Hand hand) {
        super(hand);
    }

    @Override
    public boolean isStay() {
        return true;
    }

    @Override
    public boolean isBlackjack() {
        return false;
    }

    @Override
    public boolean isBust() {
        return false;
    }
}
