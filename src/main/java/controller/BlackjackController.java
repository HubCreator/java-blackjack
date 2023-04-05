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

public final class BlackjackController {

    private final InputView inputView;
    private final OutputView outputView;

    public BlackjackController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void process() {
        final Names names = requestNames();
        final Bets bets = requestBets(names);
        final Blackjack blackjack = Blackjack.create(names, bets);
        printInitialStatus(blackjack);
        requestDraws(blackjack);
        letDealerDraw(blackjack);
        printFinalStatus(blackjack);
        printProfit(blackjack);
    }

    private Names requestNames() {
        return Names.from(inputView.requestNames());
    }

    private Bets requestBets(final Names names) {
        final List<Bet> betList = new ArrayList<>();
        for (Name name : names.getNames()) {
            final int value = inputView.requestBet(name.getName());
            betList.add(Bet.of(value));
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
        while (player.isHit() && inputView.requestDraw(player.name())) {
            blackjack.letPlayerDraw(player);
            outputView.printPlayerStatus(player.name(), player.getHand());
        }
    }

    private void letDealerDraw(final Blackjack blackjack) {
        int dealerDrawCount = blackjack.finalizeDealerTurn();
        outputView.printDealerDrawCount(blackjack.getDealerName(), dealerDrawCount);
    }

    private void printFinalStatus(final Blackjack blackjack) {
        outputView.printFinalStatus(blackjack.getFinalStatus(), blackjack.getScoreStatus());
    }

    private void printProfit(final Blackjack blackjack) {
        outputView.printProfit(blackjack.getDealerName(), blackjack.getProfitStatus());
    }
}
