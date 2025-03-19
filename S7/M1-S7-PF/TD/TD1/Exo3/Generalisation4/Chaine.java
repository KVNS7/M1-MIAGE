package Exo3.Generalisation4;

import java.util.Objects;

public class Chaine implements Sommable<Chaine> {
    private final String valeur;

    public Chaine(String valeur) {
        this.valeur = valeur;
    }

    @Override
    public Chaine sommer(Chaine autre) {
        return new Chaine(this.valeur + autre.valeur);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Chaine chaine = (Chaine) obj;
        return Objects.equals(valeur, chaine.valeur);
    }

    @Override
    public int hashCode() {
        return Objects.hash(valeur);
    }

    @Override
    public String toString() {
        return valeur;
    }

}
