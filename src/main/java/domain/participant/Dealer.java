package domain.participant;

import domain.card.Card;
import domain.game.Hand;

import java.util.Collections;

public final class Dealer {

    private final Name name;
    private Hand hand;

    private Dealer(final Name name) {
        this.name = name;
        this.hand = Hand.of(Collections.emptyList());
    }

    public static Dealer create() {
        return new Dealer(Name.of("딜러"));
    }

    public void take(final Card card) {
        if (isBusted()) {
            throw new IllegalStateException("버스트 상태에서는 더이상 카드를 뽑을 수 없습니다.");
        }
        if (isBlackjack()) {
            throw new IllegalStateException("블랙잭 상태에서는 더이상 카드를 뽑을 수 없습니다.");
        }
        if (isOverDealerStandard()) {
            throw new IllegalStateException("점수가 17점 이상이면 더 이상 카드를 뽑을 수 없습니다.");
        }
        hand = hand.take(card);
    }

    private boolean isOverDealerStandard() {
        return hand.isOverDealerStandard();
    }

    public void take(final Card... cards) {
        for (Card card : cards) {
            hand = hand.take(card);
        }
    }

    private boolean isBusted() {
        return hand.isBusted();
    }

    private boolean isBlackjack() {
        return hand.isBlackjack();
    }

    public double score() {
        return hand.getScore();
    }

    public String name() {
        return name.getName();
    }
}
