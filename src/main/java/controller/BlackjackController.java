package controller;

import domain.game.Bet;
import domain.game.Blackjack;
import domain.participant.Name;
import domain.participant.Names;
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
        final Names names = Names.from(inputView.nameRequest());
        final List<Bet> bets = calculateBets(names);
        final Blackjack blackjack = Blackjack.create(names, bets);

    }

    private List<Bet> calculateBets(final Names names) {
        final List<Bet> bets = new ArrayList<>();
        for (Name name : names.getNames()) {
            final double value = inputView.betRequest(name.getName());
            bets.add(Bet.of(value));
        }
        return bets;
    }
}
