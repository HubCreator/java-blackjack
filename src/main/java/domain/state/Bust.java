package domain.state;

import domain.game.Hand;

public final class Bust extends Finished {
    public Bust(final Hand hand) {
        super(hand);
    }
}
