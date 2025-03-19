package org.example.cards.cours4.metier.impl;

import org.example.cards.cours4.metier.api.ICard;

/**
 * A basic implementation of the {@link ICard} interface.
 */
public class Card implements ICard {
    private Rank rank;
    private Suit suit;

    /**
     * Creates a new card.
     * @param rank
     * @param suit
     * @pre rank != null && suit != null
     */
    public Card(Rank rank, Suit suit) {
        assert rank != null;
        assert suit != null;
        this.rank = rank;
        this.suit = suit;
    }

    /**
     * Suit of a card.
     * @return the suit of the card.
     */
    @Override
    public Suit suit() {
        return suit;
    }

    /**
     * Rank of a card.
     * @return the rank of the card.
     */
    @Override
    public Rank rank() {
        return rank;
    }

    /**
     * Returns a string representation of this card.
     */
    @Override
    public String toString() {
        return rank + " of " + suit;
    }

    // contraintes sur equals / hashCode / compareTo
    // (pourraient être vérifiées avec property based testing)

    // c === c : OK
    // c1 === c2 => c2 === c1 : OK
    // c1 === c2 /\ c2 === c3 => c1 === c3 : OK
    // c1 === c2 => #c1 = #c2 : OK
    // c1 < c2 <=> c2 > c1 : OK
    // c1 < c2 /\ c2 < c3 => c1 < c3 : OK
    // c1 === c2 => c1 ~ c2 : OK
    // c1 ~ c2 => c1 === c2 : KO -> pas obligatoire mais documenter

    /**
     * Compares two cards.
     * Default comparison (rank ordering based),
     * other ones are possible using methods.
     * Should be compatible with equals and hashCode.
     * NOTE : c1.equals(c2) implies c1.compareTo(c2) == 0, 
     * but c1.compareTo(c2) == 0 does not implies c1.equals(c2).
     */
    @Override
    public int compareTo(ICard o) {
        return ICard.rankComparator().compare(this, o);
    }

    /**
     * Returns a hash code for this card.
     * Should be compatible with equals and compareTo.
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((rank == null) ? 0 : rank.hashCode());
        result = prime * result + ((suit == null) ? 0 : suit.hashCode());
        return result;
    }

    /**
     * Checks whether two cards are equal.
     * Should be compatible with hashCode and compareTo.
     * NOTE : c1.equals(c2) implies c1.compareTo(c2) == 0, 
     * but c1.compareTo(c2) == 0 does not implies c1.equals(c2).
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Card other = (Card) obj;
        if (rank != other.rank)
            return false;
        if (suit != other.suit)
            return false;
        return true;
    }
}
