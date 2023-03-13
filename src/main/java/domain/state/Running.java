package domain.state;

import domain.game.GamePoint;
import domain.game.Hand;

public abstract class Running extends State {

    public static final GamePoint BUST_POINT = GamePoint.of(0);

    protected Running(final Hand hand) {
        super(hand);
    }

    @Override
    public final State stay() {
        return new Stay(hand);
    }

    @Override
    public boolean isStay() {
        return false;
    }

    @Override
    public boolean isBlackjack() {
        return false;
    }

}
