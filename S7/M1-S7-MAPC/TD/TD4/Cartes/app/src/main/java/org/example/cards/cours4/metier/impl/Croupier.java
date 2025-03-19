package org.example.cards.cours4.metier.impl;

import org.example.cards.cours4.metier.api.ShufflableCardSource;
import org.example.cards.cours4.metier.api.ShufflableCardSourceCreator;

/**
 * A basic implementation of a {@link ShufflableCardSourceCreator}
 */
public class Croupier implements ShufflableCardSourceCreator {

    @Override
    public ShufflableCardSource create() {
        return new Deck();
    }
    
}
