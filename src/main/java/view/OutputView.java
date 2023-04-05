package view;

import domain.game.Hand;
import domain.participant.Name;

import java.util.List;
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
            String strigifiedCards = entry.getValue().getCards()
                    .stream()
                    .map(CardTranslator::render)
                    .collect(Collectors.joining(DELIMITER));
            println(String.format("%s : %s", name, strigifiedCards));
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
