package code.visiteurs.v1.visiteurs;

import code.visiteurs.v1.structure.FichierSimple;
import code.visiteurs.v1.structure.Lien;
import code.visiteurs.v1.structure.Repertoire;

public interface VisiteurFichiers<T> {
    void visit(FichierSimple f);
    void visit(Lien l);
    void visit(Repertoire r);
    T resultat();
}
