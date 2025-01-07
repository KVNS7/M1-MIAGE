package TD1.commandes;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import TD3.paires.Paire;

import java.util.ArrayList;
import java.util.HashMap;

public class Commande {
    private List<Paire<Produit, Integer>> lignes;

    public Commande() {
        this.lignes = new ArrayList<>();
    }

    public Commande ajouter(Produit p, int q) {
        lignes.add(new Paire<>(p, q));
        return this;
    }

    public List<Paire<Produit, Integer>> lignes() {
        return lignes;
    }

    private static Function<Paire<Produit, Integer>, String> formateurLigne = ligne -> {
        Produit produit = ligne.fst();
        int quantite = ligne.snd();
        return String.format("%s x%d", produit.nom(), quantite);
    };
    
    /* 
     * Méthode toString avec parametre formatteur
     * U
    */

    public String toString(Function<Paire<Produit, Integer>, String> formateur) {
        // Utiliser le formatteur par défaut si aucun n’est fourni
        Function<Paire<Produit, Integer>, String> formateurUtilise = 
            (formateur == null) ? formateurLigne : formateur;

        return lignes.stream()
                .map(formateurUtilise) 
                .collect(Collectors.joining("\n", "Commande\n", ""));
    }

    @Override
    public String toString() {
        return toString(null);
    }

    // @Override
    // public String toString() {
    //     StringBuilder str = new StringBuilder();
    //     str.append("Commande\n");
    //     for (Paire<Produit, Integer> ligne : lignes) {
    //         str.append(String.format("%s x%d\n", ligne.fst(), ligne.snd()));
    //     }
    //     return str.toString();
    // }

    /**
     * cumule les lignes en fonction des produits
     */
    // public Commande normaliser() {
    //     Map<Produit, Integer> lignesCumulees = new HashMap<>();
    //     for (Paire<Produit, Integer> ligne : lignes) {
    //         Produit p = ligne.fst();
    //         int qte = ligne.snd();
    //         if (lignesCumulees.containsKey(ligne.fst())) {
    //             lignesCumulees.put(p, lignesCumulees.get(p) + qte);
    //         } else {
    //             lignesCumulees.put(p, qte);
    //         }
    //     }
    //     Commande commandeNormalisee = new Commande();
    //     for (Produit p : lignesCumulees.keySet()) {
    //         commandeNormalisee.ajouter(p, lignesCumulees.get(p));
    //     }
    //     return commandeNormalisee;
    // }

    /*
     * Version de normaliser en utilisant regrouper foreach et reduce
     */
    public Commande normaliser() {
        Map<Produit, List<Paire<Produit, Integer>>> lignesRegroupees = regrouper(ligne -> ligne.fst());
    
        Commande commandeNormalisee = new Commande();
        
        lignesRegroupees.forEach((produit, lignes) -> {
            int quantiteTotale = lignes.stream()
                .map(Paire::snd)  
                .reduce(0, Integer::sum);
            commandeNormalisee.ajouter(produit, quantiteTotale);
        });
    
        return commandeNormalisee;
    }

    public Double cout(Function<Paire<Produit, Integer>, Double> calculLigne) {
        return normaliser().lignes.stream()
                .map(l -> calculLigne.apply(l))
                .reduce(0.0, (x, y) -> x + y);
    }


    public String affiche(Function<Paire<Produit, Integer>, Double> calculLigne) {
        Commande c = this.normaliser();
        final String HLINE = "+------------+------------+-----+------------+--------+------------+\n";
        StringBuilder str = new StringBuilder();
        str.append("\n\nCommande\n");
        str.append(HLINE);
        str.append("+ nom        + prix       + qté + prix ht    + tva    + prix ttc   +\n");
        str.append(HLINE);
        for (Paire<Produit, Integer> ligne : c.lignes) {
            str.append(String.format("+ %10s + %10.2f + %3d + %10.2f + %5.2f%% + %10.2f +\n", ligne.fst(), // nom
                    ligne.fst().prix(), // prix unitaire
                    ligne.snd(), // qté
                    ligne.fst().prix() * ligne.snd(), // prix ht
                    ligne.fst().cat().tva() * 100, // tva
                    calculLigne.apply(ligne)));
        }
        str.append(HLINE);
        str.append(String.format("Total : %10.2f", c.cout(calculLigne)));
        return str.toString();
    }

    public <K> Map<K, List<Paire<Produit, Integer>>> regrouper(Function<Paire<Produit, Integer>, K> classifier) {
        Map<K, List<Paire<Produit, Integer>>> result = new HashMap<>();
        for (Paire<Produit, Integer> ligne : lignes) {
            K key = classifier.apply(ligne);  
            result.computeIfAbsent(key, k -> new ArrayList<>()).add(ligne);
        }
        return result;
    }

}
