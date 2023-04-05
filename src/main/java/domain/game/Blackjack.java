package domain.game;

import domain.participant.Dealer;
import domain.participant.Name;
import domain.participant.Names;
import domain.participant.Player;
import domain.participant.Players;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public final class Blackjack {

    private final Players players;
    private final Dealer dealer;

    private Blackjack(final Players players) {
        this.players = players;
        this.dealer = Dealer.create();
    }

    public static Blackjack create(final Names names, final List<Bet> betList) {
        List<Name> nameList = names.getNames();
        List<Player> playerList = IntStream.range(0, names.size())
                .mapToObj(index -> Player.of(nameList.get(index), betList.get(index)))
                .collect(Collectors.toList());
        return new Blackjack(Players.from(playerList));
    }
}
