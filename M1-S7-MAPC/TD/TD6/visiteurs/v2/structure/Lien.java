package code.visiteurs.v2.structure;

import code.visiteurs.v2.visiteurs.VisiteurFichiers;

public class Lien extends FichierAbstrait {

    private Fichier cible;

    public Lien(String nom, String proprietaire, Fichier cible) {
        super(nom, proprietaire);
        this.cible = cible;
    }

    @Override
    public void accept(VisiteurFichiers v) {
        v.visit(this);
    }

    @Override 
    public String toString() {
        return String.format("Lien %s", nom());
    }

}
