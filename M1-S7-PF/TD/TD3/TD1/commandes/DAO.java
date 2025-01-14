package TD1.commandes;

import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import TD3.paires.Paire;

import java.util.ArrayList;
import java.util.HashSet;

import static TD1.commandes.Categorie.*;

public class DAO {
    private List<Commande> commandes;

    private DAO(Commande c1, Commande ...cs) {
        commandes = new ArrayList<>();
        commandes.add(c1);
        commandes.addAll(List.of(cs));
    }

    public static DAO instance = null;

    public static final DAO instance() {
        if (instance == null) {
            Produit camembert = new Produit("Camembert", 4.0, NORMAL);
            Produit yaourts = new Produit("Yaourts", 2.5, INTERMEDIAIRE);
            Produit masques = new Produit("Masques", 25.0, REDUIT);
            Produit gel = new Produit("Gel", 5.0, REDUIT);
            Produit tournevis = new Produit("Tournevis", 4.5, NORMAL);
            //
            Commande c1 = new Commande()
                .ajouter(camembert, 1)
                .ajouter(yaourts, 6);
            Commande c2 = new Commande()
                .ajouter(masques, 2)
                .ajouter(gel, 10)
                .ajouter(camembert, 2)
                .ajouter(masques, 3);
            //
            instance = new DAO(c1,c2);
        }
        return instance;
    }

    /**
     * liste de toutes les commandes
     */
    public List<Commande> commandes() { return commandes; }

    /**
     * ensemble des différents produits commandés
     */
    public Set<Produit> produits() {
        return commandes.stream()
                .flatMap(c -> c.lignes().stream())
                .map(Paire::fst)
                .collect(Collectors.toSet());
    }

    /**
     * Version sans stream de produits()
    **/

    public Set<Produit> produitsSansStreams() {
    Set<Produit> result = new HashSet<>();
    for (Commande commande : commandes) {
        for (Paire<Produit, Integer> ligne : commande.lignes()) {
            result.add(ligne.fst());
        }
    }
    return result;
}

    /**
     * liste des commandes vérifiant un prédicat
     */
    public List<Commande> selectionCommande(Predicate<Commande> p) {
        return commandes.stream()
            .filter(p)
            .collect(Collectors.toList());
    }

    /**
     * Version sans stream de selectionCommande()
    **/

    public List<Commande> selectionCommandeSansStreams(Predicate<Commande> p) {
        List<Commande> result = new ArrayList<>();
        for (Commande commande : commandes) {
            if (p.test(commande)) {
                result.add(commande);
            }
        }
        return result;
    }

    /**
     * liste des commandes dont au moins une ligne vérifie un prédicat
     */
    public List<Commande> selectionCommandeSurExistanceLigne(Predicate<Paire<Produit,Integer>> p) {
        return commandes.stream()
            .filter(c -> c.lignes().stream().anyMatch(p))
            .collect(Collectors.toList());
    }

    /**
     * Version sans stream de selectionCommandeSurExistanceLigne()
    **/

    public List<Commande> selectionCommandeSurExistanceLigneSansStreams(Predicate<Paire<Produit, Integer>> p) {
        List<Commande> result = new ArrayList<>();
        for (Commande commande : commandes) {
            for (Paire<Produit, Integer> ligne : commande.lignes()) {
                if (p.test(ligne)) {
                    result.add(commande);
                    break; // On peut arrêter la boucle si une ligne correspond
                }
            }
        }
        return result;
    }

    /**
     * ensemble des différents produits commandés vérifiant un prédicat
     */
    public Set<Produit> selectionProduits(Predicate<Produit> p) {
        return produits()
            .stream()
            .filter(p)
            .collect(Collectors.toSet());
    }

    /**
     * Version sans stream de selectionProduits()
    **/

    public Set<Produit> selectionProduitsSansStreams(Predicate<Produit> p){
        Set<Produit> resultat = new HashSet<>();

        for (Produit produit : produits()) {
            if(p.test(produit)){
                resultat.add(produit);
            }
        }
        return resultat;
    }

}
