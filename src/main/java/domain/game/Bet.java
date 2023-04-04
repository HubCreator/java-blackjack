package domain.game;

public final class Bet {

    public static final int MIN_UNIT = 1000;
    
    private final double bet;

    public Bet(final double bet) {
        this.bet = bet;
    }

    public static Bet of(final double bet) {
        validateBetUnit(bet);
        return new Bet(bet);
    }

    private static void validateBetUnit(final double bet) {
        if (bet % 1000 != 0) {
            throw new IllegalArgumentException(
                    String.format("베팅 금액은 %d원 단위여야 합니다.", MIN_UNIT)
            );
        }

    }
}
