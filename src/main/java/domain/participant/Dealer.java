package domain.participant;

import domain.card.Card;
import domain.game.GamePoint;

import java.util.List;

public final class Dealer extends Participant {

    private Dealer(final Name name) {
        super(name);
    }

    public static Dealer create() {
        return new Dealer(Name.of(DEALER_NAME));
    }

    public Card getFirstCard() {
        final List<Card> cards = state.cards();
        return cards.get(0);
    }
}
