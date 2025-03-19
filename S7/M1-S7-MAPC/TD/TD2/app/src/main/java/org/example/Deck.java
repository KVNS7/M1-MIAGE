package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Deck {

    private List<Card> cards = new ArrayList<>();

    public Deck(){
        for (Card.Suit suit : Card.Suit.values()){
            for (Card.Rank rank : Card.Rank.values()){
                cards.add(new Card(suit,rank));
            }
        }
    }

    public Deck(Deck d){
        this.cards= new ArrayList<>(d.cards);
    }

    public Optional<Card> draw() {
        if(isEmpty()){
            return Optional.empty();
        }else{
            return Optional.of(cards.remove(0));
        }
    }

    public boolean isEmpty(){
        return size() == 0;
    }

    private int size() {
        return cards.size();
    }

    public Optional<Card> cardAt(int index) {
        assert index >= 0 && index < cards.size();

        if (index >=0 && index < cards.size()){
            return Optional.of(cards.get(index));
        }else{
            return Optional.empty();
        }
    };

    @Override
    public String toString(){
        // StringBuilder sb = new StringBuilder();
        // for(Card c : cards){
        //     sb.append(c).append("\n");
        // }
        // return sb.toString();

        return cards.stream()
            .map(Card::toString)
            .collect(Collectors.joining("\n"));
    }


}