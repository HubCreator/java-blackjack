package domain.result;

import domain.game.GamePoint;
import domain.state.State;

public enum GameResult {
    WIN {
        @Override
        public double getProfit(final State state, final double bet) {
            if (state.isBlackjack()) {
                return PROFIT_RATE * bet;
            }
            return bet;
        }
    },
    DRAW {
        @Override
        public double getProfit(final State state, final double bet) {
            if (state.isBust()) {
                return -bet;
            }
            return 0;
        }
    },
    LOSE {
        @Override
        public double getProfit(final State state, final double bet) {
            return -bet;
        }
    };

    public static GameResult calculate(final State playerState, final State dealerState) {
        final GamePoint playerPoint = playerState.getGamePoint();
        final GamePoint dealerPoint = dealerState.getGamePoint();
        if (playerState.isStay() && dealerState.isStay()) {
            return getGameResult(playerPoint, dealerPoint);
        }
        if (playerState.isBust()) {
            return LOSE;
        }
        if (dealerState.isBust()) {
            return WIN;
        }
        if (playerState.isBlackjack() && dealerState.isBlackjack()) {
            return DRAW;
        }
        throw new AssertionError();
    }

    private static GameResult getGameResult(final GamePoint playerPoint, final GamePoint dealerPoint) {
        if (playerPoint.isGreaterThan(dealerPoint)) {
            return WIN;
        }
        if (playerPoint.isSameAs(dealerPoint)) {
            return DRAW;
        }
        if (playerPoint.isLowerThan(dealerPoint)) {
            return LOSE;
        }
        throw new AssertionError();
    }

    public static final double PROFIT_RATE = 1.5;

    public abstract double getProfit(final State state, final double bet);
}
