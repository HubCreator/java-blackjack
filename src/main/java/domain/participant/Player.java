package domain.participant;

import domain.card.Card;
import domain.card.Deck;
import domain.game.Bet;
import domain.game.Hand;

import java.util.Collections;

public final class Player {

    private final Name name;
    private final Bet bet;
    private Hand hand;

    private Player(final Name name, final Bet bet) {
        this.name = name;
        this.bet = bet;
        this.hand = Hand.of(Collections.emptyList());
    }

    public static Player of(final Name name, final Bet bet) {
        return new Player(name, bet);
    }

    public void take(final Card card) {
        if (isBusted()) {
            throw new IllegalStateException("버스트 상태에서는 더이상 카드를 뽑을 수 없습니다.");
        }
        if (isBlackjack()) {
            throw new IllegalStateException("블랙잭 상태에서는 더이상 카드를 뽑을 수 없습니다.");
        }
        hand = hand.take(card);
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

    public boolean isHit() {
        return !isBusted() && !isBlackjack();
    }

    public double score() {
        return hand.getScore();
    }

    public Name getName() {
        return name;
    }

    public String name() {
        return name.getName();
    }

    public Hand getHand() {
        return hand;
    }
}
