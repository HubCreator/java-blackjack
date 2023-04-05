package domain.participant;

import domain.card.Card;
import domain.card.Deck;
import domain.game.Bet;
import domain.game.Hand;
import domain.game.Score;

import java.util.Collections;
import java.util.List;

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

    private void validateHit() {
        if (isBusted()) {
            throw new IllegalStateException("버스트 상태에서는 더이상 카드를 뽑을 수 없습니다.");
        }
        if (isBlackjack()) {
            throw new IllegalStateException("블랙잭 상태에서는 더이상 카드를 뽑을 수 없습니다.");
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

    public boolean isBusted() {
        return hand.isBusted();
    }

    public boolean isBlackjack() {
        return hand.isBlackjack();
    }

    public boolean isHit() {
        return !isBusted() && !isBlackjack();
    }

    public boolean isLowerThan(final Score score) {
        return hand.isLowerThan(score);
    }

    public boolean isSameAs(final Score score) {
        return hand.isSameAs(score);
    }

    public boolean isGreaterThan(final Score score) {
        return hand.isGreaterThan(score);
    }

    public Name getName() {
        return name;
    }

    public String getNameValue() {
        return name.getName();
    }

    public Score getScore() {
        return hand.getScore();
    }

    public int getBetValue() {
        return bet.getBet();
    }

    public List<Card> getCards() {
        return List.copyOf(hand.getCards());
    }
}
