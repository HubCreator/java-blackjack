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
    public final double calculateProfit(final double bet) {
        throw new IllegalStateException("게임 중간에 수익률을 계산할 수 없습니다.");
    }
}
