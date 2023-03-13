package domain.state;

import domain.card.Card;
import domain.game.Hand;

public final class Hit extends Running {

    Hit(final Hand hand) {
        super(hand);
    }

    @Override
    public State draw(final Card card) {
        this.hand = hand.add(card);
        if (isBust()) {
            return new Bust(hand);
        }
        return new Hit(hand);
    }

    @Override
    public double calculateProfit(final double bet, final State dealerState) {
        throw new IllegalStateException("게임 중간에 수익률을 계산할 수 없습니다.");
    }

    @Override
    public boolean isBust() {
        return hand.getGamePoint().isSameAs(BUST_POINT);
    }
}
