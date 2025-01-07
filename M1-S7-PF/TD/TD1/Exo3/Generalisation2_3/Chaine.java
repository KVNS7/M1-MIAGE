package Exo3.Generalisation2_3;

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
    public String toString() {
        return valeur;
    }
}
