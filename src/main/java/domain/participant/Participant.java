package domain.participant;

import domain.card.Card;
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

    public GamePoint getGamePoint() {
        return state.getGamePoint();
    }

    public boolean isBust() {
        return state.isBust();
    }

    public boolean isBlackjack() {
        return state.isBlackjack();
    }

    public void stay() {
        this.state = state.stay();
    }

    public String getName() {
        return name.getValue();
    }

    public List<Card> getCards() {
        return state.cards();
    }

    public boolean isNotFinished() {
        return !(state instanceof Finished);
    }
}
