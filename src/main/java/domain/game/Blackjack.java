package domain.game;

import domain.card.Deck;
import domain.participant.Dealer;
import domain.participant.Name;
import domain.participant.Names;
import domain.participant.Player;
import domain.participant.Players;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public final class Blackjack {

    public static final int INITIAL_CARD_COUNT = 2;

    private final Players players;
    private final Dealer dealer;
    private final Deck deck;

    private Blackjack(final Players players) {
        this.players = players;
        this.dealer = Dealer.create();
        this.deck = Deck.create();
        init();
    }

    public static Blackjack create(final Names names, final Bets bets) {
        if (names.size() != bets.size()) {
            throw new IllegalArgumentException("플레이어의 수와 베팅 금액의 수가 일치하지 않습니다.");
        }
        List<Name> nameList = names.getNames();
        List<Bet> betList = bets.getBets();
        List<Player> playerList = IntStream.range(0, names.size())
                .mapToObj(index -> Player.of(nameList.get(index), betList.get(index)))
                .collect(Collectors.toList());
        return new Blackjack(Players.from(playerList));
    }

    private void init() {
        for (int i = 0; i < INITIAL_CARD_COUNT; i++) {
            for (Player player : players.getPlayers()) {
                player.take(deck.draw());
            }
            dealer.take(deck.draw());
        }
    }

    public void letPlayerDraw(final Player player) {
        player.take(deck.draw());
    }

    public Map<Name, Hand> getInitialStatus() {
        Map<Name, Hand> result = new LinkedHashMap<>();
        result.put(dealer.getName(), dealer.getInitialHand());
        for (Player player : players.getPlayers()) {
            result.put(player.getName(), player.getHand());
        }
        return result;
    }

    public List<Player> getPlayers() {
        return players.getPlayers();
    }
}
