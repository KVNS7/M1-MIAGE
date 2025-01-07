import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import TD1.commandes.*;

public class AppTd1Ex4 {

    public static void main(String[] args) {
        DAO data = DAO.instance();

        // 1. Affichage des produits commandés à TVA réduite
        Set<Produit> produitsTVAReduite = data.selectionProduits(p -> p.cat() == Categorie.REDUIT);
        System.out.println("Produits à TVA réduite : " + produitsTVAReduite.stream()
                .map(Produit::nom)
                .collect(Collectors.toList()));

        // 2. Affichage des produits commandés à TVA réduite et coûtant plus de 5€
        Set<Produit> produitsTVAReduitePlus5 = data.selectionProduits(p -> p.cat() == Categorie.REDUIT && p.prix() > 5);
        System.out.println("Produits à TVA réduite et coûtant plus de 5€ : " + produitsTVAReduitePlus5.stream()
                .map(Produit::nom)
                .collect(Collectors.toList()));

        // 3. Affichage des commandes (non normalisées) de plus de 2 items
        List<Commande> commandesPlusDe2Items = data.selectionCommande(c -> c.lignes().size() > 2);
        System.out.println("Commandes de plus de 2 items : ");
        commandesPlusDe2Items.forEach(System.out::println);

        // 4. Affichage des commandes contenant un produit à TVA réduite commandé en plus de 2 exemplaires
        List<Commande> commandesAvecProduitTVAReduitPlus2 = data.selectionCommandeSurExistanceLigne(
                p -> p.fst().cat() == Categorie.REDUIT && p.snd() > 2
        );
        System.out.println("Commandes avec un produit à TVA réduite commandé en plus de 2 exemplaires : ");
        commandesAvecProduitTVAReduitPlus2.forEach(System.out::println);

        // 5. Affichage des commandes avec la règle de calcul du prix TTC
        data.commandes().forEach(c -> {
            String result = c.affiche(ligne -> ligne.fst().prix() * ligne.snd() * (1 + ligne.fst().cat().tva()));
            System.out.println(result);
        });

        // 6. Affichage des commandes avec réduction de la valeur du prix unitaire pour chaque ligne
        data.commandes().forEach(c -> {
            String result = c.affiche(ligne -> {
                double prixUnitaire = ligne.fst().prix();
                if (ligne.snd() > 2) {
                    prixUnitaire = prixUnitaire * 0.9;  // Réduction de 10% si quantité > 2
                }
                return prixUnitaire * ligne.snd() * (1 + ligne.fst().cat().tva());
            });
            System.out.println(result);
        });
    }
}
