package org.example.cards.cours7.metier.impl.visiteurs;

import org.example.cards.cours7.metier.api.ICard;
import org.example.cards.cours7.metier.api.VisiteurCardSource;
import org.example.cards.cours7.metier.impl.structure.CardSequence;
import org.example.cards.cours7.metier.impl.structure.CompositeCardSource;
import org.example.cards.cours7.metier.impl.structure.Deck;

public class CompteurDeCartes implements VisiteurCardSource<Integer> {

    private int n;

    public CompteurDeCartes() {
        n = 0;
    }

    @Override
    public Integer getValeur() {
        return n;
    }

    @Override public void visit(Deck d) {
        for (ICard c : d) {
            n++;
        }
    }    
    @Override public void visit(CardSequence cs) {
        for (ICard c : cs) {
            n++;
        }
    }
    @Override public void visit(CompositeCardSource ccs) {
        // RIEN A FAIRE
    }
}
