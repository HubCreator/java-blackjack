package view;

import domain.game.Hand;
import domain.participant.Name;

import java.util.Map;
import java.util.stream.Collectors;

public final class OutputView {

    public static final String DELIMITER = ", ";

    public void printInitialStatus(final Map<Name, Hand> initialStatus) {
        String names = initialStatus.keySet().stream()
                .map(Name::getName)
                .collect(Collectors.joining(DELIMITER));
        println(String.format("%s에게 2장을 나누었습니다.", names));

        for (Map.Entry<Name, Hand> entry : initialStatus.entrySet()) {
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

    private void println(final String message) {
        System.out.print(message);
        lineSeparator();
    }

    private void lineSeparator() {
        System.out.print(System.lineSeparator());
    }
}
