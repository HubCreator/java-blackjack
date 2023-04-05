import controller.BlackjackController;
import view.InputView;
import view.OutputView;

import java.util.Scanner;

public final class Application {

    public static void main(String[] args) {
        try (final Scanner scanner = new Scanner(System.in)) {
            final BlackjackController blackjackController = new BlackjackController(
                    new InputView(scanner), new OutputView()
            );
            blackjackController.process();
        }
    }
}
