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
        requestDraw(blackjack);
    }

    private Names requestNames() {
        return Names.from(inputView.requestNames());
    }

    private Bets requestBets(final Names names) {
        final List<Bet> betList = new ArrayList<>();
        for (Name name : names.getNames()) {
            final double value = inputView.requestBet(name.getName());
            betList.add(Bet.of(value));
        }
        return Bets.from(betList);
    }

    private void printInitialStatus(final Blackjack blackjack) {
        outputView.printInitialStatus(blackjack.getInitialStatus());
    }

    private void requestDraw(final Blackjack blackjack) {
        for (Player player : blackjack.getPlayers()) {
            while (player.isHit() && inputView.requestDraw(player.name())) {
                blackjack.letPlayerDraw(player);
                outputView.printPlayerStatus(player.name(), player.getHand());
            }
        }
    }
}
