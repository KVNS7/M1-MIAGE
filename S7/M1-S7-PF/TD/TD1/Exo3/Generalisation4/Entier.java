package Exo3.Generalisation4;

import java.util.Objects;

public class Entier implements Sommable<Entier>{
    private final int valeur;

    public Entier(int valeur) {
        this.valeur = valeur;
    }

    @Override
    public Entier sommer(Entier autre) {
        return new Entier(this.valeur + autre.valeur);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Entier entier = (Entier) obj;
        return valeur == entier.valeur;
    }

    @Override
    public int hashCode() {
        return Objects.hash(valeur);
    }

    @Override
    public String toString() {
        return String.valueOf(valeur);
    }

}
