package org.example.cards.cours7.metier.impl.visiteurs;

import org.example.cards.cours7.metier.api.ICard;
import org.example.cards.cours7.metier.api.VisiteurCardSource;
import org.example.cards.cours7.metier.impl.structure.CardSequence;
import org.example.cards.cours7.metier.impl.structure.CompositeCardSource;
import org.example.cards.cours7.metier.impl.structure.Deck;

public class AfficheurDeCartes implements VisiteurCardSource<Void> {
    @Override public void visit(Deck d) {
        for (ICard c: d) {
           System.out.println(c); 
        }
    }    
    @Override public void visit(CardSequence cs) {
        for (ICard c: cs) {
            System.out.println(c); 
         } 
    }
    @Override public void visit(CompositeCardSource ccs) {
        // RIEN A FAIRE
    }
    @Override
    public Void getValeur() {
        return null;
    }
}
