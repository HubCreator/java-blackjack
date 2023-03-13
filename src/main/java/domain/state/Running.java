package domain.state;

import domain.game.Hand;

public abstract class Running extends State {

    protected Running(final Hand hand) {
        super(hand);
    }

    @Override
    public final State stay() {
        return new Stay(hand);
    }

    @Override
    public double calculateProfit(final double bet, final State dealerState) {
        throw new IllegalStateException("게임 중간에 수익률을 계산할 수 없습니다.");
    }

    @Override
    public boolean isStay() {
        return false;
    }

    @Override
    public boolean isBlackjack() {
        return false;
    }

}
