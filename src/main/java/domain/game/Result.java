package domain.game;

import domain.card.Cards;
import domain.participant.Player;

public enum Result {
    WIN {
        @Override
        public int calculateProfit(final Player player) {
            if (player.isBlackJack()) {
                return (int) (PROFIT_RATE * player.getBet());
            }
            return player.getBet();
        }
    },
    DRAW {
        @Override
        public int calculateProfit(final Player player) {
            return 0;
        }
    },
    LOSE {
        @Override
        public int calculateProfit(final Player player) {
            return -player.getBet();
        }
    };

    public static final double PROFIT_RATE = 1.5;

    public abstract int calculateProfit(final Player player);
}
