package domain.card;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
class DeckTest {

    @Test
    void 덱에는_모두_52장의_서로_다른_카드가_들어있다() {
        // given
        final Deck deck = Deck.create();

        // when
        final Set<Card> result = new HashSet<>();
        for (int i = 0; i < 52; i++) {
            result.add(deck.draw());
        }

        // then
        assertThat(result).size().isEqualTo(52);
    }
}
