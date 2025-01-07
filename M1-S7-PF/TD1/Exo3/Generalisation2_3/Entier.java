package Exo3.Generalisation2_3;

public class Entier implements Sommable<Entier> {
    private final int valeur;

    public Entier(int valeur) {
        this.valeur = valeur;
    }

    @Override
    public Entier sommer(Entier autre) {
        return new Entier(this.valeur + autre.valeur);
    }

    @Override
    public String toString() {
        return String.valueOf(valeur);
    }
}
