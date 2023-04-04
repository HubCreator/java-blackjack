package domain.game;

import domain.card.Card;
import domain.card.Number;
import domain.card.Suit;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
class ScoreTest {

    @Test
    void 카드의_합을_계산한다() {
        // given
        final List<Card> cards = List.of(
                Card.of(Suit.HEART, Number.EIGHT),
                Card.of(Suit.CLOVER, Number.TWO)
        );

        // when
        final Score score = Score.from(cards);

        // then
        assertThat(score.isSameAs(Score.of(10))).isTrue();
    }

    @Test
    void 버스트가_아니라면_ACE는_11로_취급한다() {
        // given
        final List<Card> cards = List.of(
                Card.of(Suit.HEART, Number.ACE),
                Card.of(Suit.CLOVER, Number.TWO)
        );

        // when
        final Score score = Score.from(cards);

        // then
        assertThat(score.isSameAs(Score.of(13))).isTrue();
    }
}
