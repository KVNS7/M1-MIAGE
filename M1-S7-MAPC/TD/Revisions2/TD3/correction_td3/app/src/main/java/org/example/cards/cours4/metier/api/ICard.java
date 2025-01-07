package org.example.cards.cours4.metier.api;

import java.util.Comparator;

/**
 * Anything with a suit and a rank, and that can be compared on this basis.
 */
public interface ICard extends Comparable<ICard> {
    public enum Rank {
        ACE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING
    }

    public enum Suit {
        CLUBS, DIAMONDS, SPADES, HEARTS
    }

    /**
     * Suit of a card.
     * @return the suit of the card.
     */
    public Suit suit();

    /**
     * Rank of a card.
     * @return the rank of the card.
     */
    public Rank rank();

    /**
     * Returns a comparator based on the rank of cards.
     */
    public static Comparator<ICard> rankComparator() {
        return (c1, c2) -> c1.rank().compareTo(c2.rank());
    }

    /**
     * Returns a comparator based on the suit of cards.
     */
    public static Comparator<ICard> suitComparator() {
        return (c1, c2) -> c1.suit().compareTo(c2.suit());
    }

    /**
     * Returns a comparator based on the rank of cards and then the suit of cards.
     */
    public static Comparator<ICard> rankAndThenSuitComparator() {
        return (c1, c2) -> {
            int res = rankComparator().compare(c1, c2);
            if (res == 0)
                res = suitComparator().compare(c1, c2);
            return res;
        };
    }
    
}
