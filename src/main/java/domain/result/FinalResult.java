package domain.result;

import domain.participant.Dealer;
import domain.participant.Player;
import domain.participant.Players;

import java.util.LinkedHashMap;
import java.util.Map;

public class FinalResult {
    private final Map<Player, Double> playersResult = new LinkedHashMap<>();
    private double dealerProfit = 0;

    private FinalResult(final Dealer dealer, final Players players) {
        for (Player player : players.getPlayers()) {
            playersResult.put(player, player.calculateProfit(dealer));
            dealerProfit -= player.calculateProfit(dealer);
        }
    }

    public static FinalResult of(final Dealer dealer, final Players players) {
        return new FinalResult(dealer, players);
    }

    public Map<Player, Double> getPlayersResult() {
        return playersResult;
    }

    public double getDealerProfit() {
        return dealerProfit;
    }
}
