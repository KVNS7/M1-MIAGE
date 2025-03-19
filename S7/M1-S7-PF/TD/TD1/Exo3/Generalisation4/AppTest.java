package Exo3.Generalisation4;

import java.util.List;

import Exo3.Generalisation5.Arbre;
import Exo3.Generalisation5.Chaine;
import Exo3.Generalisation5.Entier;
import Exo3.Generalisation5.Feuille;
import Exo3.Generalisation5.Noeud;

public class AppTest {

    public static final Arbre<Entier> arbre0() {
        final Arbre<Entier> f1 = new Feuille<>(new Entier(3));
        final Arbre<Entier> f2 = new Feuille<>(new Entier(29));

        final Arbre<Entier> n1 = new Noeud<>(List.of(f1, f2));

        final Arbre<Entier> f3 = new Feuille<>(new Entier(7));
        final Arbre<Entier> n2 = new Noeud<>(List.of(n1, f3));

        return n2;
    }

    public static final Arbre<Chaine> arbre1() {
        final Arbre<Chaine> f1 = new Feuille<>(new Chaine("Je"));
        final Arbre<Chaine> f2 = new Feuille<>(new Chaine("m'appelle"));

        final Arbre<Chaine> n1 = new Noeud<>(List.of(f1, f2));

        final Arbre<Chaine> f3 = new Feuille<>(new Chaine("Java"));
        final Arbre<Chaine> n2 = new Noeud<>(List.of(n1, f3));

        return n2;
    }

    public static void main(String[] args) {

        Arbre<Entier> arbre1 = arbre0();

        assertTrue(arbre1.contient(new Entier(7)), "Test contient - arbre1 (7)");
        assertFalse(arbre1.contient(new Entier(17)), "Test contient - arbre1 (17)");
        System.out.println(arbre1.valeurs());
        System.out.println(arbre1.somme());
        System.out.println(arbre1.taille());

        System.out.println("\n");

        Arbre<Chaine> arbre2 = arbre1();

        assertTrue(arbre2.contient(new Chaine("Java")), "Test contient - arbre2 (\"Java\")");
        assertFalse(arbre2.contient(new Chaine("Python")), "Test contient - arbre2 (\"Python\")");
        System.out.println("Valeurs : " + arbre2.valeurs());
        System.out.println("Somme : " + arbre2.somme());
        System.out.println("Taille : " + arbre2.taille());

    }


    public static void assertTrue(boolean condition, String testName) {
        if (!condition) {
            System.out.println(testName + " échoue : attendu vrai.");
        } else {
            System.out.println(testName + " réussit.");
        }
    }

    public static void assertFalse(boolean condition, String testName) {
        if (condition) {
            System.out.println(testName + " échoue : attendu faux.");
        } else {
            System.out.println(testName + " réussit.");
        }
    }
}
