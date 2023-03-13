package domain.state;

import domain.game.Hand;

public final class Stay extends Finished {

    public Stay(final Hand hand) {
        super(hand);
    }

    @Override
    protected double getProfitRate(final State dealerState) {
        if (dealerState.isBust()) {
            return 1;
        }
        if (dealerState.isBlackjack()) {
            return -1;
        }
        if (dealerState.isStay()) {
            if (getGamePoint().isGreaterThan(dealerState.getGamePoint())) {
                return 1;
            }
            if (getGamePoint().isSameAs(dealerState.getGamePoint())) {
                return 0;
            }
            if (getGamePoint().isLowerThan(dealerState.getGamePoint())) {
                return -1;
            }
        }
        throw new AssertionError();
    }

    public boolean isStay() {
        return true;
    }

    public boolean isBlackjack() {
        return false;
    }

    public boolean isBust() {
        return false;
    }
}
