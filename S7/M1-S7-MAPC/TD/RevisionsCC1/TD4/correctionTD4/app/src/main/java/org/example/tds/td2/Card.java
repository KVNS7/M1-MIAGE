package org.example.tds.td2;

/**
 * Le traitement du joker est aisé dans ces deux cas :
 * - Card = Suit x Rank et un joker par couleur (on ajoute JOKER dans Rank)
 * - Card = CardEnum et un unique joken (on ajoute JOKER dans CardEnum)
 * C'est la seconde solution qui est implantée ici. Vous pouvez essayer de faire la première.
 * 
 */
public class Card {

    public enum Rank {
        ACE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING
    }

    public enum Suit {
        CLUBS, DIAMONDS, SPADES, HEARTS;

        public Colour colour() {
            return switch (this) {
                case CLUBS, SPADES -> Colour.BLACK;
                default -> Colour.RED;
            };
        }
    }

    public enum Colour {
        RED, BLACK
    }

    public enum CardEnum {
        // 0
        ACE_OF_CLUBS, TWO_OF_CLUBS, THREE_OF_CLUBS, FOUR_OF_CLUBS, FIVE_OF_CLUBS,
        SIX_OF_CLUBS, SEVEN_OF_CLUBS, EIGHT_OF_CLUBS, NINE_OF_CLUBS, TEN_OF_CLUBS,
        JACK_OF_CLUBS, QUEEN_OF_CLUBS, KING_OF_CLUBS,
        // 1
        ACE_OF_DIAMONDS, TWO_OF_DIAMONDS, THREE_OF_DIAMONDS, FOUR_OF_DIAMONDS, FIVE_OF_DIAMONDS,
        SIX_OF_DIAMONDS, SEVEN_OF_DIAMONDS, EIGHT_OF_DIAMONDS, NINE_OF_DIAMONDS, TEN_OF_DIAMONDS,
        JACK_OF_DIAMONDS, QUEEN_OF_DIAMONDS, KING_OF_DIAMONDS,
        // 2
        ACE_OF_SPADES, TWO_OF_SPADES, THREE_OF_SPADES, FOUR_OF_SPADES, FIVE_OF_SPADES,
        SIX_OF_SPADES, SEVEN_OF_SPADES, EIGHT_OF_SPADES, NINE_OF_SPADES, TEN_OF_SPADES,
        JACK_OF_SPADES, QUEEN_OF_SPADES, KING_OF_SPADES,
        // 3
        ACE_OF_HEARTS, TWO_OF_HEARTS, THREE_OF_HEARTS, FOUR_OF_HEARTS, FIVE_OF_HEARTS,
        SIX_OF_HEARTS, SEVEN_OF_HEARTS, EIGHT_OF_HEARTS, NINE_OF_HEARTS, TEN_OF_HEARTS,
        JACK_OF_HEARTS, QUEEN_OF_HEARTS, KING_OF_HEARTS,
        // none
        JOKER;
    }

    private CardEnum cardEnum;

    /**
     * Creates a new card.
     * 
     * @param cardEnum the enumerated type for the card.
     * @pre cardEnum != null
     */
    public Card(CardEnum cardEnum) {
        assert cardEnum != null;
        this.cardEnum = cardEnum;
    }

    /**
     * Checks if the card is a Joker.
     * 
     * @return true if it is a Joker, false otherwise.
     */
    public boolean isJoker() {
        return cardEnum == CardEnum.JOKER;
    }

    /**
     * Colour of a card.
     * 
     * @return the colour of the card.
     * @pre the card is not a Joker.
     */
    public Colour colour() {
        assert !isJoker();
        return this.suit().colour();
    }

    /**
     * Suit of a card.
     * 
     * @return the suit of the card.
     * @pre the card is not a Joker.
     */
    public Suit suit() {
        assert !isJoker();
        return Suit.values()[(this.cardEnum.ordinal() / Rank.values().length)];
    }

    /**
     * Rank of a card.
     * 
     * @return the rank of the card.
     * @pre the card is not a Joker.
     */
    public Rank rank() {
        assert !isJoker();
        return Rank.values()[this.cardEnum.ordinal() % Rank.values().length];
    }

    /**
     * Returns a string representation of this card.
     */
    @Override
    public String toString() {
        return rank() + " of " + suit();
    }
}
