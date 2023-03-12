package domain.participant;

import domain.card.Card;
import domain.card.CardNumber;
import domain.card.CardShape;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DealerTest {

    private Dealer dealer;

    @BeforeEach
    void init() {
        dealer = Dealer.create();
    }

    @DisplayName("딜러의 이름은 '딜러'다.")
    @Test
    void dealerName() {
        assertThat(dealer.getName().getValue()).isEqualTo("딜러");
    }

    @DisplayName("딜러는 자신이 가진 카드의 점수 합을 구할 수 있다.")
    @Test
    void calculateGamePoint() {
        for (int i = 0; i < 12; i++) {
            dealer.takeCard(Card.of(CardShape.HEART, CardNumber.of(1)));
        }
        assertThat(dealer.getGamePoint())
                .extracting("gamePoint")
                .isSameAs(12);
    }

}
