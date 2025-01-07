import Cartes.*;

public class Main {
    public static void main(String[] args) {
        Card c1 = new Card(Suit.CARREAUX, Rank.AS);
        Card c2 = new Card(Suit.PIQUES, Rank.SEPT);

        System.out.println("Première carte = " + c1.getRank() + " de " + c1.getSuit() + ", couleur : " + c1.getColor());
        System.out.println("Deuxième carte = " + c2.getRank() + " de " + c2.getSuit() + ", couleur : " + c2.getColor());


    }
}
