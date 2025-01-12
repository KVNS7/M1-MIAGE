package code.visiteurs.v2.structure;

import code.visiteurs.v2.visiteurs.VisiteurFichiers;

public class FichierSimple extends FichierAbstrait {

    public FichierSimple(String nom, String proprietaire) {
        super(nom, proprietaire);
    }

    @Override
    public void accept(VisiteurFichiers v) {
        v.visit(this);
    }

    @Override 
    public String toString() {
        return String.format("Fichier Simple %s", nom());
    }

}
