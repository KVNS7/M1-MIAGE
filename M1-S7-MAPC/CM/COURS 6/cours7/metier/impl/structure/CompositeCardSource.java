package org.example.cards.cours7.metier.impl.structure;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.example.cards.cours7.metier.api.CardSource;
import org.example.cards.cours7.metier.api.ICard;
import org.example.cards.cours7.metier.api.VisiteurCardSource;

public class CompositeCardSource implements CardSource {

    private List<CardSource> sources;

    public CompositeCardSource(List<CardSource> sources) {
        this.sources = new ArrayList<>(sources);
    }

    @Override
    public Iterator<ICard> iterator() {
        return null; // TODO:
    }

    @Override
    public ICard draw() {
        assert !isEmpty();
        Optional<CardSource> source = sources.stream().filter(s -> !s.isEmpty()).findFirst();
        if (source.isPresent()) {
            return source.get().draw();
        } else {
            return null; // impossible
        }
    }

    @Override
    public boolean isEmpty() {
        return sources.stream().allMatch(CardSource::isEmpty);
    }

    @Override
    public <T> void accept(VisiteurCardSource<T> v) {
        assert v != null;
        v.visit(this);
        // itération enfants : dans composites ou dans visiteur
        sources.forEach(s -> s.accept(v));
    }

}
