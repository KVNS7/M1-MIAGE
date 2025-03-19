package org.example.cards.cours7.metier.impl.structure;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.example.cards.cours7.metier.api.CardSource;
import org.example.cards.cours7.metier.api.ICard;
import org.example.cards.cours7.metier.api.VisiteurCardSource;

public class CardSequence implements CardSource {

    private List<ICard> cards;

    public CardSequence(List<ICard> cards) {
        assert cards != null;
        this.cards = new ArrayList<>(cards);
    }

    @Override
    public Iterator<ICard> iterator() {
        return cards.iterator();
    }

    @Override
    public ICard draw() {
        assert !isEmpty();
        return cards.remove(0);
    }

    @Override
    public boolean isEmpty() {
        return cards.isEmpty();
    }

    @Override
    public <T> void accept(VisiteurCardSource<T> v) {
        assert v != null;
        v.visit(this);
    }
    
}
