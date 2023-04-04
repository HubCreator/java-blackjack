package domain.participant;

import domain.game.Bet;
import domain.game.Hand;

public final class Player {

    private final Name name;
    private final Bet bet;
    private Hand hand;

    private Player(final Name name, final Bet bet) {
        this.name = name;
        this.bet = bet;
    }

    public static Player of(final Name name, final Bet bet) {
        return new Player(name, bet);
    }

}
