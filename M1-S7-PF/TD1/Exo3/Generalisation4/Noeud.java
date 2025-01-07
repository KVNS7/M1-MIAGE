package Exo3.Generalisation4;

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

}
