package domain.state;

import domain.card.Card;
import domain.game.GamePoint;
import domain.game.Hand;

public final class Hit extends Running {

    public static final GamePoint MAX = GamePoint.of(21);

    Hit(final Hand hand) {
        super(hand);
    }

    @Override
    public State draw(final Card card) {
        this.hand = hand.add(card);
        if (isBust()) {
            return new Bust(hand);
        }
        return new Hit(hand);
    }

    @Override
    public boolean isBust() {
        return hand.getGamePoint().isGreaterThan(MAX);
    }
}
