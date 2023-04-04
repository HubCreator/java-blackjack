package domain.card;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
class DeckTest {

    // given
    final Deck deck = Deck.create();

    @Test
    void 덱에는_모두_52장의_서로_다른_카드가_들어있다() {

        // when
        final Set<Card> result = new HashSet<>();
        for (int i = 0; i < 52; i++) {
            result.add(deck.draw());
        }

        // then
        assertThat(result).size().isEqualTo(52);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 10, 20, 52})
    void 덱에서_카드를_뽑으면_뽑은_만큼ㅣㄷㅁㄱ_줄어든다(int count) {

        // when
        for (int i = 0; i < count; i++) {
            deck.draw();
        }

        // then
        assertThat(deck.leftCardCount()).isEqualTo(52 - count);
    }
}
