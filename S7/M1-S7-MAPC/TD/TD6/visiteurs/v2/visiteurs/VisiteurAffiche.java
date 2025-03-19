package code.visiteurs.v2.visiteurs;

import code.visiteurs.v2.structure.FichierSimple;
import code.visiteurs.v2.structure.Lien;
import code.visiteurs.v2.structure.Repertoire;

public class VisiteurAffiche extends VisiteurAbstrait<Void> {

    @Override
    public void visit(FichierSimple f) {
        System.out.println(f);
    }

    @Override
    public void visit(Lien l) {
        System.out.println(l);
    }

    @Override
    public void visit(Repertoire r) {
        System.out.println(r);
        super.visit(r);
    }

    @Override
    public Void resultat() {
        return null;
    }
}
