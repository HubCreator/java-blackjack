package domain.participant;

import domain.card.Card;
import domain.card.CardNumber;
import domain.card.CardShape;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PlayerTest {

    private List<Card> initialData;
    private Player player;

    @BeforeEach
    void init() {
        initialData = List.of(Card.of(CardShape.HEART, CardNumber.of(1)),
                Card.of(CardShape.HEART, CardNumber.of(2)));
        player = Player.create(Name.of("HK"), initialData, 10_000);
    }

    @DisplayName("플레이어는 '딜러'라는 이름을 가지면 예외가 발생한다.")
    @Test
    void invalidNameTest() {
        assertThatThrownBy(() -> Player.of(Name.of("딜러"), 10_000))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("'딜러'라는 이름을 가질 수 없습니다.");
    }

    @DisplayName("플레이어는 자신이 가진 카드의 점수 합을 구할 수 있다.")
    @Test
    void calculateGamePoint() {
        for (int i = 0; i < 12; i++) {
            player.takeCard(Card.of(CardShape.HEART, CardNumber.of(1)));
        }
        assertThat(player.getGamePoint())
                .extracting("gamePoint")
                .isSameAs(15);
    }
}
