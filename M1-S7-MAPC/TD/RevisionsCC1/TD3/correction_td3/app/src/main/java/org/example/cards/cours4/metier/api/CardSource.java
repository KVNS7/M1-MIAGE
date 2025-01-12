package org.example.cards.cours4.metier.api;
/**
 * A source of cards, from which cards can be drawn.
 */
public interface CardSource extends Iterable<ICard> {

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
}
