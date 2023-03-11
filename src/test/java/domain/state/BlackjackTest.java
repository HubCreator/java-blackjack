package domain.state;

import domain.card.Card;
import domain.card.CardNumber;
import domain.card.CardShape;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

class BlackjackTest {

    private State state;

    @BeforeEach
    void init() {
        state = new Ready()
                .draw(Card.of(CardShape.HEART, CardNumber.of(1)))
                .draw(Card.of(CardShape.HEART, CardNumber.of(10)));
    }

    @Test
    void blackjackTest() {
        assertThat(state).isInstanceOf(Blackjack.class);
    }

    @Test
    void blackjackStayTest() {
        assertThatThrownBy(() -> state.stay())
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("게임이 끝났습니다.");
    }

    @Test
    void blackjackDrawTest() {
        assertThatThrownBy(() -> state.draw(Card.of(CardShape.CLOVER, CardNumber.of(5))))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("카드를 추가로 받을 수 없습니다.");
    }
}
