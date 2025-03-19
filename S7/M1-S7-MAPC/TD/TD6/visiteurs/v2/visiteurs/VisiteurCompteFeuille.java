package code.visiteurs.v2.visiteurs;

import code.visiteurs.v2.structure.FichierSimple;
import code.visiteurs.v2.structure.Lien;
import code.visiteurs.v2.structure.Repertoire;

public class VisiteurCompteFeuille extends VisiteurAbstrait<Integer> {

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
        super.visit(r);
    }

    @Override
    public Integer resultat() {
        return n;
    }
}
