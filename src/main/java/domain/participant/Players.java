package domain.participant;

import domain.card.Deck;
import domain.game.Score;

import java.util.List;
import java.util.stream.Collectors;

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

    public List<Player> findPlayersLowerThan(final Score score) {
        return players.stream()
                .filter(player -> player.hasLowerThan(score))
                .collect(Collectors.toUnmodifiableList());
    }

    public List<Player> findPlayerSameAs(final Score score) {
        return players.stream()
                .filter(player -> player.hasSameAs(score))
                .collect(Collectors.toUnmodifiableList());
    }

    public List<Player> findPlayerGreaterThan(final Score score) {
        return players.stream()
                .filter(player -> player.hasGreaterThan(score))
                .collect(Collectors.toUnmodifiableList());
    }

    public List<Player> getPlayers() {
        return List.copyOf(players);
    }
}
