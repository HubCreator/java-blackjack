package domain;

public final class Name {

    public static final int MIN_LENGTH = 5;
    private final String name;

    private Name(final String name) {
        this.name = name;
    }

    public static Name of(final String name) {
        validateName(name);
        return new Name(name);
    }

    private static void validateName(final String name) {
        if (name.length() > MIN_LENGTH) {
            throw new IllegalArgumentException(
                    String.format("이름의 길이는 %d글자 이하여야 합니다.", MIN_LENGTH)
            );
        }
    }
}
