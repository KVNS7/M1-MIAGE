package code.visiteurs.v1.visiteurs;

import java.util.List;

import code.visiteurs.v1.structure.FichierSimple;
import code.visiteurs.v1.structure.Lien;
import code.visiteurs.v1.structure.Repertoire;

import java.util.ArrayList;

public class VisiteurCompteType implements VisiteurFichiers<List<String>> {

    List<String> res = new ArrayList<>();
    String suffixe;

    public VisiteurCompteType(String suffixe) {
        this.suffixe = suffixe;
    }

    @Override
    public void visit(FichierSimple f) {
        if (f.nom().endsWith(suffixe)) {
            res.add(f.nom());
        }
    }

    @Override
    public void visit(Lien l) {
    }

    @Override
    public void visit(Repertoire r) {
    }

    @Override
    public List<String> resultat() {
        return res;
    }
}
