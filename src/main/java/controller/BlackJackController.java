package controller;

import domain.game.BlackjackGame;
import domain.deck.ShuffledDeck;
import domain.participant.Player;
import domain.participant.Players;
import domain.participant.Name;
import view.InputView;
import view.OutputView;

import java.util.List;
import java.util.stream.Collectors;

import static template.Repeater.repeat;

public final class BlackJackController {

    private final InputView inputView;
    private final OutputView outputView;

    public BlackJackController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    private BlackjackGame createBlackJack() {
        final List<Name> playerNames = repeat(this::userNameRequest);
        final List<Integer> bets = playerNames.stream()
                .map(name -> repeat(() -> inputView.betRequest(name)))
                .collect(Collectors.toList());
        return BlackjackGame.getInstance(playerNames, bets, new ShuffledDeck());
    }

    public void process() {
        final BlackjackGame blackjackGame = repeat(this::createBlackJack);

        outputView.printInitialStatus(blackjackGame.getDealer(), blackjackGame.getPlayers());
        getPlayersDecision(blackjackGame);
        getDealerResult(blackjackGame);
        outputView.printStatus(blackjackGame.getDealer(), blackjackGame.getPlayers());
        outputView.printFinalResult(blackjackGame.getDealer(), blackjackGame.getGameResult());
    }

    private List<Name> userNameRequest() {
        return inputView.userNameRequest()
                .stream()
                .map(Name::of)
                .collect(Collectors.toList());
    }

    private void getPlayersDecision(final BlackjackGame blackjackGame) {
        final Players participants = blackjackGame.getPlayers();
        for (Player player : participants.getPlayers()) {
            getPlayerDecision(blackjackGame, player);
        }
    }

    private void getPlayerDecision(final BlackjackGame blackjackGame, final Player player) {
        while (player.isNotFinished() && wantMoreCard(player)) {
            blackjackGame.giveCard(player);
            outputView.printPlayerCards(player);
        }
    }

    private boolean wantMoreCard(final Player player) {
        return repeat(() -> inputView.cardRequest(player.getName()));
    }

    private void getDealerResult(final BlackjackGame blackjackGame) {
        final int cardCount = blackjackGame.getAdditionalCardCount();
        final boolean haveAdditionalCard = cardCount > 0;
        outputView.printAdditionalCardCount(cardCount, haveAdditionalCard);
    }
}
