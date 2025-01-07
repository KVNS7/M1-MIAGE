package Exo3.Generalisation4;

import java.util.Set;

public class Feuille<T extends Sommable<T>> implements Arbre<T> {
    private final T valeur;

    public Feuille(T valeur) {
        this.valeur = valeur;
    }

    @Override
    public int taille() {
        return 1;
    }

    @Override
    public boolean contient(T val) {
        return valeur.equals(val);
    }

    @Override
    public Set<T> valeurs() {
        return Set.of(valeur);
    }

    @Override
    public T somme() {
        return valeur;
    }
}

