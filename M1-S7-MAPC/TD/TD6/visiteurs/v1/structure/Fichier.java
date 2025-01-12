package code.visiteurs.v1.structure;

import code.visiteurs.v1.visiteurs.VisiteurFichiers;

public interface Fichier {
    String proprietaire();
    String nom();
    void accept(VisiteurFichiers v);
}
