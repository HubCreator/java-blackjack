package domain.state;

import domain.game.Hand;

public final class Blackjack extends Finished {
    public Blackjack(final Hand hand) {
        super(hand);
    }
}
