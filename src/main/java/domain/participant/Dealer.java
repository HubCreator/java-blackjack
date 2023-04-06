package domain.participant;

import domain.card.Card;
import domain.card.Deck;
import domain.game.Hand;
import domain.game.Score;

import java.util.Collections;

public final class Dealer {

    private static final int FIRST_CARD_INDEX = 0;
    public static final String DEALER_NAME = "딜러";

    private final Name name;
    private Hand hand;

    private Dealer(final Name name) {
        this.name = name;
        this.hand = Hand.from(Collections.emptyList());
    }

    public static Dealer create() {
        return new Dealer(Name.of(DEALER_NAME));
    }

    private void validateHit() {
        if (isBusted()) {
            throw new IllegalStateException("버스트 상태에서는 더이상 카드를 뽑을 수 없습니다.");
        }
        if (isBlackjack()) {
            throw new IllegalStateException("블랙잭 상태에서는 더이상 카드를 뽑을 수 없습니다.");
        }
        if (isOverDealerStandard()) {
            throw new IllegalStateException(
                    String.format("점수가 %d점 초과면 더 이상 카드를 뽑을 수 없습니다.", Hand.DEALER_STANDARD_NUMBER)
            );
        }
    }

    public void take(final Card card) {
        validateHit();
        hand = hand.take(card);
    }

    public void take(final Card... cards) {
        for (Card card : cards) {
            validateHit();
            hand = hand.take(card);
        }
    }

    public void take(final Deck deck, final int count) {
        for (int i = 0; i < count; i++) {
            validateHit();
            hand = hand.take(deck.draw());
        }
    }

    private boolean isOverDealerStandard() {
        return hand.isOverDealerStandard();
    }

    private boolean isBusted() {
        return hand.isBusted();
    }

    private boolean isBlackjack() {
        return hand.isBlackjack();
    }

    public int finalizeTurn(final Deck deck) {
        int count = 0;
        if (!isOverDealerStandard()) {
            hand = hand.take(deck.draw());
            count += 1;
        }
        return count;
    }

    public Score getScore() {
        return hand.getScore();
    }

    public Name getName() {
        return name;
    }

    public Hand getHand() {
        return hand;
    }

    public Card getInitialCard() {
        return hand.getCards().get(FIRST_CARD_INDEX);
    }
}
