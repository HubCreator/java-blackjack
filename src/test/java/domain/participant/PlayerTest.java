package domain.participant;

import domain.card.Card;
import domain.card.Number;
import domain.card.Suit;
import domain.game.Bet;
import domain.game.Score;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
class PlayerTest {

    private Player player = Player.of(Name.from("hi"), Bet.valueOf(1000));

    @Test
    void 플레이어를_생성한다() {
        assertDoesNotThrow(() -> Player.of(Name.from("hi"), Bet.valueOf(1000)));
    }

    @Test
    void 플레이어는_카드를_받을_수_있다() {
        player.take(
                Card.of(Suit.SPADE, Number.ACE),
                Card.of(Suit.SPADE, Number.TWO),
                Card.of(Suit.SPADE, Number.THREE)
        );
        assertThat(player.getScore()).isEqualTo(Score.valueOf(16));
    }

    @Test
    void 플레이어는_버스트라면_ACE를_1로_간주할_수_있다() {
        player.take(
                Card.of(Suit.SPADE, Number.ACE),
                Card.of(Suit.SPADE, Number.TWO),
                Card.of(Suit.SPADE, Number.THREE),
                Card.of(Suit.HEART, Number.JACK)
        );
        assertThat(player.getScore()).isEqualTo(Score.valueOf(16));
    }

    @Test
    void 버스트_상태인데_카드를_뽑으면_예외가_발생한다() {
        player.take(
                Card.of(Suit.HEART, Number.JACK),
                Card.of(Suit.HEART, Number.JACK),
                Card.of(Suit.HEART, Number.JACK)
        );
        assertThatThrownBy(() -> player.take(Card.of(Suit.CLOVER, Number.EIGHT)))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("버스트 상태에서는 더이상 카드를 뽑을 수 없습니다.");
    }

    @Test
    void 블랙잭_상태인데_카드를_뽑으면_예외가_발생한다() {
        player.take(
                Card.of(Suit.HEART, Number.ACE),
                Card.of(Suit.HEART, Number.JACK)
        );
        assertThatThrownBy(() -> player.take(Card.of(Suit.CLOVER, Number.EIGHT)))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("블랙잭 상태에서는 더이상 카드를 뽑을 수 없습니다.");
    }
}
