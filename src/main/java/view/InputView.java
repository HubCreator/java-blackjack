package view;

import java.util.Scanner;

public final class InputView {

    private final Scanner scanner;

    public InputView(final Scanner scanner) {
        this.scanner = scanner;
    }

    public String requestNames() {
        println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        return readLine();
    }

    public double requestBet(final String playerName) {
        lineSeparator();
        println(String.format("%s의 배팅 금액은?", playerName));
        return toNumber(readLine());
    }

    private double toNumber(final String price) {
        try {
            return Double.parseDouble(price);
        } catch (NumberFormatException exception) {
            println("숫자를 입력해야 합니다.");
            throw new IllegalArgumentException(exception);
        }
    }

    private String readLine() {
        return scanner.nextLine();
    }

    private void println(final String message) {
        System.out.print(message);
        lineSeparator();
    }

    private void lineSeparator() {
        System.out.print(System.lineSeparator());
    }
}
