package domain.state;

import domain.game.Hand;

public final class Bust extends Finished {
    public Bust(final Hand hand) {
        super(hand);
    }

    @Override
    protected double getProfitRate(final State dealerState) {
        return -1;
    }
}
