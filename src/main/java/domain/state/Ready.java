package domain.state;

import domain.card.Card;
import domain.game.Hand;

public final class Ready extends State {

    Ready() {
        super(Hand.create());
    }

    @Override
    public State draw(final Card card) {
        final Hand newHand = hand.add(card);
        return new Hit(newHand);
    }

    @Override
    public State stay() {
        throw  new IllegalStateException("게임을 멈출 수 없습니다.");
    }

}
