package view;

import domain.game.Hand;
import domain.game.Score;
import domain.participant.Name;

import java.util.Map;
import java.util.stream.Collectors;

public final class OutputView {

    public static final String DELIMITER = ", ";

    public void printInitialStatus(final Map<Name, Hand> handStatus) {
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
        if (count > 0) {
            println(String.format("%s는 16이하라 %d 장의 카드를 더 받았습니다.", dealerName, count));
            return;
        }
        println(String.format("%s는 17이상이라 카드를 더 받지 않았습니다.", dealerName));
    }

    public void printFinalStatus(final Map<Name, Hand> handStatus, final Map<Name, Score> scoreStatus) {
        for (Map.Entry<Name, Hand> entry : handStatus.entrySet()) {
            String name = entry.getKey().getName();
            String strigifiedCards = stringifyCards(entry.getValue());
            Score score = scoreStatus.get(entry.getKey());
            println(String.format("%s : %s - 결과: %d", name, strigifiedCards, score.getScore()));
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
