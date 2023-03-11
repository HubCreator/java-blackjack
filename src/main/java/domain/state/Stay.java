package domain.state;

import domain.game.Hand;

public final class Stay extends Finished {

    public Stay(final Hand hand) {
        super(hand);
    }
}
