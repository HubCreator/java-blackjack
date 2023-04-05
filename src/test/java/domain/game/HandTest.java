package domain.game;

import domain.card.Card;
import domain.card.Number;
import domain.card.Suit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
class HandTest {

    private Hand hand;

    @BeforeEach
    void init() {
        // given
        final List<Card> cards = List.of(
                Card.of(Suit.HEART, Number.EIGHT),
                Card.of(Suit.CLOVER, Number.TWO)
        );
        hand = Hand.of(cards);
    }

    @Test
    void 카드를_한_장_받을_수_있다() {
        // when
        final Hand newHand = hand.take(Card.of(Suit.SPADE, Number.KING));

        // then
        assertThat(newHand.getCards()).containsExactly(
                Card.of(Suit.HEART, Number.EIGHT),
                Card.of(Suit.CLOVER, Number.TWO),
                Card.of(Suit.SPADE, Number.KING)
        );
    }

    @Test
    void 받은_한_장의_카드가_ACE이고_BUST가_아니라면_11로_간주한다() {
        // when
        final Hand newHand = hand.take(Card.of(Suit.SPADE, Number.ACE));

        // then
        assertThat(newHand.getScore()).isEqualTo(Score.of(21));
    }

    @Test
    void 받은_한_장의_카드가_ACE이고_11로_간주시_BUST가_된다면_1로_간주한다() {
        // when
        final Hand newHand1 = hand.take(Card.of(Suit.CLOVER, Number.FIVE));
        final Hand newHand2 = newHand1.take(Card.of(Suit.SPADE, Number.ACE));

        // then
        assertThat(newHand2.getScore()).isEqualTo(Score.of(16));
    }

    @Test
    void 받은_21장의_카드가_모두_ACE라면_모두_1로_간주한다() {
        // given
        final List<Card> cards = new ArrayList<>();
        for (int i = 0; i < 21; i++) {
            cards.add(Card.of(Suit.CLOVER, Number.ACE));
        }

        // when
        final Hand hand = Hand.of(cards);

        // then
        assertThat(hand.getScore()).isEqualTo(Score.of(21));
    }
}
