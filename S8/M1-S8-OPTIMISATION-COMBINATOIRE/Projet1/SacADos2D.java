// ! bestWithout / bestWith ???
// ! table ???

import java.util.*;

public class SacADos2D {

    /** Représente un objet avec poids, volume et valeur. */
    public static class Objet {
        final int poids;
        final int volume;
        final int valeur;

        public Objet(int poids, int volume, int valeur) {
            this.poids = poids;
            this.volume = volume;
            this.valeur = valeur;
        }

        @Override
        public String toString() {
            return String.format("\n\t- poids=%d,\n\t- volume=%d, \n\t- valeur=%d\n", poids, volume, valeur);
        }
    }

    /** Résultat du calcul : valeur optimale et indices des objets sélectionnés. */
    public static class Resultat {
        public final int valeurOptimale;
        public final List<Objet> objetsChoisis;

        Resultat(int valeurOptimale, List<Objet> objetsChoisis) {
            this.valeurOptimale = valeurOptimale;
            this.objetsChoisis = objetsChoisis;
        }
    }

    /**
     * Calcule la valeur maximale et la liste des objets à prendre.
     * 
     * @param objets    tableau d'Items
     * @param poidsMax  capacité maximale en poids
     * @param volumeMax capacité maximale en volume
     * @return Resultat (valeur optimale + indices des objets pris)
     */
    public static Resultat resoudre(Objet[] objets, int poidsMax, int volumeMax) {
        int n = objets.length;
        // table[k][p][v] = meilleure valeur en considérant les objets k..n‑1
        int[][][] table = new int[n + 1][poidsMax + 1][volumeMax + 1];

        // Remplissage de la table de droite à gauche (k décroissant)
        for (int k = n - 1; k >= 0; k--) {
            Objet obj = objets[k];
            for (int p = 0; p <= poidsMax; p++) {
                for (int v = 0; v <= volumeMax; v++) {
                    int sansObjetK = table[k + 1][p][v];
                    int avecObjetK = sansObjetK;
                    if (obj.poids <= p && obj.volume <= v) {
                        avecObjetK = obj.valeur + table[k + 1][p - obj.poids][v - obj.volume];
                    }
                    table[k][p][v] = Math.max(sansObjetK, avecObjetK);
                }
            }
        }

        // Reconstruction de la solution optimale
        List<Objet> objetsChoisis = new ArrayList<>();
        int p = poidsMax, v = volumeMax;
        for (int k = 0; k < n; k++) {
            if (table[k][p][v] != table[k + 1][p][v]) { // l'objet k est pris
                objetsChoisis.add(objets[k]);
                p -= objets[k].poids;
                v -= objets[k].volume;
            }
        }
        return new Resultat(table[0][poidsMax][volumeMax], objetsChoisis);
    }

    /* Démonstration rapide depuis la ligne de commande */
    public static void main(String[] args) {
        // Exemple : 4 objets
        Objet[] objets = {
                new Objet(3, 1, 40),
                new Objet(4, 5, 81),
                new Objet(2, 3, 40),
                new Objet(6, 2, 56),
                new Objet(3, 3, 36),
        };

        int poidsMax = 10;
        int volumeMax = 9;

        Resultat res = resoudre(objets, poidsMax, volumeMax);
        System.out.println("Valeur optimale : " + res.valeurOptimale);
        System.out.println("Objets pris :\n" + res.objetsChoisis);
    }
}