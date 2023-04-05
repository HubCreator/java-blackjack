package view;

import domain.game.Hand;
import domain.game.Score;
import domain.game.result.GameResult;
import domain.game.result.ProfitResult;
import domain.participant.Dealer;
import domain.participant.Name;
import domain.participant.Player;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class OutputView {

    public static final String DELIMITER = ", ";
    private static final String FINAL_RESULT_FORMAT = "%s: %s";

    public void printInitialStatus(final Map<Name, Hand> handStatus) {
        lineSeparator();
        String names = handStatus.keySet().stream()
                .map(Name::getName)
                .collect(Collectors.joining(DELIMITER));
        println(String.format("%s에게 2장을 나누었습니다.", names));

        for (Map.Entry<Name, Hand> entry : handStatus.entrySet()) {
            String name = entry.getKey().getName();
            String strigifiedCards = stringifyCards(entry.getValue());
            println(String.format("%s : %s", name, strigifiedCards));
        }
    }

    public void printPlayerStatus(final String name, final Hand hand) {
        println(String.format("%s의 카드 : %s", name, stringifyCards(hand)));
    }

    private static String stringifyCards(final Hand hand) {
        return hand.getCards()
                .stream()
                .map(CardTranslator::render)
                .collect(Collectors.joining(DELIMITER));
    }

    public void printDealerDrawCount(final String dealerName, final int count) {
        lineSeparator();
        if (count > 0) {
            println(String.format("%s는 16이하라 %d장의 카드를 더 받았습니다.", dealerName, count));
            return;
        }
        println(String.format("%s는 17이상이라 카드를 더 받지 않았습니다.", dealerName));
    }

    public void printFinalStatus(final Map<Name, Hand> handStatus, final Map<Name, Score> scoreStatus) {
        lineSeparator();
        for (Map.Entry<Name, Hand> entry : handStatus.entrySet()) {
            String name = entry.getKey().getName();
            String strigifiedCards = stringifyCards(entry.getValue());
            Score score = scoreStatus.get(entry.getKey());
            println(String.format("%s : %s - 결과: %d", name, strigifiedCards, score.getScore()));
        }
    }

    public void printProfit(final String dealerName, final ProfitResult profitResult) {
        lineSeparator();
        println("## 최종 수익");
        printDealerResult(dealerName, profitResult);
        printPlayersResult(profitResult.getResult());
    }

    private void printDealerResult(final String dealerName, final ProfitResult profitResult) {
        println(
                String.format(
                        FINAL_RESULT_FORMAT,
                        dealerName,
                        profitResult.getDealerProfit())
        );
    }

    private void printPlayersResult(final Map<GameResult, List<Player>> gameResult) {
        for (Map.Entry<GameResult, List<Player>> entry : gameResult.entrySet()) {
            printPlayerResult(entry);
        }
    }

    private void printPlayerResult(final Map.Entry<GameResult, List<Player>> entry) {
        final List<Player> players = entry.getValue();
        for (Player player : players) {
            println(
                    String.format(
                            FINAL_RESULT_FORMAT,
                            player.getName().getName(),
                            entry.getKey().calculateProfit(player)
                    )
            );
        }
    }

    private void println(final String message) {
        System.out.print(message);
        lineSeparator();
    }

    private void lineSeparator() {
        System.out.print(System.lineSeparator());
    }
}
