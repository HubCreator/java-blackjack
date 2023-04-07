package domain.game.result;

import domain.game.Score;
import domain.participant.Dealer;
import domain.participant.Player;
import domain.participant.Players;

import java.util.Collections;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public final class ProfitResult {

    private final Map<GameResult, List<Player>> playerResult = new EnumMap<>(GameResult.class);
    private final int dealerProfit;

    private ProfitResult(final Dealer dealer, final Players players) {
        final Score dealerScore = dealer.getScore();
        updateGameResult(GameResult.WIN, players.findPlayerGreaterThan(dealerScore));
        updateGameResult(GameResult.DRAW, players.findPlayerSameAs(dealerScore));
        updateGameResult(GameResult.LOSE, players.findPlayersLowerThan(dealerScore));
        this.dealerProfit = calculateDealerProfit();
    }

    public static ProfitResult of(final Dealer dealer, final Players players) {
        return new ProfitResult(dealer, players);
    }

    private void updateGameResult(final GameResult gameResult, final List<Player> players) {
        if (players.size() > 0) {
            playerResult.put(gameResult, Collections.unmodifiableList(players));
        }
    }

    private int calculateDealerProfit() {
        return -1 * playerResult.entrySet().stream()
                .flatMap(entry -> entry.getValue().stream()
                        .map(player -> entry.getKey().calculateProfit(player)))
                .mapToInt(Integer::intValue)
                .sum();
    }

    public Map<GameResult, List<Player>> getResult() {
        return new EnumMap<>(playerResult);
    }

    public int getDealerProfit() {
        return dealerProfit;
    }
}
