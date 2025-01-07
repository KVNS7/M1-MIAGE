package org.example;

public class Card {
    public enum Suit{Hearts, Spades, Diamonds, Clubs}
    public enum Rank{ACE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING}

    private Suit suit;
    private Rank rank;

    public Card(Suit suit, Rank rank){
        assert suit != null;
        assert rank != null;
        this.suit = suit;
        this.rank = rank;
    }

    public Suit getSuit(){return suit;}

    public Rank getRank(){return rank;}
}
