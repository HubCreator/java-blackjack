package domain.state;

import domain.card.Card;
import domain.game.Hand;

public abstract class Finished extends State {

    protected Finished(final Hand hand) {
        super(hand);
    }

    @Override
    public final State draw(final Card card) {
        throw new IllegalStateException("카드를 추가로 받을 수 없습니다.");
    }

    @Override
    public final State stay() {
        throw new IllegalStateException("게임이 끝났습니다.");
    }

    @Override
    public double calculateProfit(final double bet) {
        return bet * getProfitRate();
    }

    protected abstract double getProfitRate();

}
