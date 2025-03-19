package Exo3.Generalisation1;

import java.util.HashSet;
import java.util.Set;

public class Feuille<T> implements Arbre<T> {
    private final T valeur;

    public Feuille(T valeur) {
        this.valeur = valeur;
    }

    @Override
    public int taille() {
        return 1;
    }

    @Override
    public boolean contient(final T val) {
        return valeur.equals(val);
    }

    @Override
    public Set<T> valeurs() {
        Set<T> valeursSet = new HashSet<>();
        valeursSet.add(valeur);
        return valeursSet;
    }

    public T getValeur() {
        return valeur;
    }
}
