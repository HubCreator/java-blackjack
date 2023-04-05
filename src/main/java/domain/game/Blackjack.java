package domain.game;

import domain.card.Deck;
import domain.game.result.ProfitResult;
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
        for (Player player : players.getPlayers()) {
            player.take(deck, INITIAL_CARD_COUNT);
        }
        dealer.take(deck, INITIAL_CARD_COUNT);
    }

    public Map<Name, Hand> getInitialStatus() {
        Map<Name, Hand> result = new LinkedHashMap<>();
        result.put(dealer.getName(), dealer.getInitialHand());
        for (Player player : players.getPlayers()) {
            result.put(player.getName(), player.getHand());
        }
        return result;
    }

    public void letPlayerDraw(final Player player) {
        player.take(deck.draw());
    }

    public int finalizeDealerTurn() {
        return dealer.finalizeTurn(deck);
    }

    public List<Player> getPlayers() {
        return players.getPlayers();
    }

    public String getDealerName() {
        return dealer.getName().getName();
    }

    public Map<Name, Hand> getFinalStatus() {
        Map<Name, Hand> result = new LinkedHashMap<>();
        result.put(dealer.getName(), dealer.getHand());
        for (Player player : players.getPlayers()) {
            result.put(player.getName(), player.getHand());
        }
        return result;
    }

    public Map<Name, Score> getScoreStatus() {
        Map<Name, Score> result = new LinkedHashMap<>();
        result.put(dealer.getName(), dealer.getScore());
        for (Player player : players.getPlayers()) {
            result.put(player.getName(), player.getScore());
        }
        return result;
    }

    public ProfitResult getProfitStatus() {
        return ProfitResult.of(dealer, players);
    }
}
