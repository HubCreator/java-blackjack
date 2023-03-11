package domain.participant;

import domain.card.Card;
import domain.game.GamePoint;
import domain.state.Blackjack;
import domain.state.Bust;
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

    public GamePoint calculatePoint() {
        return state.getGamePoint();
    }

    public boolean isBusted() {
        return state instanceof Bust;
    }

    public boolean isBlackJack() {
        return state instanceof Blackjack;
    }

    public Name getName() {
        return name;
    }

    public List<Card> getCards() {
        return state.cards();
    }

    public State getState() {
        return state;
    }

    public boolean isNotFinished() {
        return !(state instanceof Finished);
    }
}
