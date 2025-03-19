package code.visiteurs.v1.client;

import code.visiteurs.v1.structure.Fichier;
import code.visiteurs.v1.structure.FichierSimple;
import code.visiteurs.v1.structure.Lien;
import code.visiteurs.v1.structure.Repertoire;
import code.visiteurs.v1.visiteurs.VisiteurAffiche;
import code.visiteurs.v1.visiteurs.VisiteurCompteFeuille;
import code.visiteurs.v1.visiteurs.VisiteurCompteType;
import code.visiteurs.v1.visiteurs.VisiteurFichiers;

import java.util.List;

public class Client {
    public static void main(String[] args) {
        Fichier f1 = new FichierSimple("A.java", "root");
        Fichier f2 = new FichierSimple("B.java", "root");
        Fichier r1 = new Repertoire("code", "root", List.of(f1));
        Fichier r2 = new Repertoire("src", "root", List.of(f2));
        Lien l1 = new Lien("lien", "root", f2);
        Fichier r0 = new Repertoire("racine", "root", List.of(r1,r2,l1));
        VisiteurFichiers<Void> v1 = new VisiteurAffiche();
        r0.accept(v1);
        VisiteurFichiers<Integer> v2 = new VisiteurCompteFeuille();
        r0.accept(v2);
        System.out.println(v2.resultat());
        VisiteurFichiers<List<String>> v3 = new VisiteurCompteType(".java");
        r0.accept(v3);
        // noter multiple occurences
        System.out.println(v3.resultat());

    }
}
