package blackjack.domain;

import java.util.ArrayList;
import java.util.List;

public class Cards {

    private final List<Card> cards;
    private final GamePoint point;

    public Cards(final List<Card> cards) {
        this.cards = new ArrayList<>(cards);
        this.point = new GamePoint(cards);
    }

    public Cards add(final Card card) {
        List<Card> cardList = new ArrayList<>(cards);
        cardList.add(card);
        return new Cards(cardList);
    }

    public boolean isBust() {
        return point.isBusted();
    }
}
