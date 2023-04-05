package domain.participant;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public final class Names {

    private static final String DELIMITER = ",";
    private static final Name dealerName = Name.of(Dealer.DEALER_NAME);

    private final List<Name> names;

    private Names(final List<Name> names) {
        this.names = names;
    }

    public static Names from(final String input) {
        return new Names(validateAndCovert(input));
    }

    private static List<Name> validateAndCovert(final String input) {
        final List<Name> result = Arrays.stream(input.split(DELIMITER))
                .map(name -> Name.of(name.trim()))
                .collect(Collectors.toList());
        validateName(result);
        return result;
    }

    private static void validateName(final List<Name> result) {
        if (result.stream().anyMatch(dealerName::equals)) {
            throw new IllegalArgumentException("참여자는 딜러라는 이름을 사용할 수 없습니다.");
        }
        if (new HashSet<>(result).size() != result.size()) {
            throw new IllegalArgumentException("중복된 이름을 사용할 수 없습니다.");
        }
    }

    public List<Name> getNames() {
        return List.copyOf(names);
    }

    public int size() {
        return names.size();
    }
}
