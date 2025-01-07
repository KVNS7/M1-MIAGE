package Exo4;

import java.util.List;
import java.util.Set;
import java.util.function.Function;

import Exo2.Paire;

public class Main {

    public static void main(String[] args) {
        // Récupérer l'instance du DAO
        DAO data = DAO.instance();

        // 1. Affichage des produits commandés à TVA réduite (utiliser selectionProduit)
        System.out.println("1. Produits commandés à TVA réduite :");
        // Version Lambda
        Set<Produit> produitsTVAReduite = data.selectionProduits(p -> p.cat() == Categorie.REDUIT);
        System.out.println(produitsTVAReduite);

        // 2. Affichage des produits commandés à TVA réduite et coûtant plus de 5€
        System.out.println("\n2. Produits commandés à TVA réduite et coûtant plus de 5€ :");
        // Version Lambda
        Set<Produit> produitsTVAReduiteEtPlus5 = data.selectionProduits(p -> p.cat() == Categorie.REDUIT && p.prix() > 5);
        System.out.println(produitsTVAReduiteEtPlus5);

        // 3. Affichage des commandes (non normalisées) de plus de 2 items
        System.out.println("\n3. Commandes (non normalisées) de plus de 2 items :");
        List<Commande> commandesPlusDe2Items = data.selectionCommande(c -> c.lignes().size() > 2);
        for (Commande c : commandesPlusDe2Items) {
            System.out.println(c);
        }

        // 4. Affichage des commandes (non normalisées) contenant au moins un produit à TVA réduite commandé en plus de 2 exemplaires
        System.out.println("\n4. Commandes (non normalisées) contenant au moins un produit à TVA réduite commandé en plus de 2 exemplaires :");
        List<Commande> commandesAvecTVAReduitePlusDe2 = data.selectionCommandeSurExistanceLigne(ligne -> ligne.fst().cat() == Categorie.REDUIT && ligne.snd() > 2);
        for (Commande c : commandesAvecTVAReduitePlusDe2) {
            System.out.println(c);
        }

        // 5. Affichage des commandes avec la règle de calcul prix TTC
        System.out.println("\n5. Affichage des commandes avec le calcul du prix TTC (prix unitaire * (1 + TVA) * quantité) :");
        Function<Paire<Produit, Integer>, Double> calculLigne = ligne ->
            ligne.fst().prix() * ligne.snd() * (1 + ligne.fst().cat().tva());

        for (Commande c : data.commandes()) {
            System.out.println(c.affiche(calculLigne));
        }

        // 6. Affichage des commandes avec réduction de prix unitaire pour chaque ligne avec qté > 2
        System.out.println("\n6. Affichage des commandes avec réduction sur prix unitaire pour les lignes dont la quantité est > 2 :");
        Function<Paire<Produit, Integer>, Double> calculLigneAvecReduction = ligne -> {
            double prixUnitaire = ligne.fst().prix();
            if (ligne.snd() > 2) {
                prixUnitaire *= 0.9; // Appliquer une réduction de 10% sur le prix unitaire
            }
            return prixUnitaire * ligne.snd() * (1 + ligne.fst().cat().tva());
        };

        for (Commande c : data.commandes()) {
            System.out.println(c.affiche(calculLigneAvecReduction));
        }
    }
}
