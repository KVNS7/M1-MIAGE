package code.visiteurs.v2.visiteurs;

import code.visiteurs.v2.structure.Repertoire;

// noter ici le visiteur absrait factorisant des choses (DRY)
// on pourrait en avoir différents pour différents parcours à utiliser pour différents visiteurs concrets.
public abstract class VisiteurAbstrait<T> implements VisiteurFichiers<T> {
    @Override
    public void visit(Repertoire r) {
        r.forEach(f -> f.accept(this));
    }

}
