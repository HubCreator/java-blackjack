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
        this.dealerProfit = updateDealerProfit();
    }

    public static ProfitResult of(final Dealer dealer, final Players players) {
        return new ProfitResult(dealer, players);
    }

    private void updateGameResult(final GameResult gameResult, final List<Player> players) {
        if (players.size() > 0) {
            playerResult.put(gameResult, Collections.unmodifiableList(players));
        }
    }

    private int updateDealerProfit() {
        int result = 0;
        for (Map.Entry<GameResult, List<Player>> entry : playerResult.entrySet()) {
            result += getProfit(entry);
            result -= getLoss(entry);
        }
        return result;
    }

    private static int getProfit(final Map.Entry<GameResult, List<Player>> entry) {
        if (GameResult.LOSE == entry.getKey()) {
            return -entry.getValue().stream()
                    .mapToInt(GameResult.LOSE::calculateProfit)
                    .sum();
        }
        return 0;
    }

    private static int getLoss(final Map.Entry<GameResult, List<Player>> entry) {
        if (GameResult.WIN == entry.getKey()) {
            return entry.getValue().stream()
                    .mapToInt(GameResult.WIN::calculateProfit)
                    .sum();
        }
        return 0;
    }

    public Map<GameResult, List<Player>> getResult() {
        return playerResult;
    }

    public int getDealerProfit() {
        return dealerProfit;
    }
}
