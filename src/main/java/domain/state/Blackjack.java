package domain.state;

import domain.game.Hand;

public final class Blackjack extends Finished {

    public Blackjack(final Hand hand) {
        super(hand);
    }

    @Override
    protected double getProfitRate(final State dealerState) {
        if (dealerState.isBlackjack()) {
            return 0;
        }
        return 1.5;
    }
}
