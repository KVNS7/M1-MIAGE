package Exo3.Arbres1;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Noeud implements Arbre{
    private List<Arbre> lien;

    public Noeud (List<Arbre> lien){
        this.lien = lien;
    }

    @Override
    public int taille(){
        int taille=0;
        for (Arbre arbre : lien) {
            taille += arbre.taille();
        }    
        return taille;
    }

    @Override
    public boolean contient(final Integer val){
        for (Arbre arbre : lien) {
            if(arbre.contient(val)) return true;
        }
        return false;
    }

    @Override
    public Set<Integer> valeurs(){
        Set<Integer> val = new HashSet<>();
        for (Arbre arbre : lien) {
            val.addAll(arbre.valeurs());
        }
        return val;
    }

    @Override
    public Integer somme() {
        int somme = 0;
        boolean nonNull = false;
        for (Arbre arbre : lien) {
            Integer val = arbre.somme();
            if (val != null) {
                somme += val;
                nonNull = true;
            }
        }
        return nonNull ? somme : null;
    }

    @Override
    public Integer min(){
        Integer min = null;

        for (Arbre arbre : lien) {
            Integer arbreMax = arbre.min();
            if (arbreMax != null && (min == null || arbreMax < min)) min = arbreMax;
        }
        return min;
    }

    @Override
    public Integer max(){
        Integer max = null;

        for (Arbre arbre : lien) {
            Integer arbreMax = arbre.max();
            if (arbreMax != null && (max == null || arbreMax > max)) max = arbreMax;
        }
        return max;
    }

    public boolean estTrie(){
        Integer lastMax = null;
        for (Arbre arbre : lien) {
            if (!arbre.estTrie()) {
                return false;
            }
            Integer arbreMin = arbre.min();
            if (lastMax != null && arbreMin != null && arbreMin < lastMax) {
                return false;
            }
            lastMax = arbre.max();
        }
        return true;
    }

}
