package domain;


import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
class NameTest {

    @Test
    void 이름을_생성한다() {
        // given
        final Name name = Name.of("hello");

        // expect
        assertDoesNotThrow(() -> name);
    }

    @Test
    void 이름이_5글자가_넘어가면_예외가_발생한다() {
        assertThatThrownBy(() -> Name.of("helloWorld"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이름의 길이는 5글자 이하여야 합니다.");
    }
}
