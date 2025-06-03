import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Main {

    /**
     * Heuristique h3: minorant sans découpe
     */
    public static double calculeMinorantH3(List<Element> elements, int poidsMax, int volumeMax) {
        elements.sort((e1, e2) -> Double.compare( // tri par ordre decroissant
                e2.getValeur() / (e2.getPoids() + e2.getVolume()),
                e1.getValeur() / (e1.getPoids() + e1.getVolume())));
        int poids = 0, volume = 0;
        double valeur = 0;
        for (Element e : elements) {
            int p = (int) e.getPoids();
            int v = (int) e.getVolume();
            if (poids + p <= poidsMax && volume + v <= volumeMax) {
                poids += p;
                volume += v;
                valeur += e.getValeur();
            }
        }
        return valeur;
    }

    /**
     * Heuristique h4: majorant avec découpe
     */
    public static double calculeMajorantH4(List<Element> elements, int poidsMax, int volumeMax) {
        int capacite = poidsMax + volumeMax;
        elements.sort((e1, e2) -> Double.compare( // meme tri que h3
                e2.getValeur() / (e2.getPoids() + e2.getVolume()),
                e1.getValeur() / (e1.getPoids() + e1.getVolume())));
        int poidsVirtuel = 0; // correspond à poids +volume
        double valeur = 0;
        for (Element e : elements) {
            int taille = (int) (e.getPoids() + e.getVolume());
            if (poidsVirtuel + taille <= capacite) {
                poidsVirtuel += taille;
                valeur += e.getValeur();
            } else {
                int reste = capacite - poidsVirtuel;
                if (reste > 0) {
                    double ratio = (double) reste / taille;
                    valeur += e.getValeur() * ratio;
                }
                break;
            }
        }
        return valeur;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Methode avec pile
     */
    public static Result branchAndBound(List<Element> elements, int poidsMax, int volumeMax) {
        elements.sort((e1, e2) -> Double.compare(
                e2.getValeur() / (e2.getPoids() + e2.getVolume()),
                e1.getValeur() / (e1.getPoids() + e1.getVolume())));

        double bestVal = 0;
        List<Element> bestChoix = new ArrayList<>();

        // Pile pour la gestion itérative
        Stack<State> stack = new Stack<>();
        stack.push(new State(0, poidsMax, volumeMax, 0, new ArrayList<>()));

        while (!stack.isEmpty()) {
            State s = stack.pop();

            // Cas terminal : tous les objets traités
            if (s.idx == elements.size()) {
                if (s.valCourante > bestVal) {
                    bestVal = s.valCourante;
                    bestChoix = new ArrayList<>(s.choix);
                }
                continue;
            }

            // Élagage par majorant (h4) sur les éléments restants
            List<Element> reste = new ArrayList<>(elements.subList(s.idx, elements.size()));
            double maj = s.valCourante + calculeMajorantH4(reste, s.poidsRem, s.volRem);
            if (maj <= bestVal) {
                continue; // on stoppe l'exploration
            }

            // Prochaine inclusion/exclusion
            Element e = elements.get(s.idx);
            int p = (int) e.getPoids();
            int v = (int) e.getVolume();

            // Exclusion
            stack.push(new State(
                    s.idx + 1,
                    s.poidsRem,
                    s.volRem,
                    s.valCourante,
                    new ArrayList<>(s.choix)));

            // Inclusion
            if (p <= s.poidsRem && v <= s.volRem) {
                List<Element> newChoix = new ArrayList<>(s.choix);
                newChoix.add(e);
                stack.push(new State(
                        s.idx + 1,
                        s.poidsRem - p,
                        s.volRem - v,
                        s.valCourante + e.getValeur(),
                        newChoix));
            }
        }

        return new Result(bestVal, bestChoix);
    }

    public static void main(String[] args) {
        int P = 10, V = 9;// poids et volume max
        List<Element> elements = List.of(
                new Element(1, 3, 1, 40),
                new Element(2, 4, 5, 81),
                new Element(3, 2, 3, 40),
                new Element(4, 6, 2, 56),
                new Element(5, 3, 3, 36));

        double minorant = calculeMinorantH3(new ArrayList<>(elements), P, V);
        double majorant = calculeMajorantH4(new ArrayList<>(elements), P, V);

        // System.out.println("Minorant h3 (sans decoupe) : " + minorant);
        // System.out.println("Majorant h4 (avec decoupe) : " + majorant);

        Result sol = branchAndBound(new ArrayList<>(elements), P, V);//calcul
        System.out.println("\nSolution optimale: " + sol.valeur);
        System.out.println("Elements selectionnes :");
        for (Element e : sol.choix) {
            System.out.printf(
                    "- id=%d, poids=%.0f, volume=%.0f, valeur=%.0f%n",
                    e.getId(), e.getPoids(), e.getVolume(), e.getValeur());
        }

    }
}
