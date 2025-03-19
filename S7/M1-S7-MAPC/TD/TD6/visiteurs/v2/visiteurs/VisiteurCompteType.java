package code.visiteurs.v2.visiteurs;

import java.util.List;

import code.visiteurs.v2.structure.Fichier;
import code.visiteurs.v2.structure.FichierSimple;
import code.visiteurs.v2.structure.Lien;
import code.visiteurs.v2.structure.Repertoire;

import java.util.ArrayList;

public class VisiteurCompteType extends VisiteurAbstrait<List<String>> {

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
        super.visit(r); // ou rien
    }

    @Override
    public List<String> resultat() {
        return res;
    }
}
