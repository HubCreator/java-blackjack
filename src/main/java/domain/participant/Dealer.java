package domain.participant;

import domain.card.Card;
import domain.deck.DeckStrategy;
import domain.game.GamePoint;

import java.util.List;

public final class Dealer extends Participant {

    public static final GamePoint STANDARD_OF_NEED_MORE_CARD = GamePoint.of(16);

    private Dealer(final Name name) {
        super(name);
    }

    public static Dealer create() {
        return new Dealer(Name.of(DEALER_NAME));
    }

    public void takeInitialCards(final DeckStrategy deck, final int count) {
        for (int i = 0; i < count; i++) {
            this.state = state.draw(deck.drawCard());
        }
    }

    public void takeCard(final Card card) {
        this.state = state.draw(card);
    }

    public boolean needMoreCard() {
        return getGamePoint().isLowerThan(STANDARD_OF_NEED_MORE_CARD) && !this.isBust();
    }

    public Card getFirstCard() {
        final List<Card> cards = state.cards();
        return cards.get(0);
    }
}
