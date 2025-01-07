package org.example.tds.td3;

import java.util.ArrayList;
import java.util.List;

import org.example.cards.cours4.metier.api.ShufflableCardSource;
import org.example.cards.cours4.metier.api.ShufflableCardSourceCreator;
import org.example.cards.cours4.metier.api.ICard;
import org.example.cards.cours4.metier.impl.Croupier;

import java.util.Iterator;

public class Hand implements IHand {

    private List<ICard> cards; // Liste des cartes
    private int maxCards; // Nombre maximal de cartes en main

    public Hand(int maxCards) {
        assert maxCards > 0;
        this.maxCards = maxCards;
        this.cards = new ArrayList<>();
    }

    public Hand(IHand hand) {
        this.maxCards = hand.maxSize();
        this.cards = new ArrayList<>(cards);
    }

    // TODO: penser à equals et hashCode en lien avec compareTo !

    @Override
    public int compareTo(IHand o) {
        return this.size() - o.size();
    }

    @Override
    public Iterator<ICard> iterator() {
        // version "simple" (utilisation d'un itérateur à disposition, ici via cards)
        // return cards.iterator();

        // version plus poussée (utilisation d'un itérateur après un peu de travail "maison")
        List<ICard> copy = new ArrayList<>(cards);
        copy.sort(ICard.rankComparator());
        return copy.iterator();

        // version "sur mesure" (à compléter)
        // return new Iterator<Card>() {
        //     @Override
        //     public boolean hasNext() {
        //         // TODO: 
        //     }
        //     @Override
        //     public Card next() {
        //         // TODO:
        //     }
            
        // };
    }

    @Override 
    public int maxSize() {
        return maxCards;
    }

    // on pourrait se poser la question d'autoriser ou pas à ajouter une carte présente
    @Override
    public void add(ICard c) {
        assert c != null;
        assert !isFull();
        cards.add(c);
    }

    // on pourrait se poser la question de retourner un booléen
    @Override
    public void remove(ICard c) {
        assert c!= null;
        assert cards.contains(c);
        cards.remove(c);
    }

    // penser à equals+hashCode dans les implémentations de ICard utilisées !
    @Override
    public boolean contains(ICard c) {
        assert c!= null;
        return cards.contains(c);
    }

    @Override
    public boolean isEmpty() {
        return cards.isEmpty();
    }

    @Override
    public boolean isFull() {
        return maxCards == cards.size();
    }

    @Override
    public int size() {
        return cards.size();
    }

    // mêmes commentaires que pour Deck
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (ICard c : cards) {
            sb.append(c + ", ");
        }
        return sb.toString();
    }

    // dans la main, noter qu'on a réorganisé les interfaces
    // pour permettre d'avoir des sources de cartes "shufflables"
    // (et on est en accord dans la factory)

    public static void main(String[] args) {
        IHand hand1 = new Hand(5);
        IHand hand2 = new Hand(5);
        ShufflableCardSourceCreator croupier = new Croupier();
        ShufflableCardSource deck = croupier.create();
        // deck.shuffle(); // on ne mélange pas sinon on ne maitrise pas les cartes pour "tester"
        hand1.add(deck.draw());
        hand2.add(deck.draw());
        hand2.add(deck.draw());
        System.out.println(hand1); // [ ACE of CLUBS ]
        System.out.println(hand2); // [ TWO of CLUBS, THREE of CLUBS ]
        System.out.println(hand1.compareTo(hand2)); // -1 (moins de cartes)
        System.out.println(hand2.compareTo(hand1)); // +1 (plus de cartes)
        System.out.println(IHand.ascendingComparator.compare(hand1, hand2)); // -1
        System.out.println(IHand.descendingComparator.compare(hand1, hand2)); // +1
        System.out.println(IHand.byRank(ICard.Rank.ACE).compare(hand1, hand2)); // +1 (1 ACE vs 0 ACE)
    }
    
}
