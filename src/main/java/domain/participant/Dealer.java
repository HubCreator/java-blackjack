package domain.participant;

import domain.game.Hand;

public final class Dealer {

    private final Name name;
    private Hand hand;

    private Dealer(final Name name) {
        this.name = name;
    }

    public static Dealer create() {
        return new Dealer(Name.of("딜러"));
    }
}
