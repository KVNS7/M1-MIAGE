package org.example.tds.td3;

import static org.example.cards.cours4.metier.api.ICard.Rank.*;
import static org.example.cards.cours4.metier.api.ICard.Suit.*;
import org.example.cards.cours4.metier.impl.Card;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class HandTest {

    /**
     * - la taille initiale est de 0
     * - on ne peut pas ajouter au delà de la taille maximale
     * - ajouter une carte change la taille (+1) si on n'a pas atteint le maximum
     */

    @Test
    public void mainInitialementVide() {
        IHand emptyHand = new Hand(5);
        assertEquals(0, emptyHand.size());
        assertTrue(emptyHand.isEmpty());
    }

    @Test
    public void ajouterUneCarteChangeLaTaille() {
        final int MAX = 5;
        IHand hand = new Hand(MAX);
        for (int i = 0; i < MAX-2; i++) {
            hand.add(new Card(ACE, HEARTS));
        }
        int size = hand.size();
        hand.add(new Card(ACE, HEARTS));
        assertEquals(size + 1, hand.size());
    }
    
    @Test
    public void ajouterMaxCartesAtteintLeMax() {
        final int MAX = 5;
        IHand hand = new Hand(MAX);
        for (int i = 0; i < MAX; i++) {
            hand.add(new Card(ACE, HEARTS));
        }
        assertEquals(MAX, hand.size());
        assertTrue(hand.isFull());
    }

    @Test(expected = AssertionError.class) // ici le test réussit si la bonne exception est levée !
    public void impossibleAjouterAuDelaDuMax() {
        final int MAX = 5;
        IHand hand = new Hand(MAX);
        for (int i = 0; i < MAX; i++) {
            hand.add(new Card(ACE, HEARTS));
        }
        hand.add(new Card(ACE, HEARTS));
    }

}
