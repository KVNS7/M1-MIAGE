package Cartes;

public class Card {

    private final Suit cSuit;
    private final Rank cRank;

    /**
     * Constructeur pour une carte.
     * 
     * @param suit la couleur de la carte (ne peut pas être
     *              null).
     * @param rank le rang de la carte (ne peut pas être nu
     *              ll).
     * @pre suit != null && rank != null
     */

    public Card(Suit suit, Rank rank) {
        assert suit != null : "La suite ne peut être nulle";
        assert rank != null : "Le rang ne peut être nul";
        
        if (rank == Rank.JOKER && suit != Suit.JOKER) {
            throw new IllegalArgumentException("Un JOKER doit avoir la suit JOKER.");
        }

        this.cSuit = suit;
        this.cRank = rank;
    }

    public Suit getSuit() {
        return Suit.values()[cSuit.ordinal()];
    }

    public Rank getRank() {
        return Rank.values()[cRank.ordinal()];
    }

    public Color getColor(){
        return this.cSuit.color();
    }

}
