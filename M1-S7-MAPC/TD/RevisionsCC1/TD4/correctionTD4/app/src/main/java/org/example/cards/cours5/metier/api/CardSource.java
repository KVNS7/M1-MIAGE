package org.example.cards.cours5.metier.api;

import java.util.Iterator;
import java.util.List;

/**
 * A source of cards, from which cards can be drawn.
 */
public interface CardSource extends Iterable<ICard> {

    public static CardSource NULL = new CardSource() {
        @Override public ICard draw(){ assert !isEmpty(); return null; }
        @Override public boolean isEmpty() { return true; }
        @Override public boolean isNull() { return true; }
        @Override public Iterator<ICard> iterator() { return List.<ICard>of().iterator(); }
    };

    /**
     * Draws a card.
     * An alternative could be to return an Optional<ICard> to lift precondition.
     * @return the drawn card
     * @pre !isEmpty()
     */
    ICard draw();

    /**
     * Checks if the card source is empty.
     * @return true if there are no cards left, false otherwise.
     */
    boolean isEmpty();

    /**
     * Checks if this card source is null.
     * @return true if the card source is null, false otherwise
     */
    default boolean isNull() { return false; }
}
