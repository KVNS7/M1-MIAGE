package org.example.tds.td2;

import java.util.List;
import java.util.ArrayList;

import org.example.cards.cours3.Card;
import org.example.cards.cours3.Deck;

public class MultiDeck {

    private List<Deck> decks;

    public MultiDeck(int nbDecks) {
        assert nbDecks > 0;
        decks = new ArrayList<>(nbDecks);
        for (int i = 0; i < nbDecks; i++) {
            decks.add(new Deck());
        }
    }

    public MultiDeck(MultiDeck multiDeck) {
        // PAS BIEN ! (copie pas assez profonde)
        // this.decks = new ArrayList<>(multiDeck.decks);
        // MIEUX
        this.decks = new ArrayList<>();
        for (Deck deck : multiDeck.decks) {
            this.decks.add(new Deck(deck));
         }
    }

    public Card draw() {
        assert size() > 0;
        // on sait qu'il existe un deck non vide donc OK de faire get sur un Optional<Deck>.
        // ATTENTION : on n'est pas sensés modifier n'importe comment des données (ici draw) dans un Stream.
        return decks.stream().filter(d -> d.size() > 0).findFirst().get().draw();
    }

    public int size() {
        return decks.stream().mapToInt(Deck::size).sum();
    }

    public List<Card> cards() {
        return decks.stream().flatMap(d -> d.cards().stream()).toList();
    }

    // on a revu le toString pour éviter de donner de l'information sur l'interne.
    @Override
    public String toString() {
        return "I am a MultiDeck with " + size() + " cards";
    }
}
