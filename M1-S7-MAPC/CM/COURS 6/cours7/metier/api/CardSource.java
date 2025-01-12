package org.example.cards.cours7.metier.api;

/**
 * A source of cards, from which cards can be drawn.
 */
public interface CardSource extends Iterable<ICard> {

    /**
     * Accept a visitor to visit this card source.
     * @param <T> the type of value in the visitor.
     * @param v the visitor to visit this card source.
     * @pre v != null
     */
    <T> void accept(VisiteurCardSource<T> v);

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
