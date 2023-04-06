package controller;

import domain.game.Bet;
import domain.game.Bets;
import domain.game.Blackjack;
import domain.participant.Name;
import domain.participant.Names;
import domain.participant.Player;
import view.InputView;
import view.OutputView;

import java.util.ArrayList;
import java.util.List;

import static domain.util.Repeater.repeat;

public final class BlackjackController {

    private final InputView inputView;
    private final OutputView outputView;

    public BlackjackController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void process() {
        final Blackjack blackjack = initBlackjack();

        printInitialStatus(blackjack);
        requestDraws(blackjack);
        letDealerDraw(blackjack);
        printFinalStatus(blackjack);
        printProfit(blackjack);
    }

    private Blackjack initBlackjack() {
        final Names names = repeat(this::requestNames);
        final Bets bets = repeat(() -> requestBets(names));
        return Blackjack.of(names, bets);
    }

    private Names requestNames() {
        return repeat(() -> Names.from(inputView.requestNames()));
    }

    private Bets requestBets(final Names names) {
        final List<Bet> betList = new ArrayList<>();
        for (Name name : names.getNames()) {
            final int value = repeat(() -> inputView.requestBet(name.getName()));
            betList.add(Bet.valueOf(value));
        }
        return Bets.from(betList);
    }

    private void printInitialStatus(final Blackjack blackjack) {
        outputView.printInitialStatus(blackjack.getInitialStatus());
    }

    private void requestDraws(final Blackjack blackjack) {
        for (Player player : blackjack.getPlayers()) {
            requestDraw(blackjack, player);
        }
    }

    private void requestDraw(final Blackjack blackjack, final Player player) {
        while (player.isHit() && repeat(() -> inputView.requestDraw(player.getNameValue()))) {
            blackjack.letPlayerDraw(player);
            outputView.printPlayerStatus(player.getNameValue(), player.getCards());
        }
    }

    private void letDealerDraw(final Blackjack blackjack) {
        int dealerDrawCount = blackjack.finalizeTurnAndGetCardCount();
        outputView.printDealerDrawCount(blackjack.getDealerName(), dealerDrawCount);
    }

    private void printFinalStatus(final Blackjack blackjack) {
        outputView.printFinalStatus(blackjack.getFinalStatus(), blackjack.getScoreStatus());
    }

    private void printProfit(final Blackjack blackjack) {
        outputView.printProfit(blackjack.getDealerName(), blackjack.getProfitStatus());
    }
}
