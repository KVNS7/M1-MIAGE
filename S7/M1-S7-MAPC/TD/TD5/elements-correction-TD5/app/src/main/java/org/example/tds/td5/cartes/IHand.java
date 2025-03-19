package org.example.tds.td5.cartes;

import java.util.Comparator;
import java.util.stream.StreamSupport;

import org.example.cards.cours4.metier.api.ICard;

public interface IHand extends Iterable<ICard>, Comparable<IHand>, Cloneable {

    /**
     * Cloning for hands.
     */
    public IHand clone();

    /**
     * Returns the maximum number of cards in a IHand.
     * @return the maximum number of cards in a IHand.
     */
    int maxSize();

    /**
     * Add a card to the hand
     * @pre c != null && !isFull()
     */
    void add(ICard c);

    /**
     * Remove a card from the hand
     * @pre c != null && contains(c)
     */
    void remove(ICard c);

    /** 
     * Checks if the hand contains a card
     * @pre c != null
     */
    boolean contains(ICard c);

    /**
     * Checks if the hand is empty
     */
    boolean isEmpty();

    /**
     * Checks if the hand is full
     */
    boolean isFull();

    /**
     * Get the number of cards in the hand
     */
    int size();

    /**
     * (Returns a) comparator to compare two hands by size (ascending).
     * On a utilisé une variable lambda, on aurait aussi pu faire une méthode statique qui la retourne.
     */
    Comparator<IHand> ascendingComparator = (hand1, hand2) -> hand1.size() - hand2.size();

    /**
     * (Returns a) comparator to compare two hands by size (descending).
     * On a utilisé une variable lambda, on aurait aussi pu faire une méthode statique qui la retourne.
     */
    Comparator<IHand> descendingComparator = (hand1, hand2) -> hand2.size() - hand1.size();

    /**
     * Returns a comparator to compare two hankds given some rank of interest.
     * Ici on fait une version méthode statique.
     */
    public static Comparator<IHand> byRank(ICard.Rank rank) {
        return new Comparator<IHand>() {

            @Override
            public int compare(IHand hand1, IHand hand2) {
                return countCards(hand1, rank) - countCards(hand2, rank);
            }

            private int countCards(IHand hand, ICard.Rank rank) {
                // ici un peu subtil, on cherche à créer un stream à partir d'un iterateur
                // vous pouvez faire plus simple avec une boucle ...
                Iterable<ICard> cardsInHand = () -> hand.iterator();
                return (int)StreamSupport.stream(cardsInHand.spliterator(), false)
                            .filter(c -> c.rank() == rank)
                            .count();
                // sans les Stream
                //
                // version 1
                //
                // int count = 0;
                // Iterator<ICard> iterator = hand.iterator();
                // while (iterator.hasNext()) {
                //     ICard card = iterator.next();
                //     if (card.rank() == rank) {
                //         count++;
                //     }
                // }
                // return count;
                //
                // version 2
                //
                // int count = 0;
                // for (ICard card : hand) {
                //     if (card.rank() == rank) {
                //         count++;
                //     }
                // return count;
            }
        };
    }
}
