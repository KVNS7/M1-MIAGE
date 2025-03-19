package code.visiteurs.v2.structure;

import java.util.Map;

import code.visiteurs.v2.visiteurs.VisiteurFichiers;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

// noter Iterable<Fichier> en plus
public class Repertoire extends FichierAbstrait implements Iterable<Fichier> {

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
    }

    @Override 
    public String toString() {
        return String.format("Repertoire %s", nom());
    }

    @Override
    public Iterator<Fichier> iterator() {
        return this.elements.values().iterator();
    }

}
