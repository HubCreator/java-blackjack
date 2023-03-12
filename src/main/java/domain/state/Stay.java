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
            if (this.getGamePoint().isGreaterThan(dealerState.getGamePoint())) {
                return 1;
            }
            if (this.getGamePoint().isSameAs(dealerState.getGamePoint())) {
                return 0;
            }
            if (this.getGamePoint().isLowerThan(dealerState.getGamePoint())) {
                return -1;
            }
        }
        throw new AssertionError();
    }
}
