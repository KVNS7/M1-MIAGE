package code.visiteurs.v1.structure;

import code.visiteurs.v1.visiteurs.VisiteurFichiers;

public class Lien extends FichierAbstrait {

    private Fichier cible;

    public Lien(String nom, String proprietaire, Fichier cible) {
        super(nom, proprietaire);
        this.cible = cible;
    }

    @Override
    public void accept(VisiteurFichiers v) {
        v.visit(this);
        // cible.accept(v); // il faudrait choisir dans visiteur
    }

    @Override 
    public String toString() {
        return String.format("Lien %s", nom());
    }

}
