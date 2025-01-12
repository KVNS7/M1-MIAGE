package org.example.cards.cours4.metier.api;

import org.example.cards.cours4.metier.api.CardSource;

/**
 * A card source that can be shuffled.
 */
public interface ShufflableCardSource extends CardSource {

    /**
     * Shuffles the cards.
     */
    void shuffle();
}
