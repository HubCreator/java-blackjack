package domain.game.result;

import domain.participant.Player;

public enum GameResult {

    WIN {
        @Override
        public int calculateProfit(final Player player) {
            if (player.isBlackjack()) {
                return (int) (BLACKJACK_PROFIT_RATE * player.getBetValue());
            }
            return player.getBetValue();
        }
    },
    DRAW {
        @Override
        public int calculateProfit(final Player player) {
            if (player.isBusted()) {
                return -player.getBetValue();
            }
            return 0;
        }
    },
    LOSE {
        @Override
        public int calculateProfit(final Player player) {
            return -player.getBetValue();
        }
    };

    public static final double BLACKJACK_PROFIT_RATE = 1.5;

    public abstract int calculateProfit(final Player player);
}
