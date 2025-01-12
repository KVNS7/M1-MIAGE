package code.visiteurs.v1.visiteurs;

import code.visiteurs.v1.structure.FichierSimple;
import code.visiteurs.v1.structure.Lien;
import code.visiteurs.v1.structure.Repertoire;

public class VisiteurCompteFeuille implements VisiteurFichiers<Integer> {

    int n = 0;

    @Override
    public void visit(FichierSimple f) {
        n++;
    }

    @Override
    public void visit(Lien l) {
        n++;
    }

    @Override
    public void visit(Repertoire r) {
        n++;
    }

    @Override
    public Integer resultat() {
        return n;
    }
}
