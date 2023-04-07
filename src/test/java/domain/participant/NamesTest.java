package domain.participant;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
class NamesTest {

    @Test
    void 쉼표를_기준으로_이름을_분리한다() {
        // given
        final String input = "hello,world,hi,hey";
        final Names names = Names.from(input);

        // when
        final List<Name> nameList = names.getNames();

        // then
        assertThat(nameList).containsExactly(
                Name.from("hello"),
                Name.from("world"),
                Name.from("hi"),
                Name.from("hey")
        );
    }

    @Test
    void 딜러라는_이름이_들어가면_예외가_발생한다() {
        // given
        final String input = "hello,world,hi,딜러";

        // expect
        assertThatThrownBy(() -> Names.from(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("참여자는 딜러라는 이름을 사용할 수 없습니다.");
    }
}
