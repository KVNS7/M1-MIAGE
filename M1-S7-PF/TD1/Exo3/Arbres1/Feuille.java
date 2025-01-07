package Exo3.Arbres1;

import java.util.Collections;
import java.util.Set;

public class Feuille implements Arbre{
    private final Integer valeur;

    public Feuille(Integer valeur){this.valeur = valeur;}

    public Integer getValeur() {return valeur;}

    @Override
    public int taille() {return 1;}

    @Override
    public boolean contient(final Integer val) {return valeur.equals(val);}

    @Override
    public Set<Integer> valeurs() {return Collections.singleton(valeur);}
    
    @Override
    public Integer somme() {return valeur;}

    @Override
    public Integer min() {return valeur;}

    @Override
    public Integer max() {return valeur;}

    @Override
    public boolean estTrie() {return true;}
}
