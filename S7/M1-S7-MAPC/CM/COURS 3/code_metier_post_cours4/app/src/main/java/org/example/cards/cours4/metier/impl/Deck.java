package org.example.cards.cours4.metier.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import org.example.cards.cours4.metier.api.ICard;
import org.example.cards.cours4.metier.api.ShufflableCardSource;
import org.example.cards.cours4.metier.api.Sortable;

/**
 * A {@link Sortable} {@link ShufflableCardSource} implementation that contains all possible {@link ICard}s.
 */
public class Deck implements ShufflableCardSource, Sortable<ICard> {

    private List<ICard> cards = new ArrayList<>();

    /**
     * Creates a deck of cards (one for each rank x suit).
     * The deck is not shuffled at creation time.
     * Package private to avoid instantiation without factory.
     */
    Deck() {
        for (ICard.Suit suit : ICard.Suit.values()) {
            for (ICard.Rank rank : ICard.Rank.values()) {
                cards.add(new Card(rank, suit));
            }
        }
    }

    /**
     * Creates a deck of cards from another one.
     * Public, no problem wrt factory.
     * Performs a copy.
     * @param deck
     */
    public Deck(Deck deck) {
        this.cards = new ArrayList<>(deck.cards);
    }

    /**
     * Draws a card.
     * An alternative could be to return an Optional<Card> to lift precondition.
     * @return the drawn card
     * @pre !isEmpty()
     */
    @Override
    public ICard draw() {
        assert !isEmpty();
        return cards.remove(0);
    }

    /**
     * Checks if the deck is empty.
     * @return true if there are no cards left, false otherwise.
     */
    @Override
    public boolean isEmpty() {
        return cards.isEmpty();
    }

    /**
     * Shuffles the cards.
     */
    @Override
    public void shuffle() {
        Collections.shuffle(cards);
    }

    /**
     * Returns a card iterator.
     */
    @Override
    public Iterator<ICard> iterator() {
        return cards.iterator();
    }

    /**
     * Sorts the cards.
     * @param comparator the comparator to use
     */
    @Override
    public void sort(Comparator<ICard> comparator) {
        Collections.sort(cards, comparator);
    }

    // remarque :
    // on a supprimé Card cardAt(int index)

    // remarque :
    // on a supprimé List<Card> cards()

    // remarque :
    // on a simplifié toString pour respecter l'encapsulation.
    // (on affiche que ce qui peut être connu via l'API publique)

    /**
     * Returns a string representation of the deck.
     */
    @Override
    public String toString() {
        String type = isEmpty()? "An empty" : "A non empty";
        return type + " card source";
    }

}
