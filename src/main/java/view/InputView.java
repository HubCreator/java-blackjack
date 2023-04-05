package view;

import java.util.Scanner;

public final class InputView {

    private final Scanner scanner;

    public InputView(final Scanner scanner) {
        this.scanner = scanner;
    }

    public String requestNames() {
        println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        return readLine().trim();
    }

    public int requestBet(final String playerName) {
        lineSeparator();
        println(String.format("%s의 베팅 금액은?", playerName));
        return toNumber(readLine().trim());
    }

    private int toNumber(final String price) {
        try {
            return Integer.parseInt(price);
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException("숫자를 입력해야 합니다.", exception);
        }
    }

    public boolean requestDraw(final String name) {
        lineSeparator();
        println(String.format("%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)", name));
        String input = readLine().trim();
        if (input.equals("y")) {
            return true;
        }
        if (input.equals("n")) {
            return false;
        }
        throw new IllegalArgumentException("y 또는 n을 입력하셔야 합니다.");
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
