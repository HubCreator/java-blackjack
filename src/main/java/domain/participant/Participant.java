package domain.participant;

import domain.card.Card;
import domain.deck.DeckStrategy;
import domain.game.GamePoint;
import domain.state.Finished;
import domain.state.Ready;
import domain.state.State;

import java.util.List;

public abstract class Participant {

    protected static final String DEALER_NAME = "딜러";

    private final Name name;
    protected State state;

    protected Participant(final Name name) {
        this.name = name;
        this.state = new Ready();
    }

    protected Participant(final Name name, final List<Card> cards) {
        this.name = name;
        this.state = new Ready(cards);
    }

    public void takeInitialCards(final DeckStrategy deck, final int count) {
        for (int i = 0; i < count; i++) {
            this.state = state.draw(deck.drawCard());
        }
    }

    public void takeCard(final Card card) {
        this.state = state.draw(card);
    }

    public boolean isNotFinished() {
        return !(state instanceof Finished);
    }

    public void stay() {
        state = state.stay();
    }

    public GamePoint getGamePoint() {
        return state.getGamePoint();
    }

    public String getName() {
        return name.getValue();
    }

    public List<Card> getCards() {
        return state.cards();
    }
}
