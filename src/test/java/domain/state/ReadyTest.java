package domain.state;

import domain.card.Card;
import domain.card.CardNumber;
import domain.card.CardShape;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ReadyTest {

    State state;

    @BeforeEach
    void init() {
        state = new Ready()
                .draw(Card.of(CardShape.HEART, CardNumber.of(1)))
                .draw(Card.of(CardShape.HEART, CardNumber.of(2)));
    }

    @DisplayName("카드를 드로우하면 카드가 저장된다.")
    @Test
    void initialCardsTest() {
        assertThat(state.cards()).containsExactly(
                Card.of(CardShape.HEART, CardNumber.of(1)),
                Card.of(CardShape.HEART, CardNumber.of(2))
        );
    }

    @DisplayName("초기값이 Hit 상태이다.")
    @Test
    void stateTest() {
        assertThat(state).isInstanceOf(Hit.class);
    }


}
