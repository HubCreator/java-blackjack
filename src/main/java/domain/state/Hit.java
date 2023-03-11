package domain.state;

import domain.card.Card;
import domain.game.Hand;

public final class Hit extends Running {

    Hit(final Hand hand) {
        super(hand);
    }

    @Override
    public State draw(final Card card) {
        final Hand newHand = hand.add(card);
        if (newHand.isBlackJack()) {
            return new Blackjack(newHand);
        }
        if (newHand.isBusted()) {
            return new Bust(newHand);
        }
        return new Hit(newHand);
    }
}
