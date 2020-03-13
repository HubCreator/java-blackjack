package domain.result;

import java.util.Arrays;
import java.util.function.BiPredicate;

import domain.user.User;

public enum MatchResult {
	WIN("승", MatchResult::isPlayerWin),
	DRAW("무", MatchResult::isPlayerDraw),
	LOSE("패", MatchResult::isPlayerLose);

	private static final String ILLEGAL_RESULT_MESSAGE = "예측 불가능한 결과입니다.";

	private final String matchResult;
	private final BiPredicate<User, User> resultCondition;

	MatchResult(String matchResult, BiPredicate<User, User> resultCondition) {
		this.matchResult = matchResult;
		this.resultCondition = resultCondition;
	}

	public static MatchResult calculatePlayerMatchResult(User player, User dealer) {
		return Arrays.stream(values())
			.filter(result -> result.resultCondition.test(player, dealer))
			.findFirst()
			.orElseThrow(() -> new IllegalArgumentException(ILLEGAL_RESULT_MESSAGE));
	}

	public String getMatchResult() {
		return matchResult;
	}

	public MatchResult reverseWinAndLose() {
		if (this == MatchResult.WIN) {
			return MatchResult.LOSE;
		}
		if (this == MatchResult.LOSE) {
			return MatchResult.WIN;
		}
		return this;
	}

	private static boolean isPlayerWin(User player, User dealer) {
		return (player.isBlackjack() && !dealer.isBlackjack()) ||
			(!player.isBust() &&
				((player.calculateScore() > dealer.calculateScore()) || dealer.isBust())
			);
	}

	private static boolean isPlayerDraw(User player, User dealer) {
		return dealer.isBlackjack() && player.isBlackjack();
	}

	private static boolean isPlayerLose(User player, User dealer) {
		return (!player.isBlackjack() && dealer.isBlackjack()) ||
			(player.isBust()) || (player.calculateScore() <= dealer.calculateScore());
	}
}
