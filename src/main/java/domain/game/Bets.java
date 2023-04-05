package domain.game;

import java.util.List;

public final class Bets {

    private final List<Bet> bets;

    private Bets(final List<Bet> bets) {
        this.bets = bets;
    }

    public static Bets from(final List<Bet> bets) {
        return new Bets(bets);
    }

    public List<Bet> getBets() {
        return List.copyOf(bets);
    }

    public int size() {
        return bets.size();
    }
}
