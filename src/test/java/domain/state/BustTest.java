package domain.state;

import domain.card.Card;
import domain.card.CardNumber;
import domain.card.CardShape;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BustTest {

    private State state;

    @BeforeEach
    void init() {
        final State ready = new Ready().draw(Card.of(CardShape.CLOVER, CardNumber.of(10)));
        assertThat(ready).isInstanceOf(Ready.class);

        state = ready.draw(Card.of(CardShape.HEART, CardNumber.of(10)));
        assertThat(state).isInstanceOf(Hit.class);
    }

    @Test
    void bustTest() {
        final State newState = state.draw(Card.of(CardShape.HEART, CardNumber.of(10)));
        assertThat(newState).isInstanceOf(Bust.class);
    }

    /*
    @Test
    void bustStayTest() {
        assertThatThrownBy(() -> state.stay())
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("게임이 끝났습니다.");
    }

    @Test
    void bustDrawTest() {
        assertThatThrownBy(() -> state.draw(Card.of(CardShape.CLOVER, CardNumber.of(5))))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("카드를 추가로 받을 수 없습니다.");
    }*/
}
