package domain.participant;

import domain.card.Card;
import domain.card.Deck;
import domain.game.Hand;
import domain.game.Score;

import java.util.Collections;
import java.util.List;

public final class Dealer {

    public static final int FIRST_CARD_INDEX = 0;

    private final Name name;
    private Hand hand;

    private Dealer(final Name name) {
        this.name = name;
        this.hand = Hand.of(Collections.emptyList());
    }

    public static Dealer create() {
        return new Dealer(Name.of("딜러"));
    }

    private void validateHit() {
        if (isBusted()) {
            throw new IllegalStateException("버스트 상태에서는 더이상 카드를 뽑을 수 없습니다.");
        }
        if (isBlackjack()) {
            throw new IllegalStateException("블랙잭 상태에서는 더이상 카드를 뽑을 수 없습니다.");
        }
        if (isOverDealerStandard()) {
            throw new IllegalStateException("점수가 17점 이상이면 더 이상 카드를 뽑을 수 없습니다.");
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

    public int score() {
        return hand.getScore().getScore();
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

    public Hand getInitialHand() {
        Card card = hand.getCards().get(FIRST_CARD_INDEX);
        return Hand.of(List.of(card));
    }
}
