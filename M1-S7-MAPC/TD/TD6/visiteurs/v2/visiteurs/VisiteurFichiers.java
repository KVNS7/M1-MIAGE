package code.visiteurs.v2.visiteurs;

import code.visiteurs.v2.structure.FichierSimple;
import code.visiteurs.v2.structure.Lien;
import code.visiteurs.v2.structure.Repertoire;

public interface VisiteurFichiers<T> {
    void visit(FichierSimple f);
    void visit(Lien l);
    void visit(Repertoire r);
    T resultat();
}
