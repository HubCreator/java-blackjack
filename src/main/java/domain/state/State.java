package domain.state;

import domain.card.Card;
import domain.game.Hand;

import java.util.List;

public abstract class State {

    protected Hand hand;

    protected State(final Hand hand) {
        this.hand = hand;
    }

    public abstract State draw(Card card);

    public abstract State stay();

    public abstract double calculateProfit(double bet);

    public final List<Card> cards() {
        return hand.getCards();
    }

}
