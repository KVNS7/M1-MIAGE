package Exo3;

import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;

public class Main {
    public static void main(String[] args) {
        Matiere m1 = new Matiere("MAT1");
        Matiere m2 = new Matiere("MAT2");
        UE ue1 = new UE("UE1", Map.of(m1, 2, m2, 2));
        Matiere m3 = new Matiere("MAT3");
        UE ue2 = new UE("UE2", Map.of(m3, 1));
        Annee a1 = new Annee(Set.of(ue1, ue2));
        Etudiant e1 = new Etudiant("39001", "Alice", "Merveille", a1);
        e1.noter(m1, 12.0);
        e1.noter(m2, 14.0);
        e1.noter(m3, 10.0);
        System.out.println(e1);
        Etudiant e2 = new Etudiant("39002", "Bob", "Eponge", a1);
        e2.noter(m1, 4.0);
        e2.noter(m3, 4.0);
        Etudiant e3 = new Etudiant("39003", "Charles", "Chaplin", a1);
        e3.noter(m1, 18.0);
        e3.noter(m2, 5.0);
        e3.noter(m3, 14.0);

        a1.afficheSi("TOUS LES ETUDIANTS (boucle for):", etudiant -> true);

        a1.afficheSi("TOUS LES ETUDIANTS (forEach):", 
                     etudiant -> true, 
                     System.out::println);

        Predicate<Etudiant> aDEF = etudiant -> {
            for (UE ue : etudiant.annee().ues()) {
                for (Matiere matiere : ue.ects().keySet()) {
                    if (!etudiant.notes().containsKey(matiere)) {
                        return true; 
                    }
                }
            }
            return false; 
        };

        a1.afficheSi("TOUS LES ETUDIANTS DEFAILLANTS:", aDEF);

        Predicate<Etudiant> aNoteEliminatoire = etudiant -> {
            for (UE ue : etudiant.annee().ues()){
                for (Matiere matiere : ue.ects().keySet()){
                    if (etudiant.notes().containsKey(matiere) && etudiant.notes().get(matiere) < 6){
                        return true;
                    }
                }
            }
            return false;
        };

        a1.afficheSi("TOUS LES ETUDIANTS AVEC UNE NOTE ELIMINATOIRE:", aNoteEliminatoire);

        Function<Etudiant,Double> moyenne = etudiant -> {
            if (aDEF.test(etudiant)) {
                return null; 
            }
        
            double somme = 0.0;
            int count = 0;
        
            for (UE ue : etudiant.annee().ues()) {
                for (Matiere matiere : ue.ects().keySet()) {
                    if (etudiant.notes().containsKey(matiere)) {
                        somme += etudiant.notes().get(matiere);
                        count++;
                    }
                }
            }
        
            if (count == 0) {
                return null;
            }
        
            return somme / count;
        };


        Predicate<Etudiant> naPasLaMoyennev1 = etudiant -> {
            if(aDEF.test(etudiant)){
                return false;
            }
            Optional<Double> moyenneEtudiant = Optional.ofNullable(moyenne.apply(etudiant));

            if (moyenneEtudiant.isEmpty()) {
                return false; 
            }

            return moyenneEtudiant.get() < 10;
        };

        a1.afficheSi("TOUS LES ETUDIANTS QUI N'ONT PAS LA MOYENNE:",naPasLaMoyennev1);


        Predicate<Etudiant> naPasLaMoyennev2 = etudiant -> {
            if(aDEF.test(etudiant))
                return true;
            Optional<Double> moyenneEtudiant = Optional.ofNullable(moyenne.apply(etudiant));

            if (moyenneEtudiant.isEmpty()) {
                return false; 
            }

            return moyenneEtudiant.get() < 10;
            
        };

        a1.afficheSi("TOUS LES ETUDIANTS QUI N'ONT PAS LA MOYENNE V2:",naPasLaMoyennev2);

        Predicate<Etudiant> session2v1 = etudiant -> 
                naPasLaMoyennev1.test(etudiant) || naPasLaMoyennev2.test(etudiant);

        a1.afficheSi("ETUDIANTS EN SESSION 2 (v1)", session2v1);

        Function<Etudiant, String> representantAvecMoyenne = etudiant -> {
            Double moyenneEtudiant = moyenne.apply(etudiant);
            if (moyenneEtudiant == null) {
                return etudiant.prenom() + " " + etudiant.nom() + " : défaillant";
            } else {
                return etudiant.prenom() + " " + etudiant.nom() + " : " + String.format("%.2f", moyenneEtudiant);
            }
        };

        a1.afficheSiv2("TOUS LES ETUDIANTS", etudiant -> true, representantAvecMoyenne);



        Function<Etudiant,String> moyenneIndicative = etudiant -> {
            double somme = 0.0;
            int count = 0;
        
            for (UE ue : etudiant.annee().ues()) {
                for (Matiere matiere : ue.ects().keySet()) {
                    if (etudiant.notes().containsKey(matiere)) {
                        somme += etudiant.notes().get(matiere);
                        count++;
                    }
                }
            }

            if(aDEF.test(etudiant))
                count++;
        
            if (count == 0) {
                return null;
            }
        
            return etudiant.prenom() + " " + etudiant.nom() + " : " + String.format("%.2f", somme / count);
        };


        a1.afficheSiv2("TOUS LES ETUDIANTS AVEC MOYENNE INDICATIVE", session2v1, moyenneIndicative);

    }

}
