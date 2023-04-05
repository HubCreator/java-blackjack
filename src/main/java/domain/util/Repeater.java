package domain.util;

import java.util.function.Supplier;

public final class Repeater {

    private static final String ERROR_HEAD = "[ERROR] ";

    private Repeater() {
    }

    public static <T> T repeat(Supplier<T> input) {
        T result = null;
        while (result == null) {
            try {
                result = input.get();
            } catch (Exception exception) {
                System.out.println(ERROR_HEAD + exception.getMessage());
            }
        }
        return result;
    }
}