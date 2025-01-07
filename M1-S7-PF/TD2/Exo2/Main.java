package Exo2;

import java.util.function.Predicate;

public class Main {
    public static void main(String[] args) {
        Predicate<Integer> tropPetit = taille -> taille < 100;
        Predicate<Integer> tropGrand = taille -> taille > 200;
        Predicate<Integer> tailleIncorrecte = tropPetit.or(tropGrand);
        Predicate<Integer> tailleCorrecte = tailleIncorrecte.negate();
        Predicate<Double> tropLourd = poids -> poids > 150.0;
        Predicate<Double> poidsCorrect = tropLourd.negate();
        Predicate<Paire<Integer, Double>> accesAutorise = paire -> tailleCorrecte.test(paire.fst)
                && poidsCorrect.test(paire.snd);

    }
}
