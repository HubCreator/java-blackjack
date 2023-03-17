package domain.state;

import domain.card.Card;
import domain.card.CardNumber;
import domain.card.CardShape;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class StayTest {

    State state;

    @BeforeEach
    void init() {
        state = new Ready()
                .draw(Card.of(CardShape.HEART, CardNumber.of(1)))
                .draw(Card.of(CardShape.HEART, CardNumber.of(2)));
        state = state.stay();
    }

    @Test
    void stayTest() {
        assertThat(state).isInstanceOf(Stay.class);
    }

    @Test
    void stayStayTest() {
        assertThatThrownBy(() -> state.stay())
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("게임이 끝났습니다.");
    }

    @Test
    void stayDrawTest() {
        assertThatThrownBy(() -> state.draw(Card.of(CardShape.CLOVER, CardNumber.of(5))))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("카드를 추가로 받을 수 없습니다.");
    }

}
