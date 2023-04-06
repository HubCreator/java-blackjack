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
            player.take(deck, count);
        }
    }

    public List<Player> findPlayerGreaterThan(final Score score) {
        return players.stream()
                .filter(player -> player.isGreaterThan(score))
                .collect(Collectors.toList());
    }

    public List<Player> findPlayerSameAs(final Score score) {
        return players.stream()
                .filter(player -> player.isSameAs(score))
                .collect(Collectors.toList());
    }

    public List<Player> findPlayersLowerThan(final Score score) {
        return players.stream()
                .filter(player -> player.isLowerThan(score))
                .collect(Collectors.toList());
    }

    public List<Player> getPlayers() {
        return List.copyOf(players);
    }
}
