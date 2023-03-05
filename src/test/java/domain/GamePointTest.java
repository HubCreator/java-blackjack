package domain;

import domain.card.Card;
import domain.card.CardNumber;
import domain.card.CardShape;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class GamePointTest {

    @Test
    @DisplayName("카드 게임 포인트는 카드들을 기반으로 값을 계산한다.")
    void gamePointCalculateTest() {
        final List<Card> data = List.of(
                Card.create(CardShape.CLOVER, CardNumber.of(2)),
                Card.create(CardShape.DIAMOND, CardNumber.of(3)),
                Card.create(CardShape.HEART, CardNumber.of(4))
        );

        final GamePoint gamePoint = new GamePoint(data);
        assertThat(gamePoint.getPoint()).isEqualTo(9);
    }

    @Test
    @DisplayName("A는 1혹은 11로 계산할 수 있으면 해당 카드들의 최선 값을 반환한다.")
    void optimizeCalculateTest1() {
        final List<Card> data = List.of(
                Card.create(CardShape.CLOVER, CardNumber.of(1)),
                Card.create(CardShape.DIAMOND, CardNumber.of(2)),
                Card.create(CardShape.HEART, CardNumber.of(3))
        );

        final GamePoint gamePoint = new GamePoint(data);
        assertThat(gamePoint.getPoint()).isEqualTo(16);
    }

    @Test
    @DisplayName("카드 계산 값이 21이 넘었을 경우 burst처리 한다.")
    void optimizeCalculateTest2() {
        final List<Card> data = List.of(
                Card.create(CardShape.CLOVER, CardNumber.of(12)),
                Card.create(CardShape.DIAMOND, CardNumber.of(13)),
                Card.create(CardShape.HEART, CardNumber.of(2))
        );
        final GamePoint gamePoint = new GamePoint(data);
        assertThat(gamePoint.getPoint()).isEqualTo(0);
    }

    @Test
    @DisplayName("카드 계산 값이 21 초과이고, ACE값이 있다면 ACE 값은 1로 계산한다.")
    void optimizeCalculateTest3() {
        final List<Card> data = List.of(
                Card.create(CardShape.CLOVER, CardNumber.of(12)),
                Card.create(CardShape.DIAMOND, CardNumber.of(13)),
                Card.create(CardShape.HEART, CardNumber.of(1))
        );
        final GamePoint gamePoint = new GamePoint(data);
        assertThat(gamePoint.getPoint()).isEqualTo(21);
    }

    @Test
    @DisplayName("카드 계산 값이 21 초과이고, ACE값이 있다면 ACE 값은 1로 계산한다.")
    void optimizeCalculateTest4() {
        final List<Card> data = List.of(
                Card.create(CardShape.CLOVER, CardNumber.of(1)),
                Card.create(CardShape.DIAMOND, CardNumber.of(1)),
                Card.create(CardShape.HEART, CardNumber.of(1)),
                Card.create(CardShape.HEART, CardNumber.of(1)),
                Card.create(CardShape.HEART, CardNumber.of(1)),
                Card.create(CardShape.HEART, CardNumber.of(1))
        );
        final GamePoint gamePoint = new GamePoint(data);
        assertThat(gamePoint.getPoint()).isEqualTo(16);
    }
}
