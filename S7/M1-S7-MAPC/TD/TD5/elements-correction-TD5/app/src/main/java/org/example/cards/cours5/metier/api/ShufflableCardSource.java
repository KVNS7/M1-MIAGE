package org.example.cards.cours5.metier.api;

/**
 * A card source that can be shuffled.
 */
public interface ShufflableCardSource extends CardSource {

    /**
     * Shuffles the cards.
     */
    void shuffle();
}
