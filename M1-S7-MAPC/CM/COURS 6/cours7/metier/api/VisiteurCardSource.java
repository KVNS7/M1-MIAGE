package org.example.cards.cours7.metier.api;

import org.example.cards.cours7.metier.impl.structure.CardSequence;
import org.example.cards.cours7.metier.impl.structure.CompositeCardSource;
import org.example.cards.cours7.metier.impl.structure.Deck;

public interface VisiteurCardSource<T> {
    T getValeur();
    void visit(Deck d);
    void visit(CardSequence cs);
    void visit(CompositeCardSource ccs);
}
