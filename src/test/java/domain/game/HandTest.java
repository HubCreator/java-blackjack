package domain.game;

import domain.card.Card;
import domain.card.Number;
import domain.card.Suit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

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
    void name() {
        // when
        final Hand newHand = hand.take(Card.of(Suit.SPADE, Number.KING));

        // then
        assertThat(newHand.getCards()).containsExactly(
                Card.of(Suit.HEART, Number.EIGHT),
                Card.of(Suit.CLOVER, Number.TWO),
                Card.of(Suit.SPADE, Number.KING)
        );

    }
}
