package domain.participant;

import java.util.Objects;

public final class Name {

    private static final int MAX_LENGTH = 5;

    private final String name;

    private Name(final String name) {
        this.name = name;
    }

    public static Name of(final String name) {
        validateName(Objects.requireNonNull(name));
        return new Name(name);
    }

    private static void validateName(final String name) {
        if (name.isBlank()) {
            throw new IllegalArgumentException("이름은 빈 칸일 수 없습니다.");
        }
        if (name.length() > MAX_LENGTH) {
            throw new IllegalArgumentException(
                    String.format("이름의 길이는 %d글자 이하여야 합니다.", MAX_LENGTH)
            );
        }
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Name otherName = (Name) o;
        return Objects.equals(name, otherName.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
