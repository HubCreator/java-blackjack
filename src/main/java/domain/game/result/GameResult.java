package domain.game.result;

import domain.participant.Player;

public enum GameResult {

    WIN {
        @Override
        public int calculateProfit(final Player player) {
            if (player.isBlackjack()) {
                return (int) (PROFIT_RATE * player.bet());
            }
            return player.bet();
        }
    },
    DRAW {
        @Override
        public int calculateProfit(final Player player) {
            if (player.isBusted()) {
                return -player.bet();
            }
            return 0;
        }
    },
    LOSE {
        @Override
        public int calculateProfit(final Player player) {
            return -player.bet();
        }
    };

    public static final double PROFIT_RATE = 1.5;

    public abstract int calculateProfit(final Player player);
}