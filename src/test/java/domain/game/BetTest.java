package domain.game;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
class BetTest {

    @ParameterizedTest
    @ValueSource(ints = {1000, 2000, 10000, 15000})
    void 베팅_금액은_천원_단위로_입력받을_수_있다(int bet) {
        assertDoesNotThrow(() -> Bet.of(bet));
    }

    @ParameterizedTest
    @ValueSource(ints = {1001, 99, 100, 1002})
    void 베팅_금액은_천원_단위가_아니면_예외가_발생한다(int bet) {

        assertThatThrownBy(() -> Bet.of(bet))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("베팅 금액은 1000원 단위여야 합니다.");
    }

}
