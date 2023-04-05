package view;

import domain.card.Card;
import domain.card.Number;
import domain.card.Suit;

public final class CardTranslator {

    private CardTranslator() {
    }

    public static String render(final Card card) {
        return renderSuit(card.getSuit()) + renderNumber(card.getNumber());
    }

    private static String renderSuit(final Suit suit) {
        if (suit == Suit.HEART) {
            return "하트";
        }
        if (suit == Suit.DIAMOND) {
            return "다이아몬드";
        }
        if (suit == Suit.SPADE) {
            return "스페이드";
        }
        if (suit == Suit.CLOVER) {
            return "클로버";
        }
        throw new UnsupportedOperationException();
    }

    private static String renderNumber(final Number number) {
        if (number == Number.ACE) {
            return "A";
        }
        if (number == Number.JACK) {
            return "J";
        }
        if (number == Number.QUEEN) {
            return "Q";
        }
        if (number == Number.KING) {
            return "K";
        }
        return String.valueOf(number.getValue());
    }
}
