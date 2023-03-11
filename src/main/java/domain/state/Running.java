package domain.state;

import domain.card.Card;
import domain.game.Hand;


public abstract class Running extends State {

    protected Running(final Hand hand) {
        super(hand);
    }

    @Override
    public State stay() {
        return new Stay(hand);
    }

    protected Hand add(final Card card) {
        return hand.add(card);
    }

}
