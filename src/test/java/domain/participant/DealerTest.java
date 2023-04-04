package domain.participant;

import domain.card.Card;
import domain.card.Number;
import domain.card.Suit;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
class DealerTest {

    private Dealer dealer = Dealer.create();

    @Test
    void 딜러를_생성한다() {
        assertDoesNotThrow(Dealer::create);
    }

    @Test
    void 딜러는_카드를_받을_수_있다() {
        dealer.take(
                Card.of(Suit.SPADE, Number.ACE),
                Card.of(Suit.SPADE, Number.TWO),
                Card.of(Suit.SPADE, Number.THREE)
        );

        assertThat(dealer.score()).isEqualTo(16);
    }

    @Test
    void 딜러의_초기_카드의_합이_16_이하라면_한_장을_더_받을_수_있다() {
        dealer.take(
                Card.of(Suit.SPADE, Number.ACE),
                Card.of(Suit.SPADE, Number.TWO)
        );

        assertDoesNotThrow(() -> dealer.take(Card.of(Suit.CLOVER, Number.THREE)));
    }

    @Test
    void 딜러의_초기_카드의_합이_17_이상인데_한_장을_더_받으면_예외가_발생한다() {
        dealer.take(
                Card.of(Suit.SPADE, Number.ACE),
                Card.of(Suit.SPADE, Number.SIX)
        );

        assertThatThrownBy(() -> dealer.take(Card.of(Suit.CLOVER, Number.THREE)))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("점수가 17점 이상이면 더 이상 카드를 뽑을 수 없습니다.");
    }

    @Test
    void 버스트_상태인데_카드를_뽑으면_예외가_발생한다() {
        dealer.take(
                Card.of(Suit.HEART, Number.JACK),
                Card.of(Suit.HEART, Number.JACK),
                Card.of(Suit.HEART, Number.JACK)
        );
        assertThatThrownBy(() -> dealer.take(Card.of(Suit.CLOVER, Number.EIGHT)))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("버스트 상태에서는 더이상 카드를 뽑을 수 없습니다.");
    }

    @Test
    void 블랙잭_상태인데_카드를_뽑으면_예외가_발생한다() {
        dealer.take(
                Card.of(Suit.HEART, Number.ACE),
                Card.of(Suit.HEART, Number.JACK)
        );
        assertThatThrownBy(() -> dealer.take(Card.of(Suit.CLOVER, Number.EIGHT)))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("블랙잭 상태에서는 더이상 카드를 뽑을 수 없습니다.");
    }
}
