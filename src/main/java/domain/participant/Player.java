package domain.participant;

import domain.card.Card;
import domain.deck.DeckStrategy;
import domain.game.Bet;
import domain.game.GamePoint;

import java.util.List;

public final class Player extends Participant {

    private final Bet bet;

    private Player(final Name name, final int bet) {
        super(name);
        this.bet = Bet.of(bet);
    }

    private Player(final Name name, final List<Card> cards, final int bet) {
        super(name, cards);
        this.bet = Bet.of(bet);
    }

    public static Player of(final Name name, final int bet) {
        validateName(name);
        return new Player(name, bet);
    }

    public static Player create(final Name name, final List<Card> cards, final int bet) {
        validateName(name);
        return new Player(name, cards, bet);
    }

    public void takeInitialCards(final DeckStrategy deck, final int count) {
        for (int i = 0; i < count; i++) {
            this.state = state.draw(deck.drawCard());
        }
    }

    public void takeCard(final Card card) {
        this.state = state.draw(card);
    }

    private static void validateName(final Name name) {
        if (name.getValue().equals(DEALER_NAME)) {
            throw new IllegalArgumentException(
                    String.format("'%s'라는 이름을 가질 수 없습니다.", DEALER_NAME));
        }
    }

    public boolean hasLowerThan(final GamePoint gamePoint) {
        return calculatePoint().isLowerThan(gamePoint);
    }

    public boolean hasSameAs(final GamePoint gamePoint) {
        return calculatePoint().isSameAs(gamePoint);
    }

    public boolean hasGreaterThan(final GamePoint gamePoint) {
        return calculatePoint().isGreaterThan(gamePoint);
    }

    public int getBet() {
        return bet.getBet();
    }
}
