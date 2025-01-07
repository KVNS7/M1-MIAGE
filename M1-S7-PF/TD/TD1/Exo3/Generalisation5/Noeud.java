package Exo3.Generalisation5;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Noeud<T extends Sommable<T>> implements Arbre<T> {
    private final List<Arbre<T>> enfants;

    public Noeud(List<Arbre<T>> enfants) {
        this.enfants = enfants;
    }

    @Override
    public int taille() {
        int taille = 0;
        for (Arbre<T> enfant : enfants) {
            taille += enfant.taille();
        }
        return taille;
    }

    @Override
    public boolean contient(final T val) {
        for (Arbre<T> enfant : enfants) {
            if (enfant.contient(val)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Set<T> valeurs() {
        Set<T> valeursSet = new HashSet<>();
        for (Arbre<T> enfant : enfants) {
            valeursSet.addAll(enfant.valeurs());
        }
        return valeursSet;
    }

    public List<Arbre<T>> getEnfants() {
        return enfants;
    }

    @Override
    public T somme() {
        T sommeTotale = null;

        for (Arbre<T> enfant : enfants) {
            T valeurEnfant = enfant.somme();
            if (sommeTotale == null) {
                sommeTotale = valeurEnfant;
            } else {
                sommeTotale = sommeTotale.sommer(valeurEnfant);
            }
        }
        return sommeTotale;
    }

    @Override
    public T min() {
        T min = null;
        for (Arbre<T> enfant : enfants) {
            T minEnfant = enfant.min();
            if (min == null || minEnfant.compareTo(min) < 0) {
                min = minEnfant;
            }
        }
        return min;
    }

    @Override
    public T max() {
        T max = null;
        for (Arbre<T> enfant : enfants) {
            T maxEnfant = enfant.max();
            if (max == null || maxEnfant.compareTo(max) > 0) {
                max = maxEnfant;
            }
        }
        return max;
    }

    @Override
    public boolean estTrie() {
        for (int i = 0; i < (enfants.size()) - 1; i++) {
            Arbre<T> enfant = enfants.get(i);
            Arbre<T> suivant = enfants.get(i + 1);

            if (!enfant.estTrie() || !suivant.estTrie()) {
                return false;
            }

            if (enfant.max().compareTo(suivant.min()) > 0) {
                return false;
            }
        }
        return true;
    }

}
