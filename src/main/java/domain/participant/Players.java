package domain.participant;

import domain.card.Deck;

import java.util.List;

public final class Players {

    private final List<Player> players;

    private Players(final List<Player> players) {
        this.players = players;
    }

    public static Players from(final List<Player> players) {
        return new Players(players);
    }

    public void take(final Deck deck, final int count) {
        for (Player player : players) {
            for (int i = 0; i < count; i++) {
                player.take(deck.draw());
            }
        }
    }

    public List<Player> getPlayers() {
        return List.copyOf(players);
    }
}
