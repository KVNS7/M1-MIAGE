package code.visiteurs.v1.structure;

import java.util.Map;

import code.visiteurs.v1.visiteurs.VisiteurFichiers;

import java.util.HashMap;
import java.util.List;

public class Repertoire extends FichierAbstrait {

    private Map<String, Fichier> elements;

    public Repertoire(String nom, String proprietaire, List<Fichier> enfants) {
        super(nom, proprietaire);
        this.elements = new HashMap<>();
        for (Fichier f: enfants) {
            elements.put(f.nom(), f);
        }
    }

    @Override
    public void accept(VisiteurFichiers v) {
        v.visit(this);
        for(Fichier f : elements.values()) {
            f.accept(v);
        }
    }

    @Override 
    public String toString() {
        return String.format("Repertoire %s", nom());
    }

}
