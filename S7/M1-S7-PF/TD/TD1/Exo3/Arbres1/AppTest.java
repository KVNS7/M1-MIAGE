package Exo3.Arbres1;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class AppTest {

    public static final Arbre arbre0() {
        return new Noeud(new ArrayList<Arbre>());
    }

    public static final Arbre arbre1() {
        final Arbre f1 = new Feuille(1);
        final Arbre f2 = new Feuille(2);
        final Arbre n1 = new Noeud(List.of(f1, f2));
        final Arbre f3 = new Feuille(3);
        final Arbre n2 = new Noeud(List.of(n1, f3));
        return n2;
    }

    public static final Arbre arbre2() {
        final Arbre f1 = new Feuille(2);
        final Arbre f2 = new Feuille(3);
        final Arbre n1 = new Noeud(List.of(f1, f2));
        final Arbre f3 = new Feuille(1);
        final Arbre n2 = new Noeud(List.of(n1, f3));
        return n2;
    }

    public static final Arbre arbre3() {
        final Arbre f1 = new Feuille(2);
        final Arbre f2 = new Feuille(1);
        final Arbre n1 = new Noeud(List.of(f1, f2));
        final Arbre f3 = new Feuille(3);
        final Arbre n2 = new Noeud(List.of(n1, f3));
        return n2;
    }

    public static void main(String[] args) {
        testSize();
        testContient();
        testValeurs();
        testValeur();
        testMin();
        testMax();
        testEstTrie();
    }

    public static void testSize() {
        assertEqual(0, arbre0().taille(), "Test Size - arbre0");
        assertEqual(3, arbre1().taille(), "Test Size - arbre1");
        assertEqual(3, arbre2().taille(), "Test Size - arbre2");
        assertEqual(3, arbre3().taille(), "Test Size - arbre3");
    }

    public static void testContient() {
        assertFalse(arbre0().contient(1), "Test Contient - arbre0 (1)");
        assertFalse(arbre0().contient(2), "Test Contient - arbre0 (2)");
        assertFalse(arbre0().contient(3), "Test Contient - arbre0 (3)");
        assertFalse(arbre0().contient(4), "Test Contient - arbre0 (4)");
        assertTrue(arbre1().contient(1), "Test Contient - arbre1 (1)");
        assertTrue(arbre2().contient(1), "Test Contient - arbre2 (1)");
        assertTrue(arbre3().contient(1), "Test Contient - arbre3 (1)");
        assertTrue(arbre1().contient(2), "Test Contient - arbre1 (2)");
        assertTrue(arbre2().contient(2), "Test Contient - arbre2 (2)");
        assertTrue(arbre3().contient(2), "Test Contient - arbre3 (2)");
        assertTrue(arbre1().contient(3), "Test Contient - arbre1 (3)");
        assertTrue(arbre2().contient(3), "Test Contient - arbre2 (3)");
        assertTrue(arbre3().contient(3), "Test Contient - arbre3 (3)");
        assertFalse(arbre1().contient(4), "Test Contient - arbre1 (4)");
        assertFalse(arbre2().contient(4), "Test Contient - arbre2 (4)");
        assertFalse(arbre3().contient(4), "Test Contient - arbre3 (4)");
    }

    public static void testValeurs() {
        final Set<Integer> contenu = Set.of(1, 2, 3);
        assertEqual(Set.of(), arbre0().valeurs(), "Test Valeurs - arbre0");
        assertEqual(contenu, arbre1().valeurs(), "Test Valeurs - arbre1");
        assertEqual(contenu, arbre2().valeurs(), "Test Valeurs - arbre2");
        assertEqual(contenu, arbre3().valeurs(), "Test Valeurs - arbre3");
    }

    public static void testValeur() {
        //assertEqual(null, arbre0().somme(), "Test Valeur - arbre0");
        assertEqual((Integer) 6, arbre1().somme(), "Test Valeur - arbre1");
        assertEqual((Integer) 6, arbre2().somme(), "Test Valeur - arbre2");
        assertEqual((Integer) 6, arbre3().somme(), "Test Valeur - arbre3");
    }

    public static void testMin() {
        //assertEqual(null, arbre0().min(), "Test Min - arbre0");
        assertEqual((Integer) 1, arbre1().min(), "Test Min - arbre1");
        assertEqual((Integer) 1, arbre2().min(), "Test Min - arbre2");
        assertEqual((Integer) 1, arbre3().min(), "Test Min - arbre3");
    }

    public static void testMax() {
        //assertEqual(null, arbre0().max(), "Test Max - arbre0");
        assertEqual((Integer) 3, arbre1().max(), "Test Max - arbre1");
        assertEqual((Integer) 3, arbre2().max(), "Test Max - arbre2");
        assertEqual((Integer) 3, arbre3().max(), "Test Max - arbre3");
    }

    public static void testEstTrie() {
        assertTrue(arbre0().estTrie(), "Test EstTrie - arbre0");
        assertTrue(arbre1().estTrie(), "Test EstTrie - arbre1");
        assertFalse(arbre2().estTrie(), "Test EstTrie - arbre2");
        assertFalse(arbre3().estTrie(), "Test EstTrie - arbre3");
    }

    // Méthodes d'assertion
    public static void assertEqual(Object expected, Object actual, String testName) {
        if (!expected.equals(actual)) {
            System.out.println(testName + " échoue : attendu " + expected + ", mais obtenu " + actual);
        } else {
            System.out.println(testName + " réussit.");
        }
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
