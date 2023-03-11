package domain.state;

import domain.card.Card;
import domain.card.CardNumber;
import domain.card.CardShape;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class HitTest {

    State state;

    @BeforeEach
    void init() {
        state = new Ready()
                .draw(Card.of(CardShape.HEART, CardNumber.of(1)))
                .draw(Card.of(CardShape.HEART, CardNumber.of(2)));
    }

    @Test
    void hitDrawTest() {
        final State newState = state.draw(Card.of(CardShape.HEART, CardNumber.of(10)));
        assertThat(newState).isInstanceOf(Hit.class);
    }

    @Test
    void hitAndCardsTest() {
        final State newState = state.draw(Card.of(CardShape.HEART, CardNumber.of(10)));
        assertThat(newState.cards()).containsExactly(
                Card.of(CardShape.HEART, CardNumber.of(1)),
                Card.of(CardShape.HEART, CardNumber.of(2)),
                Card.of(CardShape.HEART, CardNumber.of(10))
        );
    }

    @Test
    void stayTest() {
        final State newState = state.draw(Card.of(CardShape.HEART, CardNumber.of(10)));
        assertThat(newState.stay()).isInstanceOf(Stay.class);
    }

    @Test
    void bustTest() {
        final State newState = state.draw(Card.of(CardShape.HEART, CardNumber.of(10)))
                .draw(Card.of(CardShape.HEART, CardNumber.of(10)));
        assertThat(newState).isInstanceOf(Bust.class);
    }
}
