package domain.game;

public final class Bet {

    public static final int MIN_UNIT = 1_000;

    private final int bet;

    public Bet(final int bet) {
        this.bet = bet;
    }

    public static Bet of(final int bet) {
        validateBetUnit(bet);
        validateBetRange(bet);
        return new Bet(bet);
    }

    private static void validateBetUnit(final int bet) {
        if (bet % MIN_UNIT != 0) {
            throw new IllegalArgumentException(
                    String.format("베팅 금액은 %d원 단위여야 합니다.", MIN_UNIT)
            );
        }
    }

    private static void validateBetRange(final int bet) {
        if (bet > 1_000_000_000) {
            throw new IllegalArgumentException("베팅 유효 범위를 초과하셨습니다.");
        }
    }

    public int getBet() {
        return bet;
    }
}
