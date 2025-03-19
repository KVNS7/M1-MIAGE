package code.visiteurs.v2.structure;

import code.visiteurs.v2.visiteurs.VisiteurFichiers;

public interface Fichier {
    String proprietaire();
    String nom();
    void accept(VisiteurFichiers v);
}
