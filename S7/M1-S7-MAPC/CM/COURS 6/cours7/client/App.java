package org.example.cards.cours7.client;

import java.util.List;

import org.example.cards.cours7.metier.api.*;
import org.example.cards.cours7.metier.impl.*;
import org.example.cards.cours7.metier.impl.structure.CompositeCardSource;
import org.example.cards.cours7.metier.impl.structure.Deck;
import org.example.cards.cours7.metier.impl.visiteurs.AfficheurDeCartes;
import org.example.cards.cours7.metier.impl.visiteurs.CompteurDeCartes;

public class App {

    public static void main(String[] args) {
        CardSource d1 = new Deck();
        CardSource d2 = new Deck();
        CardSource ccs = new CompositeCardSource(List.of(d1,d2));
        VisiteurCardSource<Void> v1 = new AfficheurDeCartes();
        ccs.accept(v1);
        VisiteurCardSource<Integer> v2 = new CompteurDeCartes();
        ccs.accept(v2);
        System.out.println(v2.getValeur());
    }
}
